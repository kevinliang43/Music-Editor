package cs3500.music.model;

import java.util.HashMap;
import java.util.Map;

/**
 * To convert pitch in numbers to string.
 */
public class PitchToString {
  private Map<Integer, String> map;

  /**
   * Converts a pitch number to string.
   */
  public PitchToString() {
    map = new HashMap<>();
    int index = 0;
    for (int i = 0; i < 11; i++) {
      map.put(index, "C" + i);
      index++;
      map.put(index, "C#" + i);
      index++;
      map.put(index, "D" + i);
      index++;
      map.put(index, "D#" + i);
      index++;
      map.put(index, "E" + i);
      index++;
      map.put(index, "F" + i);
      index++;
      map.put(index, "F#" + i);
      index++;
      map.put(index, "G" + i);
      index++;
      map.put(index, "G#" + i);
      index++;
      map.put(index, "A" + i);
      index++;
      map.put(index, "A#" + i);
      index++;
      map.put(index, "B" + i);
      index++;
    }
  }

  /**
   * get the letter name of the pitch.
   *
   * @return letter name
   */
  public String getI(int i) {
    return map.get(i);
  }

  /**
   * Gets the integer that represents the key.
   *
   * @param s The string representation of a tone.
   * @return The corresponding integer value of the key.
   */
  public int getKey(String s) {
    for (int i : map.keySet()) {
      if (map.get(i).equals(s)) {
        return i;
      }
    }
    return 0;
  }
}