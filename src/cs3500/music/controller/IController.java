package cs3500.music.controller;

/**
 * Created by KevinLiang on 11/21/16.
 */

/**
 * Represents the interface for Controllers for the Music Editor.
 */
public interface IController {

  /**
   * Loads the Keyboard runnables for the specific controller.
   */
  public void initiateKeyboard();

  /**
   * loads the Mouse runnables for the specific controller.
   */
  public void initiateMouse();

  /**
   * begins the controller.
   */
  public void begin();
}
