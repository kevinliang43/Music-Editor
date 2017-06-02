package cs3500.music.view;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.util.MusicReader;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Oliver on 11/7/2016.
 */
public class ConsoleViewTest {
  @Test
  public void testMarryHadALittleLamb() {
    Readable in = new StringReader("");
    Appendable out = new StringBuilder();
    IMusicModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader(
              new File("mary-little-lamb.txt")), new MusicModel.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    //    MockConsoleView tester = new MockConsoleView(model, in, out);
    //    tester.displayView();

    assertEquals(
            "    E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   F5  F#5   " +
                    "G5 \n" +
                    " 1                 X                                            X  \n" +
                    " 2                 |                                            |  \n" +
                    " 3                 |                                  X  \n" +
                    " 4                 |                                  |  \n" +
                    " 5                 |                        X  \n" +
                    " 6                 |                        |  \n" +
                    " 7                 |                                  X  \n" +
                    " 8                                                    |  \n" +
                    " 9                 X                                            X  \n" +
                    "10                 |                                            |  \n" +
                    "11                 |                                            X  \n" +
                    "12                 |                                            |  \n" +
                    "13                 |                                            X  \n" +
                    "14                 |                                            |  \n" +
                    "15                 |                                            |  \n" +
                    "16\n" +
                    "17                 X                                  X  \n" +
                    "18                 |                                  |  \n" +
                    "19                 |                                  X  \n" +
                    "20                 |                                  |  \n" +
                    "21                 |                                  X  \n" +
                    "22                 |                                  |  \n" +
                    "23                 |                                  |  \n" +
                    "24                 |                                  |  \n" +
                    "25                 X                                            X  \n" +
                    "26                 |                                            |  \n" +
                    "27                                                                      " +
                    "       X  \n" +
                    "28                                                                        " +
                    "     |  \n" +
                    "29                                                                        " +
                    "     X  \n" +
                    "30                                                                        " +
                    "     |  \n" +
                    "31                                                                        " +
                    "     |  \n" +
                    "32                                                                        " +
                    "     |  \n" +
                    "33                 X                                            X  \n" +
                    "34                 |                                            |  \n" +
                    "35                 |                                  X  \n" +
                    "36                 |                                  |  \n" +
                    "37                 |                        X  \n" +
                    "38                 |                        |  \n" +
                    "39                 |                                  X  \n" +
                    "40                 |                                  |  \n" +
                    "41                 X                                            X  \n" +
                    "42                 |                                            |  \n" +
                    "43                 |                                            X  \n" +
                    "44                 |                                            |  \n" +
                    "45                 |                                            X  \n" +
                    "46                 |                                            |  \n" +
                    "47                 |                                            X  \n" +
                    "48                 |                                            |  \n" +
                    "49                 X                                  X  \n" +
                    "50                 |                                  |  \n" +
                    "51                 |                                  X  \n" +
                    "52                 |                                  |  \n" +
                    "53                 |                                            X  \n" +
                    "54                 |                                            |  \n" +
                    "55                 |                                  X  \n" +
                    "56                 |                                  |  \n" +
                    "57  X                                       X  \n" +
                    "58  |                                       |  \n" +
                    "59  |                                       |  \n" +
                    "60  |                                       |  \n" +
                    "61  |                                       |  \n" +
                    "62  |                                       |  \n" +
                    "63  |                                       |  \n" +
                    "64  |                                       |  ", out.toString());
  }

}