package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by Oliver on 10/17/2016.
 */

/**
 * This model is used to represent the music that is being created.
 * This model keeps the lowest and highest pitches stored as well as the length of the music to
 * maintain parameters.
 * The music is represented by a list of chords.
 * The chord is represented by a list of notes.
 * A null note represents that in the current chord, the pitch isn't being played.
 * <p>
 * The model consists of certain fields:
 * - lowestPitch   the lowest note in the current sheet of music
 * - highestPitch  the highest Pitch in the current sheet of music
 * - length        the length of the piece of music
 * - music         An two dimensional List that represents the current state of the music
 * The outer layer of the List represents the beat count and the inner List
 * represents the chord being played at that given time.
 * The notes in the chord are represented by Notes, specifically NoteStrikes and
 * NoteHolds that represent wether the note is being stricken or held respectively.
 * - notes         A list of the current notes that are in the current piece of music. These notes
 * are specifically NoteDurations which represent a note with a start location
 * and end location.
 * </p>
 */
public class MusicModel implements IMusicModel {
  private Note lowestPitch;
  private Note highestPitch;
  private int length;
  private List<Note> notes;
  // 1 second per beat or 1 million microseconds per beat
  private int tempo = 1000000;
  private int currentBeat = 0;
  private boolean paused = false;
  TreeMap<Integer, List<Note>> music;
  private Note selectedNote = null;
  private Note noteToBeAdded = null;

  /**
   * Creates the base music model.
   * There are no notes, so the highest pitch and lowest pitch don't exist and music/notes
   * are empty.
   * Also the length is 0 because no actual notes yet.
   */
  public MusicModel() {
    this.lowestPitch = null;
    this.highestPitch = null;
    this.music = new TreeMap<Integer, List<Note>>();
    this.notes = new ArrayList<Note>();
    this.length = 0;
  }

  /**
   * Returns the note being selected for deletion.
   *
   * @return the current NoteDuration to be deleted.
   */
  @Override
  public Note getSelectedNote() {
    return this.selectedNote;
  }

  /**
   * Updates the note being selected for deletion to a new note.
   *
   * @param n represents the new note being selected for Deletion.
   */
  @Override
  public void setSelectedNote(Note n) {
    this.selectedNote = n;
  }

  /**
   * Returns the note to be added.
   *
   * @return the NoteDuration to be added to the model.
   */
  @Override
  public Note getNoteToBeAdded() {
    return this.noteToBeAdded;
  }

  /**
   * Sets the note to be added.
   *
   * @param n represents the new note to be added to the model.
   */
  @Override
  public void setNoteToBeAdded(Note n) {
    this.noteToBeAdded = n;
  }

  /**
   * Adds a note to the music.
   *
   * @param n The note being added.
   * @throws IllegalArgumentException cannot add a note if it overlaps with another note.
   */
  @Override
  public void addNote(Note n) throws IllegalArgumentException {

    if (this.isOverlappingMusic(n)) {
      throw new IllegalArgumentException("The note is overlapping a note that already exists");
    }


    if (lowestPitch == null && highestPitch == null) {
      this.lowestPitch = n;
      this.highestPitch = n;
    }

    expandIfNecessary(n);

    int endOfNote = n.getEnd();

    if (endOfNote > this.length) {
      expandDuration(endOfNote - this.length);
    }

    //adds note to the list of notes
    this.notes.add(n);


    //adds the note to the current state of the music
    addToSheetMusic(n);


  }

  /**
   * Adds NoteStrikes and NoteHolds to the music state accordingly based on the given note.
   *
   * @param n The given note.
   */
  private void addToSheetMusic(Note n) {
    addSorted(
            music.get(n.getStart()),
            new Note(n.getPitchLetter(),
                    n.getOctave(),
                    n.getStart(), n.getEnd(), n.getInstrument(), n.getVolume()));

  }

  /**
   * Adds the note into the list of notes sorted.
   *
   * @param notes The list of notes.
   * @param n     The note being added.
   */
  private void addSorted(List<Note> notes, Note n) {
    for (int i = 0; i < notes.size(); i++) {
      if (n.comparePitch(notes.get(i)) < 0) {
        notes.add(i, n);
        break;
      }
    }
    if (!notes.contains(n)) {
      notes.add(n);
    }
  }

  /**
   * Checks if the note is outside the current parameters and changes the parameters accordingly.
   *
   * @param n The note being checked.
   */
  private void expandIfNecessary(Note n) {
    int distanceFromHighest = n.comparePitch(this.highestPitch);
    if (distanceFromHighest > 0) {
      this.highestPitch = n;
    }

    int distanceFromLowest = n.comparePitch(this.lowestPitch);
    if (distanceFromLowest < 0) {
      this.lowestPitch = n;
    }
  }


  /**
   * Expand the current length of the music by a certain amount.
   *
   * @param amount the amount the music needs to be expanded by
   */
  public void expandDuration(int amount) {
    for (int i = 0; i < amount; i++) {

      this.music.put(length + i, new ArrayList<Note>());
    }
    this.length += amount;
  }

  /**
   * Checks if a certain note is overlapping a note existing in this piece of music.
   *
   * @param n the note being checked
   * @return whether the note is overlapping an existing note
   */
  protected boolean isOverlappingMusic(Note n) {
    boolean ret = false;
    for (Note note : this.notes) {
      if (n.comparePitch(note) == 0) {
        ret = (n.getStart() >= note.getStart() && n.getStart() < note.getEnd())
                || (n.getEnd() > note.getStart() && n.getEnd() <= note.getEnd()
                || n.getStart() <= note.getStart() && n.getEnd() >= note.getEnd());
        if (ret) {
          return ret;
        }
      }
    }
    return ret;
  }

  /**
   * Returns the note being overlapped by the given note.
   *
   * @param n Given Note.
   * @return the note within the model that is overlapped by the given one.
   */
  @Override
  public Note getOverlappedNote(Note n) {
    boolean ret = false;
    for (Note note : this.notes) {
      if (n.comparePitch(note) == 0) {
        ret = (n.getStart() > note.getStart() && n.getStart() < note.getEnd())
                || (n.getEnd() > note.getStart() && n.getEnd() < note.getEnd());
        if (ret) {
          return note;
        }
      }
    }
    return null;
  }

  /**
   * Removes a note from the model.
   *
   * @param n The note being removed.
   * @throws IllegalArgumentException cannot remove a note that doesnt exist.
   */
  @Override
  public void removeNote(Note n) throws IllegalArgumentException {
    for (Note note : notes) {
      if (n.comparePitch(note) == 0 && n.getStart() == note.getStart()
              && n.getEnd() == note.getEnd()) {
        notes.remove(note);
        for (int i = n.getStart(); i < n.getEnd(); i++) {
          for (Note musicNote : music.get(i)) {
            if (musicNote.comparePitch(n) == 0) {
              music.get(i).remove(musicNote);
              break;
            }
          }
        }
        break;
      }
    }
    this.reformat();

    // removes the note from the treeMap
    if (this.music.containsKey(n.getStart())) {
      if (this.music.get(n.getStart()).contains(n)) {
        this.music.get(n.getStart()).remove(n);
      }
    }

  }


  /**
   * Reformats the size of the music to fit the new parameters after removal.
   */
  public void reformat() {

    //Means the only note in music was removed
    if (notes.size() == 0) {
      this.highestPitch = null;
      this.lowestPitch = null;
      this.music = new TreeMap<Integer, List<Note>>();
      this.notes = new ArrayList<Note>();
      this.length = 0;
      return;
    }

    setNewLowestPitch();
    setNewHighestPitch();

    int lastBeat = 0;
    for (Note n : notes) {
      if (n.getEnd() > lastBeat) {
        lastBeat = n.getEnd();
      }
    }
    this.removeBeats(this.length - lastBeat);


  }

  /**
   * Removes beats from the music.
   *
   * @param amount represents the amount of beats to remove.
   */

  public void removeBeats(int amount) {
    int indx = this.length - 1;
    for (int i = 0; i < amount; i++) {
      this.music.remove(indx);
      indx--;
      this.length--;
    }
  }

  /**
   * Resets the new lowest pitch.
   */
  private void setNewLowestPitch() {
    Note newLowestPitch = new Note(Pitch.B, 10);
    for (int i : music.keySet()) {
      for (Note n : music.get(i)) {
        if (n.comparePitch(newLowestPitch) < 0) {
          newLowestPitch = new Note(n.getPitchLetter(), n.getOctave());
        }
      }
    }
    this.lowestPitch = newLowestPitch;
  }

  /**
   * Sets the highest Pitch.
   */
  private void setNewHighestPitch() {
    Note newHighestPitch = new Note(Pitch.C, 1);
    for (int i : music.keySet()) {
      for (Note n : music.get(i)) {
        if (n.comparePitch(newHighestPitch) > 0) {
          newHighestPitch = new Note(n.getPitchLetter(), n.getOctave());
        }
      }
    }
    this.highestPitch = newHighestPitch;
  }

  /**
   * Adds two pieces of music togther, consecutively.
   *
   * @param m the other piece of music being added.
   */
  @Override
  public void addMusicConsec(IMusicModel m) {
    for (Note n : m.getNotes()) {
      this.addNote(new Note(n.getPitchLetter(), n.getOctave(),
              n.getStart() + this.length, n.getEnd() + this.length,
              n.getInstrument(), n.getVolume()));
    }
  }

  /**
   * adds two pieces of music simultaneously playing.
   *
   * @param m The other piece of music being added.
   */
  @Override
  public void addMusicSimul(IMusicModel m) {
    for (Note n : m.getNotes()) {
      try {
        this.addNote(n);
      } catch (IllegalArgumentException ie) {
        //just dont add the note if there is an exception.
      }
    }
  }


  /**
   * updates the treemap of noteduration.
   *
   * @param note the new note being added to the treemap.
   */
  public void updateMusic(Note note) {

    if (music.keySet().contains(note.getStart())) {
      music.get(note.getStart()).add(note);
    }
    // key doesnt exist, add a new list
    else {
      List<Note> newList = new ArrayList<Note>();
      newList.add(note);
      music.put(note.getStart(), newList);
    }
  }


  /**
   * getter for the notes.
   *
   * @return the list of note durations from this model.
   */
  @Override
  public List<Note> getNotes() {
    return this.notes;
  }

  /**
   * the getter for the highest note of this music sheet.
   *
   * @return the Note with the highest pitch.
   */
  @Override
  public Note getHighestPitch() {
    return this.highestPitch;
  }

  /**
   * the getter for the lowest note of this music sheet.
   *
   * @return the Note with the lowest pitch.
   */
  @Override
  public Note getLowestPitch() {
    return this.lowestPitch;
  }

  /**
   * the getter for the duration of this model.
   *
   * @return an integer represeting the length of the music.
   */
  @Override
  public int getDuration() {
    return this.length;
  }

  /**
   * returns the treemap organization of the notes in this model.
   *
   * @return the treemap organization of the notes in this model.
   */
  @Override
  public TreeMap<Integer, List<Note>> getMusic() {
    return this.music;
  }

  /**
   * Displays the music.
   *
   * @return String value of the music.
   */
  @Override
  public String displayMusic() {
    if (this.lowestPitch == null && this.highestPitch == null && length == 0) {
      return "";
    }
    String ret = "";
    int lengthDigitTest = this.length;
    int lengthDigit = 0;

    while (lengthDigitTest > 0) {
      lengthDigitTest /= 10;
      lengthDigit++;

    }

    for (int i = 0; i < lengthDigit; i++) {
      ret += " ";
    }

    //Displays all pitches
    Note currentNoteOutput = this.lowestPitch;
    while (currentNoteOutput.comparePitch(this.highestPitch) <= 0) {
      ret += currentNoteOutput.toString();
      currentNoteOutput = Note.nextNote(currentNoteOutput);
    }
    ret += "\n";

    //Displays the music
    int beat = 1;
    for (List<Note> beats : music.values()) {
      ret += displayLine(beat, beats, lengthDigit);
      ret += "\n";
      beat++;
    }

    ret = ret.substring(0, ret.length() - 1);
    return ret;
  }

  /**
   * Returns the string value of a certain beat in the music.
   *
   * @param beat        The beat that is being converted to a String
   * @param notes       The list of notes that are being converted
   * @param lengthDigit The amount of digits there are in the max length of duration
   * @return The String value of the line
   */
  public String displayLine(int beat, List<Note> notes, int lengthDigit) {
    String ret = "";
    int tempBeat = beat;
    int count = 0;
    while (tempBeat > 0) {
      tempBeat /= 10;
      count++;
    }
    for (int i = 0; i < lengthDigit - count; i++) {
      ret += " ";
    }
    ret += beat;

    Note prevNote = this.lowestPitch;

    boolean addSpace = true;

    for (Note n : notes) {
      int howFar = n.comparePitch(prevNote);
      if (howFar == 0) {
        addSpace = false;
      }
      if (addSpace) {
        ret += "     ";
      }
      for (int i = 0; i < howFar - 1; i++) {
        ret += "     ";
      }
      ret += n.toString();

      prevNote = n;
      addSpace = false;
    }
    return ret;
  }


  /**
   * Getter for the tempo of this Model.
   *
   * @return the tempo (in microseconds).
   */
  public int getTempo() {
    return tempo;
  }

  /**
   * Sets the tempo of this piece.
   *
   * @param tempo represents the new tempo of the piece (Measured in microseconds).
   */
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * returns the notes at the given beat.
   *
   * @param beat the beat of the notes that are being retrieved.
   * @return list of notes at the current beat.
   */
  @Override
  public List<Note> retrieveNotesAtBeat(int beat) {
    return this.music.get(beat);
  }

  /**
   * represents a builder class for MusicModel class.
   */
  public static final class Builder implements CompositionBuilder<IMusicModel> {

    private MusicModel model;

    public Builder() {
      this.model = new MusicModel();
    }

    /**
     * Constructs an actual composition, given the notes that have been added.
     *
     * @return The new composition
     */
    @Override
    public IMusicModel build() {
      return this.model;
    }

    /**
     * Sets the tempo of the piece.
     *
     * @param tempo represents the new tempo of the piece (in microseconds).
     * @return The Builder (this).
     */
    @Override
    public CompositionBuilder<IMusicModel> setTempo(int tempo) {
      this.model.setTempo(tempo);
      return this;
    }


    /**
     * Adds a new given note to this piece.
     *
     * @param start      The start beat of this note.
     * @param end        The end beat of this note.
     * @param instrument The MIDI Number representation of an instrument.
     * @param pitch      The pitch of the note. In MIDI, 60 is represented as Middle C (C4).
     * @param volume     The volume of the note. The higher the integer, the higher the volume.
     * @return The builder (this), with a new note added to it.
     */
    @Override
    public CompositionBuilder<IMusicModel> addNote(int start, int end, int instrument,
                                                   int pitch, int volume) {
      Note newNote = new Note(Pitch.intToPitch(pitch), pitch / 12, start, end,
              instrument, volume);
      try {
        this.model.addNote(newNote);
      } catch (IllegalArgumentException ie) {
        //just don't add note if it is overlapping
      }
      return this;
    }


  }

  /**
   * gets the current beat of the model.
   *
   * @return integer representing the current beat of the model.
   */
  public int getCurrentBeat() {
    return currentBeat;
  }

  /**
   * sets the current beat.
   *
   * @param currentBeat an integer representing the new current beat.
   */
  public void setCurrentBeat(int currentBeat) {

    if (currentBeat <= this.getDuration() && currentBeat >= 0) {
      this.currentBeat = currentBeat;
    }
  }

  /**
   * returns the boolean representing whether or not the model is currentl paused.
   *
   * @return boolean representing whether or not the model is currentl paused.
   */
  public boolean isPaused() {
    return paused;
  }


  /**
   * sets the paused value of the model.
   *
   * @param paused represents the new state in which the model is to be in. Paused/Unpaused.
   */
  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  @Override
  public List<String> getTone(List<Note> notes) {
    ArrayList<String> tones = new ArrayList<String>();
    //Reversed, find out why
    Note lowest = new Note(this.lowestPitch.getPitchLetter(), this.lowestPitch.getOctave());
    Note highest = new Note(this.highestPitch.getPitchLetter(), this.highestPitch.getOctave());
    while (lowest.comparePitch(highest) <= 0) {
      tones.add(lowest.pureToString());
      lowest = lowest.nextNote(lowest);
    }
    return tones;

  }
}

