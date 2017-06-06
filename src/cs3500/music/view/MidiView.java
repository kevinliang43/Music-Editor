package cs3500.music.view;

import cs3500.music.model.Note;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A MIDI view2 of the music.
 * Plays the whole music.
 */
public class MidiView extends JFrame implements IView {
  private final Receiver receiver;
  private final Synthesizer synthesizer;
  private Map<Integer, List<Note>> music; //a copy of music in the model.
  private int tempo;
  private int current;


  /**
   * Constructs a midiView.
   */
  public MidiView() {
    Synthesizer synthTemp = null;
    Receiver receiverTemp = null;
    try {
      synthTemp = MidiSystem.getSynthesizer();
      receiverTemp = synthTemp.getReceiver();
      synthTemp.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    synthesizer = synthTemp;
    receiver = receiverTemp;
    music = new HashMap<Integer, List<Note>>();
  }

  /**
   * Constructs a convenient constructor for a midi view2.
   *
   * @param midi a midiDevice
   */
  public MidiView(MidiDevice midi) {
    Synthesizer synthTemp = null;
    Receiver receiverTemp = null;

    try {
      receiverTemp = midi.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    receiver = receiverTemp;
    synthesizer = synthTemp;
  }

  /**
   * Relevant classes and methods from the .javaxsound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  /**
   * Plays the note at certain time.
   *
   * @param time the beat number.
   * @throws InvalidMidiDataException when needed.
   */
  private void playNote(int time) throws InvalidMidiDataException {
    if (music.containsKey(time)) {
      for (Note note : music.get(time)) {

        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
                note.getPitch(), note.getVolume());
        //we changed the receiver.send() method here, in order to pause and resume music properly.
        receiver.send(start, -1);

        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
                note.getPitch(), note.getVolume());

        //receiver.send() method changed here as well.
        receiver.send(stop, synthesizer.getMicrosecondPosition()
                + tempo * (note.getEnd() - note.getStart()));
      }
    }
  }


  @Override
  public void setAllNotes(Map<Integer, List<Note>> music) {
    this.music = music;
  }

  @Override
  public void setLength(int length) {
    // does not need it.
  }

  @Override
  public void setTone(List<String> tone) {
    // midi view2 does not need to set tone.
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }


  @Override
  public void setCurrentBeat(int current) {
    this.current = current;
  }


  @Override
  public void showView() {
    try {
      playNote(current - 1);
      Thread.sleep(tempo / 1000);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
