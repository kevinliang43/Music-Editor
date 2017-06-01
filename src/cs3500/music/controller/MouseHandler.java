package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver on 11/20/2016.
 */

/**
 * Represents the handler for Mouse events.
 */
public class MouseHandler implements MouseListener {

  Map<Integer, Runnable> click;
  Map<Integer, Runnable> press;
  Map<Integer, Runnable> release;
  private int x;
  private int y;
  private int endX;
  private MouseEvent currentMouse;


  /**
   * Constructor for a Mouse Handler.
   */
  public MouseHandler() {
    this.click = new HashMap<Integer, Runnable>();
    this.press = new HashMap<Integer, Runnable>();
    this.release = new HashMap<Integer, Runnable>();
  }

  /**
   * Checks for a MouseEvent of type 'Clicked'. If exisits, then runs the appropriate runnable.
   * @param e represents the 'Clicked' MouseEvent to be checked and run.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    this.currentMouse = e;
    if (this.click.get(e.getButton()) != null) {
      this.click.get(e.getButton()).run();
      x = e.getX();
      y = e.getY();
    }
  }

  /**
   * Checks for a MouseEvent of type 'Pressed'. If exisits, then runs the appropriate runnable.
   * @param e represents the 'Pressed' MouseEvent to be checked and run.
   */
  @Override
  public void mousePressed(MouseEvent e) {
    endX = 0;
    this.currentMouse = e;
    x = e.getX();
    y = e.getY();

    if (this.press.containsKey(e.getButton())) {
      this.press.get(e.getButton()).run();
    }

  }

  /**
   * Checks for a MouseEvent of type 'Released'. If exisits, then runs the appropriate runnable.
   * @param e represents the 'Released' MouseEvent to be checked and run.
   */
  @Override
  public void mouseReleased(MouseEvent e) {

    this.currentMouse = e;

    if (e.getButton() == e.BUTTON3) {
      endX = e.getX();
    }

    if (release.containsKey(e.getButton())) {
      this.release.get(e.getButton()).run();
    }
  }

  /**
   * Checks for a MouseEvent of type 'Entered'. If exisits, then runs the appropriate runnable.
   * @param e represents the 'Entered' MouseEvent to be checked and run.
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // does Nothing.
  }

  /**
   * Checks for a MouseEvent of type 'Exited'. If exisits, then runs the appropriate runnable.
   * @param e represents the 'Exited' MouseEvent to be checked and run.
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // does Nothing.
  }

  /**
   * Adds a runnable to the the map representing pressing with mouse.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunnablePress(int i, Runnable r) {
    this.press.put(i, r);
  }

  /**
   * Adds a runnable to the the map representing click of the mouse.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunnableClick(int i, Runnable r) {
    this.click.put(i, r);
  }

  /**
   * Adds a runnable to the the map representing releasing the mouse.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunnableRelease(int i, Runnable r) {
    this.release.put(i, r);
  }


  public MouseEvent getCurrentMouse() {
    return this.currentMouse;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getEndX() {
    return endX;
  }
}
