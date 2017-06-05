package cs3500.music.view;

import cs3500.music.model.Note;

import java.util.List;
import java.util.Map;

public interface IView {

  /**
   * All notes to print.
   *
   * @param music the copy of music stored in the model
   */
  void setAllNotes(Map<Integer, List<Note>> music);


  /**
   * Length of the music.
   *
   * @param length length of the music
   */
  void setLength(int length);


  /**
   * All different pitch & octave.
   *
   * @param notes list of tones
   */
  void setTone(List<String> notes);

  /**
   * Sets the tempo.
   *
   * @param tempo the tempo
   */
  void setTempo(int tempo);

  /**
   * Shows the view2.
   */
  void showView();


  /**
   * Sets the current beat to each view2.
   * @param current the current beat
   */
  void setCurrentBeat(int current);
}
