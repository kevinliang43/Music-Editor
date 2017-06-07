package cs3500.music.view;


import cs3500.music.model.Note;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is a composite view2 class for a music.
 * It contains a guiView and a midi view2.
 * It shows the gui view2 while playing the music,
 * is a combination of gui view2 and midi view2.
 * It has current beat, length of music, tempo of music, tones of music,
 * and whether the music is pause or not in its field.
 */
public class CompositeView implements IGuiView {
  private GuiViewFrame gui;
  private MidiView midi;
  private int current;
  private int length;
  private int tempo;
  private boolean isPaused;
  private List<String> tone;

  /**
   * Constructs a composite view2.
   */
  public CompositeView() {
    this.gui = new GuiViewFrame();
    this.midi = new MidiView();
    tone = new ArrayList<String>();
    isPaused = false;
  }

  @Override
  public void setAllNotes(Map<Integer, List<Note>> music) {
    gui.setAllNotes(music);
    midi.setAllNotes(music);
  }

  @Override
  public void setLength(int length) {
    this.length = length;
    gui.setLength(length);
    midi.setLength(length);
  }

  @Override
  public void setTone(List<String> notes) {
    tone = notes;
    gui.setTone(notes);
    midi.setTone(notes);
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
    gui.setTempo(tempo);
    midi.setTempo(tempo);
  }

  @Override
  public void goHome() {
    current = 0;
  }

  @Override
  public void goEnd() {
    current = length;
  }

  @Override
  public void scroll(boolean goRight) {
    gui.scroll(goRight);
  }

  @Override
  public int scrollBarMoved(boolean isHorizontal) {
    return gui.scrollBarMoved(isHorizontal);
  }

  @Override
  public void setPause() {
    this.isPaused = !isPaused;
  }

  @Override
  public void addKeyListener(KeyListener kl) {
    gui.addKeyListener(kl);
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    gui.addMouseListener(ml);
  }

  @Override
  public List<String> getTone() {
    return tone;
  }

  @Override
  public void showView() {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if (!isPaused) {
          setCurrentBeat(current);
          midi.showView();
          gui.showView();
          current++;
        }
      }
    };

    timer.scheduleAtFixedRate(task, 0, tempo / 1000);
  }


  @Override
  public void setCurrentBeat(int current) {
    gui.setCurrentBeat(current);
    midi.setCurrentBeat(current);
  }

  @Override
  public int getCurrent() {
    return current;
  }


  @Override
  public boolean getPauseState() {
    return isPaused;
  }

  /**
   * Returns the Composite View's gui view2.
   * @return  the gui view2
   */
  public GuiViewFrame getGui() {
    return this.gui;
  }
}
