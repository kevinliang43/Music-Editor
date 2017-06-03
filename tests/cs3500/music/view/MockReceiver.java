package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;


public class MockReceiver implements Receiver {

  StringBuilder output;

  MockReceiver(StringBuilder output) {
    this.output = output;
  }

  @Override
  public void send(MidiMessage message, long time) {
    ShortMessage sentMessage = (ShortMessage) message;
    int inst = sentMessage.getChannel();
    int pitch = sentMessage.getData1();
    int vol = sentMessage.getData2();

    if (sentMessage.getCommand() == ShortMessage.NOTE_ON) {
      this.output.append("NOTE_ON, " + inst + ", " + pitch + ", " + vol + ", " + time + ".\n");
    }
    else {
      this.output.append("NOTE_OFF, " + inst + ", " + pitch + " " + vol + " " + time + ".\n");
    }
  }

  @Override
  public void close() {
    System.out.println(output);
  }
}