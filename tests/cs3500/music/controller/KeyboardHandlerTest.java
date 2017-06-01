package cs3500.music.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by KevinLiang on 11/22/16.
 */
public class KeyboardHandlerTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();

  @Before // initializes initial conditions.
  public void initialize() {
    System.setOut(new PrintStream(out));
  }

  @After // resets conditions
  public void reset() {
    System.setOut(null);
  }

  // Key events to be tested. For the Gui/composite view: scrolling, pausing, and resetting.
  KeyEvent leftKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_LEFT, ' ', 0);
  KeyEvent rightKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_RIGHT, ' ', 0);
  KeyEvent homeKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_HOME, ' ', 0);
  KeyEvent endKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_END, ' ', 0);
  KeyEvent upKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_UP, ' ', 0);
  KeyEvent downKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_DOWN, ' ', 0);
  KeyEvent rKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_R, ' ', 0);
  KeyEvent spaceKey = new KeyEvent(new Component() {}, 0, 10,
          InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0);


  // lambda functinos representing mock Runnables.
  private Runnable reset = () -> System.out.print("reset");
  private Runnable space = () -> System.out.print("pause");
  private Runnable home = () -> System.out.print("scrolled beginning");
  private Runnable end = () -> System.out.print("scrolled end");
  private Runnable up = () -> System.out.print("scrolled up");
  private Runnable right = () -> System.out.print("scrolled right");
  private Runnable down = () -> System.out.print("scrolled down");
  private Runnable left = () -> System.out.print("scrolled left");



  @Test
  public void testUp() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_UP, up);
    kb.keyPressed(upKey);
    assertEquals("scrolled up", out.toString());
  }

  @Test
  public void testDown() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_DOWN, down);
    kb.keyPressed(downKey);
    assertEquals("scrolled down", out.toString());
  }

  @Test
  public void testLeft() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_LEFT, left);
    kb.keyPressed(leftKey);
    assertEquals("scrolled left", out.toString());
  }

  @Test
  public void testRight() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_RIGHT, right);
    kb.keyPressed(rightKey);
    assertEquals("scrolled right", out.toString());
  }

  @Test
  public void testHome() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_HOME, home);
    kb.keyPressed(homeKey);
    assertEquals("scrolled beginning", out.toString());
  }

  @Test
  public void testEnd() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_END, end);
    kb.keyPressed(endKey);
    assertEquals("scrolled end", out.toString());
  }

  @Test
  public void testReset() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_R, reset);
    kb.keyPressed(rKey);
    assertEquals("reset", out.toString());
  }

  @Test
  public void testPause() {
    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(KeyEvent.VK_SPACE, space);
    kb.keyPressed(spaceKey);
    assertEquals("pause", out.toString());
  }

}
