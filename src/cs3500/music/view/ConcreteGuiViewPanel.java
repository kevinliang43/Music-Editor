package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.PitchToString;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

/**
 * The concrete view2 that represents the music model in a gui view2 way.
 */
public class ConcreteGuiViewPanel extends JPanel {
  private Map<Integer, List<Note>> music; //the copy of the music from the model
  private List<String> tone;
  private int length;
  private int current;
  private final int MARGINWIDTH;
  private final int MARGINHEIGHT;
  private final int WIDTH;
  private final int HEIGHT;

  /**
   * Constructs a concreteGuiViewPanel.
   */
  public ConcreteGuiViewPanel() {
    super();
    this.MARGINWIDTH = 40;
    this.MARGINHEIGHT = 30;
    this.WIDTH = 84;
    this.HEIGHT = 21;
    music = new HashMap<Integer, List<Note>>();
    tone = new ArrayList<String>();
  }

  /**
   * To draw everything.
   */
  protected void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    showView(g);
  }

  /**
   * Sets all the notes.
   *
   * @param music the music passing in.
   */
  void setNotes(Map<Integer, List<Note>> music) {
    this.music = music;
  }

  /**
   * Sets the length of music.
   *
   * @param length the length passing in.
   */
  void setLength(int length) {
    this.length = length;
  }

  /**
   * Sets the tone of the music.
   * The tone is a list of string, such as "C1" and "D#3".
   *
   * @param tone the tone passing in.
   */
  void setTone(List<String> tone) {
    Collections.reverse(tone);
    this.tone = tone;

  }

  /**
   * Sets the current beat.
   *
   * @param current the current beat
   */
  void setCurrentBeat(int current) {
    this.current = current;
  }

  /**
   * To paint the beats row in the Gui View.
   *
   * @param g The graphics passing in.
   */
  private void paintBeat(Graphics g) {
    Graphics2D gr = (Graphics2D) g;
    gr.setPaint(Color.BLACK);
    for (int i = 0; i <= length; i++) {
      gr.drawString(Integer.toString(i * 16)
              , i * 4 * WIDTH + MARGINWIDTH, MARGINHEIGHT - 10);
    }
  }

  /**
   * To paint the tone column in the Gui View.
   *
   * @param g The graphics passing in.
   */
  private void paintTone(Graphics g) {
    Graphics2D gr = (Graphics2D) g;
    gr.setPaint(Color.BLACK);
    gr.drawString(tone.get(0), 0, MARGINHEIGHT + 15);


    for (int i = 1; i < tone.size(); i++) {
      int p = MARGINHEIGHT + 15 + i * HEIGHT;
      gr.drawString(tone.get(i), 0, p);
    }
  }


  /**
   * Paints the entire table in the Gui View.
   *
   * @param g The graphics passing in.
   */
  private void paintTable(Graphics g) {
    Graphics2D gr = (Graphics2D) g;
    gr.setColor(Color.BLACK);

    for (int i = 0; i < tone.size(); i++) {
      for (int j = 0; j < length + 1; j++) {
        gr.drawRect(MARGINWIDTH + j * WIDTH, MARGINHEIGHT + i * HEIGHT
                , WIDTH, HEIGHT);
      }
    }
  }

  /**
   * Prints all notes in the Gui View.
   *
   * @param g The graphics passing in.
   */
  private void paintNotes(Graphics g) {
    for (int i = 0; i < length; i++) {
      if (music.containsKey(i)) {
        for (Note note : music.get(i)) {
          paintOneNote(g, note);
        }
      }
    }
  }

  /**
   * Draws the red line.
   *
   * @param g the graphics passing in.
   */
  private void drawRedLine(Graphics g) {
    g.setColor(Color.RED);
    int x = MARGINWIDTH + current * HEIGHT;
    g.drawLine(x, MARGINHEIGHT, x, tone.size() * HEIGHT + MARGINHEIGHT);
  }

  /**
   * Returns the current position of red line.
   *
   * @return the current position of red line
   */
  int redLinePosition() {
    return MARGINWIDTH + current * HEIGHT;
  }

  /**
   * Prints one note in the Gui View.
   *
   * @param g    The graphics passing in.
   * @param note the note to be drawn.
   */
  private void paintOneNote(Graphics g, Note note) {
    PitchToString p = new PitchToString();
    Graphics2D gr = (Graphics2D) g;
    int x = note.getStart() * HEIGHT + MARGINWIDTH;
    int y = tone.indexOf(p.getI(note.getPitch())) * HEIGHT + MARGINHEIGHT;

    gr.setColor(Color.BLACK);
    gr.fillRect(x, y, HEIGHT, HEIGHT);

    if (note.getEnd() - note.getStart() != 1) {
      for (int i = 0; i < note.getEnd() - note.getStart() - 1; i++) {
        gr.setColor(Color.GREEN);
        x += HEIGHT;
        gr.fillRect(x, y, HEIGHT, HEIGHT);
      }
    }
  }

  /**
   * Paints the entire window.
   *
   * @param g The graphics passing in.
   */
  private void showView(Graphics g) {
    paintBeat(g);
    paintTone(g);
    paintNotes(g);
    paintTable(g);
    drawRedLine(g);
    this.setPreferredSize(new Dimension(length * HEIGHT + MARGINWIDTH,
            tone.size() * HEIGHT + MARGINHEIGHT));
  }
}
