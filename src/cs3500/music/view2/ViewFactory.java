package cs3500.music.view2;

import cs3500.music.model.IMusicModel;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.IView;
import cs3500.music.view.MidiView;


/**
 * Created by Oliver on 12/4/2016.
 */

public class ViewFactory {
  private IMusicModel musicModel;

  public enum ViewType {
    CONSOLE, GUI, MIDI, COMPOSITE;
  }

  public ViewFactory(IMusicModel model) {
    this.musicModel = model;
  }

  /**
   * Creates the View, depending on which view2 is wanted.
   *
   * @param type represents the type of view2 requested.
   * @return A MusicView, differing depending on which view2 is requested.
   */
  public IView createView(ViewType type) {
    IView cv;
    if (type == ViewType.CONSOLE) {

      cv = new ConsoleView(new StringBuffer());
      cv.setAllNotes(this.musicModel.getMusic());
      cv.setLength(this.musicModel.getDuration());
      cv.setTone(this.musicModel.getTone(this.musicModel.getNotes()));
      cv.setTempo(this.musicModel.getTempo());

      return cv;
    } else if (type == ViewType.GUI) {
      cv = new GuiViewFrame();
      cv.setAllNotes(this.musicModel.getMusic());
      cv.setLength(this.musicModel.getDuration());
      cv.setTone(this.musicModel.getTone(this.musicModel.getNotes()));
      cv.setTempo(this.musicModel.getTempo());
      return cv;
    } else if (type == ViewType.COMPOSITE) {
      cv = new CompositeView();
      cv.setAllNotes(this.musicModel.getMusic());
      cv.setLength(this.musicModel.getDuration());
      cv.setTone(this.musicModel.getTone(this.musicModel.getNotes()));
      cv.setTempo(this.musicModel.getTempo());

      return cv;
    } else {
      cv = new MidiView();
      cv.setAllNotes(this.musicModel.getMusic());
      cv.setLength(this.musicModel.getDuration());
      cv.setTone(this.musicModel.getTone(this.musicModel.getNotes()));
      cv.setTempo(this.musicModel.getTempo());
      return cv;

    }
  }

}
