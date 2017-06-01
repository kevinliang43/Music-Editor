package cs3500.music.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by KevinLiang on 11/22/16.
 */
public class MouseHandlerTests {

  private final ByteArrayOutputStream out = new ByteArrayOutputStream();

  @Before // initializes initial conditions.
  public void initialize() {
    System.setOut(new PrintStream(out));
  }

  @After // resets conditions
  public void reset() {
    System.setOut(null);
  }

  // Represents the Mouse events to be tested.
  MouseEvent rightClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON3_MASK, 0, 0, MouseEvent.BUTTON3, false, MouseEvent.BUTTON3);
  MouseEvent leftClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON1_MASK, 0, 0, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1);
  MouseEvent middleClick = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON2_MASK, 0, 0, MouseEvent.BUTTON2, false, MouseEvent.BUTTON2);

  // represents the mock runnables to be tested.
  Runnable rightC = () -> System.out.print("right mouse clicked");
  Runnable leftC = () -> System.out.print("left mouse clicked");
  Runnable middleC = () -> System.out.print("middle mouse clicked");


  @Test
  public void testRClick() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnableClick(MouseEvent.BUTTON3, rightC);
    mh.mouseClicked(rightClick);
    assertEquals("right mouse clicked", out.toString());
  }

  @Test
  public void testLClick() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnableClick(MouseEvent.BUTTON1, leftC);
    mh.mouseClicked(leftClick);
    assertEquals("left mouse clicked", out.toString());
  }

  @Test
  public void testMClick() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnableClick(MouseEvent.BUTTON2, middleC);
    mh.mouseClicked(middleClick);
    assertEquals("middle mouse clicked", out.toString());
  }

  @Test
  public void testLPressed() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnablePress(MouseEvent.BUTTON1, leftC);
    mh.mousePressed(leftClick);
    assertEquals("left mouse clicked", out.toString());

  }

  @Test
  public void testRPressed() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnablePress(MouseEvent.BUTTON3, rightC);
    mh.mousePressed(rightClick);
    assertEquals("right mouse clicked", out.toString());

  }

  @Test
  public void testMPressed() {
    MouseHandler mh = new MouseHandler();
    mh.addRunnablePress(MouseEvent.BUTTON2, middleC);
    mh.mousePressed(middleClick);
    assertEquals("middle mouse clicked", out.toString());

  }

}
