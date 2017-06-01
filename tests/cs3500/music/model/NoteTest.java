package cs3500.music.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 10/17/2016.
 */
public class NoteTest {


  //Tests whether the Note constructor throws an IllegalArgumentException if
  // it is passed an octave > 10
  @Test(expected = IllegalArgumentException.class)
  public void invalidNoteConstructorAboveTen() {
    Note note = new Note(Pitch.C, 11);
  }

  //Tests whether the Note constructor throws an IllegalArgumentException if
  // it is passed an octave < 1.
  @Test(expected = IllegalArgumentException.class)
  public void invalidNoteConstructorLessOne() {
    Note note = new Note(Pitch.C, 0);
  }

  //Tests whether the changePitch method throws an IllegalArgumentException if
  // it is passed an octave < 1.
  @Test(expected = IllegalArgumentException.class)
  public void invalidPitchAboveTen() {
    Note note = new Note(Pitch.C, 7);
    note.changePitch(Pitch.E, 11);

  }

  //Tests whether the changePitch method throws an IllegalArgumentException if
  // it is passed an octave < 1.
  @Test(expected = IllegalArgumentException.class)
  public void invalidPitchLessOne() {
    Note note = new Note(Pitch.C, 7);
    note.changePitch(Pitch.E, 0);

  }

  //Tests if the getPitch method works.
  @Test
  public void testGetPitch() {
    Note newNote = new Note(Pitch.A, 7);
    assertEquals(Pitch.A, newNote.getPitch());
  }

  //Tests if the getOctave method works.
  @Test
  public void testGetOctave() {
    Note newNote = new Note(Pitch.A, 8);
    assertEquals(8, newNote.getOctave());
  }

  //  //Tests if the getDuration method works.
  //  @Test
  //  public void testGetDuration() {
  //    NoteDuration newNote = new NoteDuration(Pitch.A, 8, 1, 7, 0, 0);
  //    assertEquals(7, newNote.getDuration());
  //  }
  //
  //  //Tests the changePitch method.
  //  @Test
  //  public void testChangePitch() {
  //    Note newNote = new Note(Pitch.A, 8);
  //    newNote.changePitch(Pitch.D, 7);
  //    assertEquals(true, newNote.getPitch() == Pitch.D && newNote.getOctave() == 7);
  //  }


  //Tests the comparePitch method for just the note being different.
  @Test
  public void testComparePitchNote() {
    Note note1 = new Note(Pitch.CSHARP, 8);
    Note note2 = new Note(Pitch.D, 8);
    assertEquals(true, note1.comparePitch(note2) < 0);
  }

  //Tests the comparePitch method for just the note being different.
  @Test
  public void testComparePitchOctave() {
    Note note1 = new Note(Pitch.A, 9);
    Note note2 = new Note(Pitch.A, 8);
    assertEquals(true, note1.comparePitch(note2) > 0);
  }

  //Tests the comparePitch method for same pitch note.
  @Test
  public void testComparePitchSame() {
    Note note1 = new Note(Pitch.A, 9);
    Note note2 = new Note(Pitch.A, 9);
    assertEquals(0, note1.comparePitch(note2));
  }

  //Tests the comparePitch method at the octave break.
  @Test
  public void testComparePitchOctaveBreak() {
    Note note1 = new Note(Pitch.B, 9);
    Note note2 = new Note(Pitch.C, 10);
    assertEquals(true, note1.comparePitch(note2) < 0);
  }

  //Tests the exact return of comparePitch.
  @Test
  public void testComparePitchExact() {
    Note note1 = new Note(Pitch.CSHARP, 5);
    Note note2 = new Note(Pitch.E, 5);
    assertEquals(-3, note1.comparePitch(note2));
  }

  //Tests the exact return of comparePitch.
  @Test
  public void testComparePitchBetweenOctave() {
    Note note1 = new Note(Pitch.CSHARP, 5);
    Note note2 = new Note(Pitch.E, 6);
    assertEquals(-15, note1.comparePitch(note2));
  }


  //  //Checks the isStrike method of the notes for Note.
  //  @Test
  //  public void testIsStrikeNote() {
  //    Note note1 = new Note(Pitch.C, 5);
  //    assertEquals(false, note1.isStrike());
  //  }
  //
  //  //Checks the isStrike method for NoteDuration.
  //  @Test
  //  public void testIsStrikeNoteDuration() {
  //    Note note = new NoteDuration(Pitch.ASHARP, 6, 4, 7, 0, 0);
  //    assertEquals(false, note.isStrike());
  //  }

  //Checks the nextPitch static method.
  @Test
  public void testNextPitchSameOctave() {
    assertEquals(true, new Note(Pitch.FSHARP, 6).comparePitch(Note.nextNote(new Note(Pitch.F, 6)))
            == 0);
  }

  //Checks the nextPitch going between octaves.
  @Test
  public void testNextPitchNextOctave() {
    assertEquals(true, new Note(Pitch.C, 7).comparePitch(Note.nextNote(new Note(Pitch.B, 6)))
            == 0);
  }

  //Checks the exception thrown when the next note is off the hearing scale.
  @Test(expected = IllegalArgumentException.class)
  public void testNextOctaveInvalid() {
    Note.nextNote(new Note(Pitch.B, 10));
  }

  //Checks the toString method of notes.
  @Test
  public void testToString() {
    Note note = new Note(Pitch.C, 6);
    assertEquals("  C6 ", note.toString());
  }

  //Checks the toString method of notes.
  @Test
  public void testToStringSharp() {
    Note note = new Note(Pitch.GSHARP, 4);
    assertEquals(" G#4 ", note.toString());
  }

  //Checks the toString method of notes.
  @Test
  public void testToStringOctTen() {
    Note note = new Note(Pitch.G, 10);
    assertEquals("  G10", note.toString());
  }

  //Checks the toString method of notes.
  @Test
  public void testToStringOctTenAndSharp() {
    Note note = new Note(Pitch.GSHARP, 10);
    assertEquals(" G#10", note.toString());
  }

  //  //Checks the output of NoteStrike.
  //  @Test
  //  public void testToStringNoteStrike() {
  //    Note note = new NoteStrike(Pitch.GSHARP, 10, 5);
  //    assertEquals("  X  ", note.toString());
  //  }
  //
  //  //Checks the output of NoteHold.
  //  @Test
  //  public void testToStringNoteHold() {
  //    Note note = new NoteHold(Pitch.GSHARP, 10, 5);
  //    assertEquals("  |  ", note.toString());
  //  }

  //Tests the previous note method
  @Test
  public void testPrevNote() {
    Note n = new Note(Pitch.G, 1);
    Note result = new Note(Pitch.FSHARP, 1);
    assertEquals(0, result.comparePitch(Note.prevNote(n)));
  }

  //Tests for improper index of prevNote
  @Test(expected = IllegalArgumentException.class)
  public void testPrevNoteFail() {
    Note n = new Note(Pitch.C, 1);
    Note.prevNote(n);
  }


}