package cs3500.music.view;

/**
 * Created by KevinLiang on 11/7/16.
 */


import org.junit.Test;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.provider.ConsoleView;

import javax.sound.midi.MidiUnavailableException;

import static org.junit.Assert.assertEquals;

//Testing class for the View Factory.
public class ViewFactoryTest {

  IMusicModel model = new MusicModel.Builder().build();
  ViewFactory viewFactory = new ViewFactory(model);

  //  @Test
  //  public void testMidi() throws MidiUnavailableException {
  //    assertEquals(viewFactory.createView(
  //            ViewFactory.ViewType.MIDI) instanceof MidiViewImpl, true);
  //  }

  @Test
  public void testConsole() throws MidiUnavailableException {
    assertEquals(viewFactory.createView(ViewFactory.ViewType.CONSOLE) instanceof ConsoleView, true);
  }

//  @Test
//  public void testGui() throws MidiUnavailableException {
//    assertEquals(viewFactory.createView(ViewFactory.ViewType.GUI) instanceof GuiViewFrame, true);
//  }

}