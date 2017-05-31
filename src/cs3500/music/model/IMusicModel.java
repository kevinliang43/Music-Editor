package cs3500.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by Oliver on 10/17/2016.
 */

/**
 * Provides the base skeleton of what all music models will need.
 * This includes the ability to:
 *  - add Notes.
 *  - remove Notes.
 *  - add other pieces of music to be played simultaneously.
 *  - add other pieces of music to be played consecutively.
 *  - display the music's current state.
 */
public interface IMusicModel {

  /**
   * Adds a new note into the piece of music.
   * @param n The note being added.
   * @throws IllegalArgumentException   when the note give overlaps another
   *                                    note that already exists.
   */
  void addNote(Note n) throws IllegalArgumentException;

  /**
   * Remove the note from a piece of music.
   * @param n The note being removed.
   */
  void removeNote(Note n);

  /**
   * Adds all the notes in the other piece of music into this one so that the notes are playing
   * simultaneously.
   * @param m The other piece of music being added.
   */
  void addMusicSimul(IMusicModel m);

  /**
   * Adds all the notes in the other pieces of music into this one so that the new notes play
   * after this piece of music.
   * @param m he other piece of music being added.
   */
  void addMusicConsec(IMusicModel m);

  /**
   * Displays the music.
   * @return A string representing the music.
   */
  String displayMusic();

  /**
   * Gets this music piece's list of notes.
   * @return  This music model's list of notes.
   */
  List<Note> getNotes();

  /**
   * Gets the highest pitch.
   * @return  the highest pitch.
   */
  Note getHighestPitch();

  /**
   * Gets the lowest pitch.
   * @return  the lowest pitch.
   */
  Note getLowestPitch();

  /**
   * Gets the duration of the music.
   * @return  the duration.
   */
  int getDuration();

  /**
   * Get the sheet of music.
   * @return  the music.
   */
  TreeMap<Integer, List<Note>> getMusic();

  /**
   * Getter for the tempo of this Model.
   * @return the tempo (in microseconds).
   */
  int getTempo();

  /**
   * Sets the tempo of this piece.
   * @param tempo represents the new tempo of the piece (Measured in microseconds).
   */
  void setTempo(int tempo);

  /**
   * Retreives the list of beats being played at a specific beat.
   * @param beat  the beat that is being retrieved.
   * @return      the list of notes being played.
   */
  List<Note> retrieveNotesAtBeat(int beat);


  /**
   * Gets the current beat of the song.
   * @return an integer representing the current beat.
   */
  public int getCurrentBeat();


  /**
   * Sets the current beat of the song to a specified beat.
   * @param currentBeat an integer representing the new current beat.
   */
  public void setCurrentBeat(int currentBeat);


  /**
   * returns a boolean representing whether this model is currently paused.
   * @return a boolean representing whether this model is currently paused.
   */
  public boolean isPaused();


  /**
   * sets the model's paused status to a new boolean.
   * @param paused represents the new state in which the model is to be in. Paused/Unpaused.
   */
  public void setPaused(boolean paused);

  /**
   * Gets the note that is overlapping the given note.
   * @param n   Given Note
   * @return    The note that is overlapping
   */
  public Note getOverlappedNote(Note n);

  /**
   * Gets the currently selected note.
   * @return  the currently selected note
   */
  Note getSelectedNote();

  /**
   * Sets the currently selected note.
   */
  void setSelectedNote(Note n);

  /**
   * Gets the note to be added.
   * @return  the currently selected note
   */
  Note getNoteToBeAdded();

  /**
   * Sets the note to be added.
   */
  void setNoteToBeAdded(Note n);

  /**
   * Returns list of tone as strings.
   *
   * @param notes all the notes in the music.
   * @return list of tone as strings.
   */
  List<String> getTone(List<Note> notes);

}
