package cs3500.music.model;

/**
 * Created by Oliver on 10/17/2016.
 */

/**
 * Represents all the different natural notes that are represented in music.
 */
public enum Pitch {
  C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;


  /**
   * Returns the pitch of the int value.
   * @param i the int value
   * @return  The pitch
   */
  public static Pitch intToPitch(int i) {
    int absolute = i % 12;

    switch (absolute) {
      case 0:
        return C;
      case 1:
        return CSHARP;
      case 2:
        return D;
      case 3:
        return DSHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return FSHARP;
      case 7:
        return G;
      case 8:
        return GSHARP;
      case 9:
        return A;
      case 10:
        return ASHARP;
      case 11:
        return B;
      default:
        throw new IllegalArgumentException("The given Note has no valid pitch.");
    }
  }
}
