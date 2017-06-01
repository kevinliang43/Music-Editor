package cs3500.music.model;


/**
 * Created by Oliver on 10/21/2016.
 */
public class MusicModelTest {

//the refactoring of our NoteDuration class into one singular Note class made tests different.


//  //Tests the empty music
//  @Test
//  public void testEmptyMusic() {
//    IMusicModel model = new MusicModel();
//    assertEquals("", model.displayMusic());
//  }
//
//  //Tests the music being played in melody
//  @Test
//  public void testMelody() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.G, 5, 1, 2));
//    model.addNote(new NoteDuration(Pitch.GSHARP, 5, 3, 4));
//    model.addNote(new NoteDuration(Pitch.A, 5, 5, 6));
//    assertEquals("   G5  G#5   A5 \n" +
//            "1  X  \n" +
//            "2  |  \n" +
//            "3       X  \n" +
//            "4       |  \n" +
//            "5            X  \n" +
//            "6            |  ", model.displayMusic());
//  }
//
//  //Tests the exception thrown if note being added is overlapping.
//  @Test(expected = IllegalArgumentException.class)
//  public void testOverlappingAddNote() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.G, 5, 1, 21, 0, 0));
//    model.addNote(new NoteDuration(Pitch.G, 5, 7, 22, 0, 0));
//  }
//
//  //Tests the note is being added to the music correctly.
//  @Test
//  public void testAddNoteDisplay() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 6, 0, 0));
//    assertEquals(
//            "   A4 \n" +
//                    "1  X  \n" +
//                    "2  |  \n" +
//                    "3  |  \n" +
//                    "4  |  \n" +
//                    "5  |  \n" +
//                    "6  |  ",
//            model.displayMusic()
//
//    );
//  }
//
//  //Tests the note being added is also being added to the notes field.
//  @Test
//  public void testAddNoteNotes() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 6, 0, 0));
//    assertEquals(1, model.getNotes().size());
//  }
//
//  //Tests the addNote method is expanding parameters properly.
//  @Test
//  public void testAddNoteParamExpansion() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 7, 0, 0));
//    model.addNote(new NoteDuration(Pitch.D, 5, 4, 10, 0, 0));
//    assertEquals("    A4  A#4   B4   C5  C#5   D5 \n" +
//                    " 1  X  \n" +
//                    " 2  |  \n" +
//                    " 3  |  \n" +
//                    " 4  |                        X  \n" +
//                    " 5  |                        |  \n" +
//                    " 6  |                        |  \n" +
//                    " 7  |                        |  \n" +
//                    " 8                           |  \n" +
//                    " 9                           |  \n" +
//                    "10                           |  ",
//            model.displayMusic()
//    );
//  }
//
//  //Tests adding music to another piece of music simultaneously.
//  @Test
//  public void testAddMusicSimul() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 7, 0, 0));
//    IMusicModel model2 = new MusicModel();
//    model2.addNote(new NoteDuration(Pitch.D, 5, 4, 10, 0, 0));
//    model2.addNote(new NoteDuration(Pitch.CSHARP, 5, 4, 6, 0, 0));
//    model.addMusicSimul(model2);
//    assertEquals("    A4  A#4   B4   C5  C#5   D5 \n" +
//                    " 1  X  \n" +
//                    " 2  |  \n" +
//                    " 3  |  \n" +
//                    " 4  |                   X    X  \n" +
//                    " 5  |                   |    |  \n" +
//                    " 6  |                   |    |  \n" +
//                    " 7  |                        |  \n" +
//                    " 8                           |  \n" +
//                    " 9                           |  \n" +
//                    "10                           |  ",
//            model.displayMusic()
//    );
//  }
//
//  //Tests adding music to another piece of music simultaneously with overlapping notes.
//  @Test
//  public void testAddMusicSimulWithOverlapping() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 7, 0, 0));
//    IMusicModel model2 = new MusicModel();
//    model2.addNote(new NoteDuration(Pitch.D, 5, 4, 10, 0, 0));
//    model2.addNote(new NoteDuration(Pitch.A, 4, 6, 8, 0, 0));
//    model.addMusicSimul(model2);
//    assertEquals("    A4  A#4   B4   C5  C#5   D5 \n" +
//                    " 1  X  \n" +
//                    " 2  |  \n" +
//                    " 3  |  \n" +
//                    " 4  |                        X  \n" +
//                    " 5  |                        |  \n" +
//                    " 6  |                        |  \n" +
//                    " 7  |                        |  \n" +
//                    " 8                           |  \n" +
//                    " 9                           |  \n" +
//                    "10                           |  ",
//            model.displayMusic()
//    );
//  }
//
//  //Tests adding music that has same parameters to the
//  @Test
//  public void testAddMusicConsec() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 7, 0, 0));
//    IMusicModel model2 = new MusicModel();
//    model2.addNote(new NoteDuration(Pitch.A, 4, 6, 8, 0, 0));
//    model.addMusicConsec(model2);
//    assertEquals("    A4 \n" +
//                    " 1  X  \n" +
//                    " 2  |  \n" +
//                    " 3  |  \n" +
//                    " 4  |  \n" +
//                    " 5  |  \n" +
//                    " 6  |  \n" +
//                    " 7  |  \n" +
//                    " 8\n" +
//                    " 9\n" +
//                    "10\n" +
//                    "11\n" +
//                    "12\n" +
//                    "13  X  \n" +
//                    "14  |  \n" +
//                    "15  |  ",
//            model.displayMusic()
//    );
//  }
//
//  //Tests adding music consecutively and expaning parameters.
//  @Test
//  public void testAddMusicExpand() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 7, 0, 0));
//    IMusicModel model2 = new MusicModel();
//    model2.addNote(new NoteDuration(Pitch.C, 5, 6, 8, 0, 0));
//    model.addMusicConsec(model2);
//    assertEquals("    A4  A#4   B4   C5 \n" +
//                    " 1  X  \n" +
//                    " 2  |  \n" +
//                    " 3  |  \n" +
//                    " 4  |  \n" +
//                    " 5  |  \n" +
//                    " 6  |  \n" +
//                    " 7  |  \n" +
//                    " 8\n" +
//                    " 9\n" +
//                    "10\n" +
//                    "11\n" +
//                    "12\n" +
//                    "13                 X  \n" +
//                    "14                 |  \n" +
//                    "15                 |  ",
//            model.displayMusic()
//    );
//  }
//
//  //Tests the getNotes method.
//  @Test
//  public void testGetNotes() {
//    IMusicModel model = new MusicModel();
//    model.addNote(new NoteDuration(Pitch.A, 4, 1, 6, 0, 0));
//    model.addNote(new NoteDuration(Pitch.C, 5, 5, 7, 0, 0));
//    assertEquals(2, model.getNotes().size());
//  }
//
//  //Tests the removal of Notes.
//  @Test
//  public void testNoteRemoveMiddle() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7, 0, 0));
//    m.addNote(new NoteDuration(Pitch.F, 3, 6, 8, 0, 0));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 4, 10, 0, 0));
//    m.removeNote(new NoteDuration(Pitch.F, 3, 6, 8, 0, 0));
//    assertEquals(
//            "    E3   F3  F#3 \n" +
//            " 1  X  \n" +
//            " 2  |  \n" +
//            " 3  |  \n" +
//            " 4  |         X  \n" +
//            " 5  |         |  \n" +
//            " 6  |         |  \n" +
//            " 7  |         |  \n" +
//            " 8            |  \n" +
//            " 9            |  \n" +
//            "10            |  ", m.displayMusic());
//  }
//
//  //Tests the removal of lowest Notes.
//  @Test
//  public void testNoteRemoveLowest() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7, 0, 0));
//    m.addNote(new NoteDuration(Pitch.F, 3, 6, 8, 0, 0));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 4, 10, 0, 0));
//    m.removeNote(new NoteDuration(Pitch.E, 3, 1, 7, 0, 0));
//    assertEquals("    F3  F#3 \n" +
//                    " 1\n" +
//                    " 2\n" +
//                    " 3\n" +
//                    " 4       X  \n" +
//                    " 5       |  \n" +
//                    " 6  X    |  \n" +
//                    " 7  |    |  \n" +
//                    " 8  |    |  \n" +
//                    " 9       |  \n" +
//                    "10       |  ", m.displayMusic());
//  }
//
//  //Tests the removal of lowest Notes.
//  @Test
//  public void testNoteRemoveHighest() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7, 0, 0));
//    m.addNote(new NoteDuration(Pitch.F, 3, 6, 8, 0, 0));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 4, 10, 0, 0));
//    m.removeNote(new NoteDuration(Pitch.FSHARP, 3, 4, 10, 0, 0));
//    assertEquals("    E3   F3 \n" +
//            " 1  X  \n" +
//            " 2  |  \n" +
//            " 3  |  \n" +
//            " 4  |  \n" +
//            " 5  |  \n" +
//            " 6  |    X  \n" +
//            " 7  |    |  \n" +
//            " 8       |  ", m.displayMusic());
//  }
//
//  //Tests the removal of lowest Notes but not the only lowest note.
//  @Test
//  public void testNoteRemoveLowestNotOnly() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7));
//    m.addNote(new NoteDuration(Pitch.E, 3, 9, 13));
//    m.addNote(new NoteDuration(Pitch.F, 3, 6, 8));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 4, 10));
//    m.removeNote(new NoteDuration(Pitch.E, 3, 9, 13));
//    assertEquals("    E3   F3  F#3 \n" +
//            " 1  X  \n" +
//            " 2  |  \n" +
//            " 3  |  \n" +
//            " 4  |         X  \n" +
//            " 5  |         |  \n" +
//            " 6  |    X    |  \n" +
//            " 7  |    |    |  \n" +
//            " 8       |    |  \n" +
//            " 9            |  \n" +
//            "10            |  ", m.displayMusic());
//  }
//
//  //Tests the removal of lowest Notes but not the only lowest note.
//  @Test
//  public void testNoteRemoveHighestNotOnly() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 6));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 1, 2));
//    m.addNote(new NoteDuration(Pitch.F, 3, 5, 7));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 3, 9));
//    m.removeNote(new NoteDuration(Pitch.FSHARP, 3, 1, 2));
//    assertEquals("   E3   F3  F#3 \n" +
//            "1  X  \n" +
//            "2  |  \n" +
//            "3  |         X  \n" +
//            "4  |         |  \n" +
//            "5  |    X    |  \n" +
//            "6  |    |    |  \n" +
//            "7       |    |  \n" +
//            "8            |  \n" +
//            "9            |  ", m.displayMusic());
//  }
//
//  //Tests removing the only Note and then readding.
//  @Test
//  public void testRemoveOnlyNote() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7));
//    m.removeNote(new NoteDuration(Pitch.E, 3, 1, 7));
//    m.addNote(new NoteDuration(Pitch.A, 4, 1, 7));
//    assertEquals( "   A4 \n" +
//            "1  X  \n" +
//            "2  |  \n" +
//            "3  |  \n" +
//            "4  |  \n" +
//            "5  |  \n" +
//            "6  |  \n" +
//            "7  |  ", m.displayMusic());
//  }
//
//  //Tests removing a note that isn't in the music
//  @Test
//  public void testRemoveNothing() {
//    MusicModel m = new MusicModel();
//    m.addNote(new NoteDuration(Pitch.E, 3, 1, 7));
//    m.addNote(new NoteDuration(Pitch.FSHARP, 3, 2, 3));
//    m.addNote(new NoteDuration(Pitch.F, 3, 6, 8));
//    m.removeNote(new NoteDuration(Pitch.FSHARP, 3, 1, 2));
//    assertEquals("   E3   F3  F#3 \n" +
//            "1  X  \n" +
//            "2  |         X  \n" +
//            "3  |         |  \n" +
//            "4  |  \n" +
//            "5  |  \n" +
//            "6  |    X  \n" +
//            "7  |    |  \n" +
//            "8       |  ", m.displayMusic());
//  }
}