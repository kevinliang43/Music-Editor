package cs3500.music.view;

import cs3500.music.model.Note;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

/**
 * A skeleton Frame in Swing.
 * It extends JFrame, and contains a panel, which draws everything.
 * It also has a JScrollPane, which can scroll the frame.
 */
public class GuiViewFrame extends JFrame implements IView {
  private final ConcreteGuiViewPanel displayPanel;
  private JScrollPane jp;

  /**
   * Creates new GuiView.
   */
  public GuiViewFrame() {
    this.setTitle("MusicEditor!!!");
    this.displayPanel = new ConcreteGuiViewPanel();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.jp = new JScrollPane(displayPanel);
    this.add(displayPanel);
    this.setSize(new Dimension(1000, 500));
    this.add(jp);
    this.setResizable(false);
    this.setVisible(true);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    jp.addMouseListener(listener);
  }

  @Override
  public void setAllNotes(Map<Integer, List<Note>> music) {
    displayPanel.setNotes(music);
  }

  @Override
  public void setLength(int length) {
    displayPanel.setLength(length);
  }

  @Override
  public void setTone(List<String> tone) {
    displayPanel.setTone(tone);
  }

  @Override
  public void setTempo(int tempo) {
    // gui view2 does not need to set tempo.
  }

  /**
   * Scrolls the scroll bar by 4.
   *
   * @param goRight true if it is going right, false if it is going left.
   */
  public void scroll(boolean goRight) {
    if (goRight) {
      jp.getHorizontalScrollBar().setValue(jp.getHorizontalScrollBar().getValue() + 4);
    } else {
      jp.getHorizontalScrollBar().setValue(jp.getHorizontalScrollBar().getValue() - 4);
    }
  }

  /**
   * This is a local passage method, returns how long did the scrollBar move.
   *
   * @param isHorizontal true if it is horizontal bar, false if it is vertical.
   * @return how long did the scrollBar move
   */
  public int scrollBarMoved(boolean isHorizontal) {
    if (isHorizontal) {
      return jp.getHorizontalScrollBar().getValue();
    } else {
      return jp.getVerticalScrollBar().getValue();
    }
  }

  @Override
  public void showView() {
    int redLine = displayPanel.redLinePosition();
    if (redLine >= 1000 && redLine >= jp.getViewport().getViewPosition().getX() + 1000) {
      jp.getHorizontalScrollBar().setValue(jp.getHorizontalScrollBar().getValue() + 100);
    }
    jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    jp.setViewportView(displayPanel);
    this.repaint();
  }

  @Override
  public void setCurrentBeat(int current) {
    displayPanel.setCurrentBeat(current);
  }


  /**
   * Gets the gui's scroll pane.
   * @return the scroll pane
   */
  public JScrollPane getScroll() {
    return this.jp;
  }
}
