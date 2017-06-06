package cs3500.music.view;

import cs3500.music.model.Note;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Console view2 of the Music Editor Model representation in a new window.
 * The new window consists of the music model ouput in a text-based way.
 */
public class ConsoleView implements IView {
  private Map<Integer, List<Note>> music; //a copy of music in the model.
  private final Appendable ap;
  private List<String> tone;
  private int length;

  /**
   * The constructor and initialization.
   */
  public ConsoleView(Appendable ap) {
    music = new HashMap<Integer, List<Note>>();
    this.ap = ap;
    tone = new ArrayList<String>();
    length = 0;
  }

  /**
   * Equate the global variable music with the music passing in.
   *
   * @param music Ther music passing in.
   */
  @Override
  public void setAllNotes(Map<Integer, List<Note>> music) {
    this.music = music;
  }

  @Override
  public void setLength(int length) {
    this.length = length;
  }

  @Override
  public void setTone(List<String> notes) {
    this.tone = notes;
  }

  @Override
  public void setTempo(int tempo) {
    return;
  }

  @Override
  public void showView() {
    String result = "";
    if (length != 0) {
      int wide = ("" + length).length(); //length of the beat number
      String top = topLine(tone, wide);//gets the top line

      //adds the top line
      result += top + "\n";

      List<Note> current = new ArrayList<Note>();
      for (int i = 0; i < length + 1; i++) {
        int numLength = (i + "").length();
        while (numLength != wide) { //fulls spaces before the beats number
          result += " ";
          numLength++;
        }
        result += "" + i; //Prints beats number

        if (music.get(i) != null) {
          current.addAll(music.get(i));
        }

        if (!current.isEmpty()) { //if we have note to print
          for (int j = 0; j < tone.size(); j++) {
            List<Note> sameTone = new ArrayList<Note>();
            for (int k = current.size() - 1; k >= 0; k--) {
              if (current.get(k).getEnd() <= i) {
                current.remove(k);
              } else if (current.get(k).toString().equals(tone.get(j))) {
                sameTone.add(current.get(k));
              }
            }

            if (sameTone.isEmpty()) {
              result += "     ";
            } else {
              boolean hasHeadNote = false;
              for (Note note : sameTone) {
                if (note.getStart() == i) {
                  hasHeadNote = true;
                  break;
                }
              }
              if (hasHeadNote) {
                result += "  x  ";
              } else {
                result += "  |  ";
              }
            }
          }
        }
        result += "\n";
      }
      write(result);
    }
  }

  @Override
  public void setCurrentBeat(int current) {
    // Don't need it.
  }


  /**
   * Generates the top line of the console view2.
   *
   * @param strings list of tone as strings.
   * @param i       length of the beat number.
   * @return the top line of the view2.
   */
  private String topLine(List<String> strings, int i) {
    String result = "";
    while (i != 0) { //fulls spaces in front of the first line
      result += " ";
      i--;
    }
    for (String s : strings) {
      result += getTopHelper(s); //adds spaces to each string
    }
    return result;
  }

  /**
   * Adds spaces according to the length of the tone.
   *
   * @param s the tone as a string.
   * @return the tone string with spaces.
   */
  private String getTopHelper(String s) {
    switch (s.length()) {
      case 2:
        return "  " + s + " ";
      case 3:
        return " " + s + " ";
      case 4:
        return " " + s;
      case 5:
        return s;
      default:
        throw new IllegalArgumentException("Invalid length.");
    }
  }

  /**
   * Appends the console view2 result.
   *
   * @param s the console view2 result.
   */
  private void write(String s) {
    try {
      ap.append(s);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
