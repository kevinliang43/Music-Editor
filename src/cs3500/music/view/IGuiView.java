package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;


public interface IGuiView extends IView {

  /**
   * Sets the current beat of composite view2 to 0
   * in order to play the music from the beginning.
   */
  void goHome();

  /**
   * Go to the end of the music.
   * Sets the current beat of composite view2 to length of the music.
   */
  void goEnd();

  /**
   * Scrolls the view2 when presses left and right arrows.
   *
   * @param goRight a boolean, true if right arrow pressed, false if left arrow pressed.
   */
  void scroll(boolean goRight);

  /**
   * Returns how long did the scroll bar moved.
   *
   * @param isHorizontal a boolean, true if is a horizontal bar, false if is a vertical bar.
   * @return how long did the scroll bar moved
   */
  int scrollBarMoved(boolean isHorizontal);


  /**
   * Sets the pause state of the composite view2.
   * based on whether pause key is pressed.
   */
  void setPause();

  /**
   * Adds the keyListener to the view2.
   *
   * @param kl the keyListener
   */
  void addKeyListener(KeyListener kl);

  /**
   * Adds the mouseListener to the view2.
   *
   * @param ml the mouse listener
   */
  void addMouseListener(MouseListener ml);

  /**
   * Gets list of tone for usage in the composite view2.
   *
   * @return list of tone as strings
   */
  List<String> getTone();


  /**
   * Gets the current beat.
   *
   * @return the current beat
   */
  int getCurrent();


  /**
   * Gets the pause state, true if it is paused, false if it is not.
   *
   * @return if it is paused
   */
  boolean getPauseState();
}
