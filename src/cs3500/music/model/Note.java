package cs3500.music.model;
/**
 * Created by Oliver on 10/17/2016.
 */


/**
 * Represents a note in a piece of music.
 */
public class Note {
  private Pitch pitch;
  private int octave;
  private int end;
  private int start;
  private int volume;
  private int instrument;


  /**
   * Constructor for Note.
   *
   * @param pitch represents the pitch of the note.
   * @param octave represents the octave of the note.
   */
  public Note(Pitch pitch, int octave) {
    if (octave < 1 || octave > 10) {
      throw new IllegalArgumentException("Invalid Octave");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.instrument = 0;
    this.volume = 0;
    this.end = 0;
    this.start = 0;

  }

  /**
   * This creates a musical note given the pitch, whether it's a sharp, its octave,
   * and its start and end time.
   *
   * @param pitch  The pitch of a note, given as an enum Pitch
   * @param octave The octave the note is in (1-10)
   * @throws IllegalArgumentException If the octave given is not between 1 and 10 inclusive
   */
  public Note(Pitch pitch, int octave, int start, int end)
          throws IllegalArgumentException {
    if (octave < 1 || octave > 10) {
      throw new IllegalArgumentException("Invalid Octave");
    }
    if (end < start) {
      throw new IllegalArgumentException("The note cannot end before it starts");
    }
    this.pitch = pitch;
    this.octave = octave;
    this.instrument = 0;
    this.volume = 0;
    this.end = end;
    this.start = start;
  }

  /**
   * The Note Duration with additional instrument and volume values.
   *
   * @param pitch      Pitch of note
   * @param octave     The octave the note is in
   * @param start      Start of note
   * @param end        The end of note
   * @param instrument The instrument value
   * @param volume     Volume value
   */
  public Note(Pitch pitch, int octave, int start, int end, int instrument, int volume) {
    if (octave < 1 || octave > 10) {
      throw new IllegalArgumentException("Invalid Octave");
    }
    this.pitch = pitch;
    this.octave = octave;
    if (end < start) {
      throw new IllegalArgumentException("The note cannot end before it starts");
    }

    this.instrument = instrument;
    this.volume = volume;
    this.end = end;
    this.start = start;
  }

  /**
   * Gets the pitch of the note.
   *
   * @return the pitch of the note
   */
  public int getPitch() {
    return this.getNoteNumber();
  }

  /**
   * Returns the pitch.
   *
   * @return the pitch.
   */
  public Pitch getPitchLetter() {
    return this.pitch;
  }

  /**
   * Gets the note's octave.
   *
   * @return the octave.
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * Changes the pitch of the note.
   *
   * @param pitch The new pitch you want the note to be.
   */
  public void changePitch(Pitch pitch, int octave) {
    this.pitch = pitch;
    if (octave < 1 || octave > 10) {
      throw new IllegalArgumentException("Invalid Octave");
    }
    this.octave = octave;
  }

  /**
   * Compares the current note with the given Note.
   *
   * @param n The given note to compare this note to.
   * @return Return < 0 if this note is lower pitch, 0 if same, and > 0 if higher pitch
   */
  public int comparePitch(Note n) {
    int thisNoteRep = 0;
    int thatNoteRep = 0;

    thisNoteRep += this.octave * 12;
    switch (this.pitch) {
      case C:
        thisNoteRep += 1;
        break;
      case CSHARP:
        thisNoteRep += 2;
        break;
      case D:
        thisNoteRep += 3;
        break;
      case DSHARP:
        thisNoteRep += 4;
        break;
      case E:
        thisNoteRep += 5;
        break;
      case F:
        thisNoteRep += 6;
        break;
      case FSHARP:
        thisNoteRep += 7;
        break;
      case G:
        thisNoteRep += 8;
        break;
      case GSHARP:
        thisNoteRep += 9;
        break;
      case A:
        thisNoteRep += 10;
        break;
      case ASHARP:
        thisNoteRep += 11;
        break;
      case B:
        thisNoteRep += 12;
        break;
      default:
        throw new IllegalArgumentException("The given Note has no valid pitch.");
    }

    thatNoteRep += n.getOctave() * 12;
    switch (n.getPitchLetter()) {
      case C:
        thatNoteRep += 1;
        break;
      case CSHARP:
        thatNoteRep += 2;
        break;
      case D:
        thatNoteRep += 3;
        break;
      case DSHARP:
        thatNoteRep += 4;
        break;
      case E:
        thatNoteRep += 5;
        break;
      case F:
        thatNoteRep += 6;
        break;
      case FSHARP:
        thatNoteRep += 7;
        break;
      case G:
        thatNoteRep += 8;
        break;
      case GSHARP:
        thatNoteRep += 9;
        break;
      case A:
        thatNoteRep += 10;
        break;
      case ASHARP:
        thatNoteRep += 11;
        break;
      case B:
        thatNoteRep += 12;
        break;
      default:
        throw new IllegalArgumentException("The given Note has no valid pitch.");
    }


    return thisNoteRep - thatNoteRep;
  }

  /**
   * Gets the next note.
   *
   * @param n the current note
   * @return the next note.
   */
  public static Note nextNote(Note n) {
    Pitch p = null;
    int octave = n.getOctave();

    switch (n.getPitchLetter()) {
      case C:
        p = Pitch.CSHARP;
        break;
      case CSHARP:
        p = Pitch.D;
        break;
      case D:
        p = Pitch.DSHARP;
        break;
      case DSHARP:
        p = Pitch.E;
        break;
      case E:
        p = Pitch.F;
        break;
      case F:
        p = Pitch.FSHARP;
        break;
      case FSHARP:
        p = Pitch.G;
        break;
      case G:
        p = Pitch.GSHARP;
        break;
      case GSHARP:
        p = Pitch.A;
        break;
      case A:
        p = Pitch.ASHARP;
        break;
      case ASHARP:
        p = Pitch.B;
        break;
      case B:
        p = Pitch.C;
        octave++;
        break;
      default:
        break;
    }

    return new Note(p, octave);
  }

  /**
   * Gets the previous note.
   *
   * @param n the current note
   * @return the previous note.
   */
  public static Note prevNote(Note n) {
    Pitch p = null;
    int octave = n.getOctave();

    switch (n.getPitchLetter()) {
      case C:
        p = Pitch.B;
        octave--;
        break;
      case CSHARP:
        p = Pitch.C;
        break;
      case D:
        p = Pitch.CSHARP;
        break;
      case DSHARP:
        p = Pitch.D;
        break;
      case E:
        p = Pitch.DSHARP;
        break;
      case F:
        p = Pitch.E;
        break;
      case FSHARP:
        p = Pitch.F;
        break;
      case G:
        p = Pitch.FSHARP;
        break;
      case GSHARP:
        p = Pitch.G;
        break;
      case A:
        p = Pitch.GSHARP;
        break;
      case ASHARP:
        p = Pitch.A;
        break;
      case B:
        p = Pitch.ASHARP;
        break;
      default:
        break;
    }

    return new Note(p, octave);
  }

  @Override
  public String toString() {
    String ret = " ";

    switch (this.pitch) {
      case C:
        ret += " C";
        break;
      case CSHARP:
        ret += "C#";
        break;
      case D:
        ret += " D";
        break;
      case DSHARP:
        ret += "D#";
        break;
      case E:
        ret += " E";
        break;
      case F:
        ret += " F";
        break;
      case FSHARP:
        ret += "F#";
        break;
      case G:
        ret += " G";
        break;
      case GSHARP:
        ret += "G#";
        break;
      case A:
        ret += " A";
        break;
      case ASHARP:
        ret += "A#";
        break;
      case B:
        ret += " B";
        break;
      default:
        break;
    }
    ret += octave;
    if (octave < 10) {
      ret += " ";
    }
    return ret;
  }

  /**
   * Gets the MIDI Note Number Representation of this Note.
   *
   * @return the MIDI integer representation of this note.
   */
  public int getNoteNumber() {
    int thisNoteRep = 0;

    thisNoteRep += this.getOctave() * 12;

    switch (this.pitch) {
      case C:
        thisNoteRep += 0;
        break;
      case CSHARP:
        thisNoteRep += 1;
        break;
      case D:
        thisNoteRep += 2;
        break;
      case DSHARP:
        thisNoteRep += 3;
        break;
      case E:
        thisNoteRep += 4;
        break;
      case F:
        thisNoteRep += 5;
        break;
      case FSHARP:
        thisNoteRep += 6;
        break;
      case G:
        thisNoteRep += 7;
        break;
      case GSHARP:
        thisNoteRep += 8;
        break;
      case A:
        thisNoteRep += 9;
        break;
      case ASHARP:
        thisNoteRep += 10;
        break;
      case B:
        thisNoteRep += 11;
        break;
      default:
        throw new IllegalArgumentException("The given Note has no valid pitch.");
    }
    return thisNoteRep;
  }

  /**
   * Returns the string value of the note without space padding.
   *
   * @return The string value of note
   */
  public String pureToString() {
    String ret = "";

    switch (this.pitch) {
      case C:
        ret += "C";
        break;
      case CSHARP:
        ret += "C#";
        break;
      case D:
        ret += "D";
        break;
      case DSHARP:
        ret += "D#";
        break;
      case E:
        ret += "E";
        break;
      case F:
        ret += "F";
        break;
      case FSHARP:
        ret += "F#";
        break;
      case G:
        ret += "G";
        break;
      case GSHARP:
        ret += "G#";
        break;
      case A:
        ret += "A";
        break;
      case ASHARP:
        ret += "A#";
        break;
      case B:
        ret += "B";
        break;
      default:
        break;
    }
    ret += octave;

    return ret;
  }

  /**
   * Gets the note's duration which is inclusive of start and end point.
   *
   * @return the duration
   */
  public int getDuration() {
    return this.end - this.start + 1;
  }

  /**
   * Returns the starting point of the note.
   *
   * @return the start
   */
  public int getStart() {
    return this.start;
  }

  /**
   * Returns the end point of the note.
   *
   * @return the end
   */
  public int getEnd() {
    return this.end;
  }

  /**
   * Returns the instrument of the note.
   *
   * @return the instrument int value
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Returns the volume of the note.
   *
   * @return the volume int value
   */
  public int getVolume() {
    return this.volume;
  }

}
