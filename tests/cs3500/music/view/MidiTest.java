package cs3500.music.view;

/**
 * Created by KevinLiang on 11/7/16.
 */

//import org.junit.Test;
import java.io.FileReader;
import cs3500.music.util.CompositionBuilder;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;


//Testing class for the MIDI device.

public class MidiTest {

  //MidiViewImpl midi;
  FileReader fr;
  MockMidiDevice mockM = new MockMidiDevice();
  MockReceiver mockR;
  IMusicModel model;
  CompositionBuilder<IMusicModel> builder = new MusicModel.Builder();

//  /**
//   * initializes the fields above. Gets proper and available testing conditions.
//   */
//  public void initialize() {
//    try {
//      fr = new FileReader(new File("mary-little-lamb.txt"));
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
//    model = MusicReader.parseFile(fr, builder);
//
//    try {
//      mockR = (MockReceiver) mockM.getReceiver();
//    } catch (MidiUnavailableException e) {
//      e.printStackTrace();
//    }
//    try {
//      midi = new MidiViewImpl(model, mockM);
//    } catch (MidiUnavailableException e) {
//      e.printStackTrace();
//    }
//  }

//  /**
//   * Test the output of the Mock Midi on the Mary-little-lamb.txt input.
//   */
//  @Test
//  public void testMary() {
//    this.initialize();
//    midi.displayView();
//    assertEquals("NOTE_ON, 0, 64, 90, 200000.\n" +
//            "NOTE_OFF, 0, 64 90 400000.\n" +
//            "NOTE_ON, 0, 55, 90, 200000.\n" +
//            "NOTE_OFF, 0, 55 90 1400000.\n" +
//            "NOTE_ON, 0, 62, 90, 600000.\n" +
//            "NOTE_OFF, 0, 62 90 800000.\n" +
//            "NOTE_ON, 0, 60, 90, 1000000.\n" +
//            "NOTE_OFF, 0, 60 90 1200000.\n" +
//            "NOTE_ON, 0, 62, 90, 1400000.\n" +
//            "NOTE_OFF, 0, 62 90 1600000.\n" +
//            "NOTE_ON, 0, 55, 90, 1800000.\n" +
//            "NOTE_OFF, 0, 55 90 3000000.\n" +
//            "NOTE_ON, 0, 64, 90, 1800000.\n" +
//            "NOTE_OFF, 0, 64 90 2000000.\n" +
//            "NOTE_ON, 0, 64, 90, 2200000.\n" +
//            "NOTE_OFF, 0, 64 90 2400000.\n" +
//            "NOTE_ON, 0, 64, 90, 2600000.\n" +
//            "NOTE_OFF, 0, 64 90 3000000.\n" +
//            "NOTE_ON, 0, 55, 90, 3400000.\n" +
//            "NOTE_OFF, 0, 55 90 4800000.\n" +
//            "NOTE_ON, 0, 62, 90, 3400000.\n" +
//            "NOTE_OFF, 0, 62 90 3600000.\n" +
//            "NOTE_ON, 0, 62, 90, 3800000.\n" +
//            "NOTE_OFF, 0, 62 90 4000000.\n" +
//            "NOTE_ON, 0, 62, 90, 4200000.\n" +
//            "NOTE_OFF, 0, 62 90 4800000.\n" +
//            "NOTE_ON, 0, 55, 90, 5000000.\n" +
//            "NOTE_OFF, 0, 55 90 5200000.\n" +
//            "NOTE_ON, 0, 64, 90, 5000000.\n" +
//            "NOTE_OFF, 0, 64 90 5200000.\n" +
//            "NOTE_ON, 0, 67, 90, 5400000.\n" +
//            "NOTE_OFF, 0, 67 90 5600000.\n" +
//            "NOTE_ON, 0, 67, 90, 5800000.\n" +
//            "NOTE_OFF, 0, 67 90 6400000.\n" +
//            "NOTE_ON, 0, 55, 90, 6600000.\n" +
//            "NOTE_OFF, 0, 55 90 8000000.\n" +
//            "NOTE_ON, 0, 64, 90, 6600000.\n" +
//            "NOTE_OFF, 0, 64 90 6800000.\n" +
//            "NOTE_ON, 0, 62, 90, 7000000.\n" +
//            "NOTE_OFF, 0, 62 90 7200000.\n" +
//            "NOTE_ON, 0, 60, 90, 7400000.\n" +
//            "NOTE_OFF, 0, 60 90 7600000.\n" +
//            "NOTE_ON, 0, 62, 90, 7800000.\n" +
//            "NOTE_OFF, 0, 62 90 8000000.\n" +
//            "NOTE_ON, 0, 55, 90, 8200000.\n" +
//            "NOTE_OFF, 0, 55 90 9600000.\n" +
//            "NOTE_ON, 0, 64, 90, 8200000.\n" +
//            "NOTE_OFF, 0, 64 90 8400000.\n" +
//            "NOTE_ON, 0, 64, 90, 8600000.\n" +
//            "NOTE_OFF, 0, 64 90 8800000.\n" +
//            "NOTE_ON, 0, 64, 90, 9000000.\n" +
//            "NOTE_OFF, 0, 64 90 9200000.\n" +
//            "NOTE_ON, 0, 64, 90, 9400000.\n" +
//            "NOTE_OFF, 0, 64 90 9600000.\n" +
//            "NOTE_ON, 0, 55, 90, 9800000.\n" +
//            "NOTE_OFF, 0, 55 90 11200000.\n" +
//            "NOTE_ON, 0, 62, 90, 9800000.\n" +
//            "NOTE_OFF, 0, 62 90 10000000.\n" +
//            "NOTE_ON, 0, 62, 90, 10200000.\n" +
//            "NOTE_OFF, 0, 62 90 10400000.\n" +
//            "NOTE_ON, 0, 64, 90, 10600000.\n" +
//            "NOTE_OFF, 0, 64 90 10800000.\n" +
//            "NOTE_ON, 0, 62, 90, 11000000.\n" +
//            "NOTE_OFF, 0, 62 90 11200000.\n" +
//            "NOTE_ON, 0, 52, 90, 11400000.\n" +
//            "NOTE_OFF, 0, 52 90 12800000.\n" +
//            "NOTE_ON, 0, 60, 90, 11400000.\n" +
//            "NOTE_OFF, 0, 60 90 12800000.", mockM.getOutput());
//  }
}
