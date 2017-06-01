package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver on 11/18/2016.
 */

/**
 * Handles keyboard entries.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> press;
  private Map<Integer, Runnable> type;
  private Map<Integer, Runnable> release;
  private String queueType = "";

  /**
   * Constructor for a Keyboard Handler.
   */
  public KeyboardHandler() {
    press = new HashMap<Integer, Runnable>();
    type = new HashMap<Integer, Runnable>();
    release = new HashMap<Integer, Runnable>();
  }

  /**
   * Handles typed keys.
   *
   * @param e represents a Keyevent of type 'Typed'.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    char c = e.getKeyChar();
    if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
            || c == '6' || c == '7' || c == '8' || c == '9') {
      this.queueType += Character.toString(c);
    }
  }

  /**
   * Handles pressed keys.
   *
   * @param e represents a Keyevent of type 'Pressed'.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (this.press.containsKey(keyCode)) {
      this.press.get(keyCode).run();
    }
  }

  /**
   * Handles released keys.
   *
   * @param e represents a Keyevent of type 'Released'.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    if (this.release.containsKey(keyCode)) {
      this.release.get(keyCode).run();
    }
  }

  /**
   * Adds a runnable to the the map representing holding a key.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunnablePress(int i, Runnable r) {
    this.press.put(i, r);
  }

  /**
   * Adds a runnable to the the map representing typing a key.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunableType(int i, Runnable r) {
    this.type.put(i, r);
  }

  /**
   * Adds a runnable to the the map representing releasing a key.
   *
   * @param i The int value of the key.
   * @param r The runnable.
   */
  public void addRunnableRelease(int i, Runnable r) {
    this.release.put(i, r);
  }

  /**
   * Gets the current queue of typed chars.
   *
   * @return the tyoe characters.
   */
  public String getQueueType() {
    return this.queueType;
  }

  /**
   * Empties the queue of type characters.
   */
  public void removeQueueType() {
    this.queueType = "";
  }


}
