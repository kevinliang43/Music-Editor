package cs3500.music.controller;

/**
 * Created by KevinLiang on 11/21/16.
 */


import java.util.Objects;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;

import cs3500.music.provider.CompositeView;
import cs3500.music.provider.IGuiView;
import cs3500.music.provider.GuiViewFrame;


/**
 * Represents a controller for the Composite View.
 */
public class CompositeController implements IController {

  IMusicModel model;
  CompositeView view;

  /**
   * Constructor for a Composite Controller.
   *
   * @param model represents the model for which the controller is acting on.
   */
  public CompositeController(IMusicModel model) {
    this.model = model;
    this.view = new CompositeView();

    view.setAllNotes(model.getMusic());
    view.setLength(model.getDuration());
    view.setTone(model.getTone(model.getNotes()));
    view.setTempo(model.getTempo());
  }

  /**
   * Convienice Constructor for a Composite controller.
   *
   * @param model represents the model being acted on.
   * @param view  the view to be controlled.
   */
  public CompositeController(IMusicModel model, CompositeView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Loads all of the Keyboard runnables onto the Keyboard handler.
   * Adds the handler to the view Listener.
   */
  @Override
  public void initiateKeyboard() {

    KeyboardHandler kb = new KeyboardHandler();
    kb.addRunnablePress(37, new ScrollLeft(this.model, this.view));
    kb.addRunnablePress(39, new ScrollRight(this.model, this.view));
    kb.addRunnablePress(36, new ScrollToFront(this.model, this.view));
    kb.addRunnablePress(35, new ScrollToEnd(this.model, this.view));
    kb.addRunnablePress(32, new PauseC(this.view));
    kb.addRunnablePress(82, new ResetC(this.view));
    kb.addRunnablePress(8, new RemoveNote(this.model, this.view));
    kb.addRunnablePress(65, new ResetQueue(kb));

    view.addKeyListener(kb);

  }

  /**
   * Loads all of the Mouse runnables onto the Mouse Handler.
   * Adds the handler to the view Listener.
   */
  @Override
  public void initiateMouse() {

    MouseHandler m = new MouseHandler();
    m.addRunnablePress(1, new SelectNote(this.model, this.view.getGui(), m));
    m.addRunnablePress(3, new SelectNoteToBeAdded(this.model, this.view.getGui(), m));
    m.addRunnableRelease(3, new AddNote(this.model, this.view.getGui(), this.view, m));

    this.view.addMouseListener(m);

  }

  /**
   * Starts the Composite View of the Music Editor.
   */
  @Override
  public void begin() {

    Objects.requireNonNull(this.model);
    Objects.requireNonNull(this.view);
    initiateKeyboard();
    initiateMouse();
    view.showView();

  }

  //The Runnables

  /**
   * Runnable for Pausing the Composite View.
   */
  class PauseC implements Runnable {
    CompositeView comp;

    public PauseC(CompositeView comp) {
      this.comp = comp;
    }

    /**
     * Pauses the Composite View, stopping the red line, and pausing the music.
     */
    public void run() {
      this.comp.setPause();
    }
  }

  /**
   * Runnable for removing a Note.
   */
  class RemoveNote implements Runnable {
    IMusicModel model;
    CompositeView view;

    /**
     * Constructor for a RemoveNote runnable.
     *
     * @param model represents the model being acted on.
     * @param view  represents the view being acted on.
     */
    public RemoveNote(IMusicModel model, CompositeView view) {
      this.model = model;
      this.view = view;
    }

    /**
     * Runs the Remove Note runnable. Removes a note from the model and updates it on the views.
     */
    public void run() {
      if (view.getPauseState() && model.getSelectedNote() != null) {
        this.model.removeNote(model.getSelectedNote());
        this.model.setSelectedNote(null);
      }

      view.setAllNotes(model.getMusic());
      view.setLength(model.getDuration());
      view.setTone(model.getTone(model.getNotes()));
      view.setTempo(model.getTempo());
    }
  }

  /**
   * Runnable for resetting the Song. (Brings music back to the beginning).
   */
  class ResetC implements Runnable {
    CompositeView comp;

    /**
     * constructor for the ResetC runnable.
     *
     * @param comp represents the composite view being acted on.
     */
    public ResetC(CompositeView comp) {
      this.comp = comp;
    }

    /**
     * Resets the music to the beginning.
     */
    public void run() {
      this.comp.setCurrentBeat(0);
    }
  }

  /**
   * Runnable for resetting the queue in a Keyboard handler.
   */
  class ResetQueue implements Runnable {
    KeyboardHandler k;

    /**
     * Constructor for the ResetQueue Runnable.
     *
     * @param k represents the keyboard handler.
     */
    public ResetQueue(KeyboardHandler k) {
      this.k = k;
    }

    /**
     * resets the queue.
     */
    public void run() {
      k.removeQueueType();
    }

  }

  /**
   * Runnable for Scrolling Left on the GUI.
   */
  class ScrollLeft implements Runnable {
    IMusicModel model;
    IGuiView gui;

    /**
     * Constructor for the Scrolling left Runnable.
     *
     * @param model represents the model being acted on.
     * @param gui   represents the gui being acted on.
     */
    public ScrollLeft(IMusicModel model, IGuiView gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls left on the GUI.
     */
    public void run() {
      if (gui.getPauseState()) {
        this.gui.scroll(false);
      }
    }
  }

  /**
   * Runnable for Scrolling Right on the GUI.
   */
  class ScrollRight implements Runnable {
    IMusicModel model;
    IGuiView gui;

    /**
     * the constructor.
     *
     * @param model represents the model being acted on.
     * @param gui   represents the gui being acted on.
     */
    public ScrollRight(IMusicModel model, IGuiView gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls Right on the GUI.
     */
    public void run() {
      if (gui.getPauseState()) {
        this.gui.scroll(true);
      }
    }
  }

  /**
   * Runnable for Scrolling to the End of the Piece on the GUI.
   */
  class ScrollToEnd implements Runnable {
    IGuiView gui;
    IMusicModel model;

    /**
     * constructor for the runnable.
     *
     * @param model the model being acted on.
     * @param gui   the gui being acted on.
     */
    public ScrollToEnd(IMusicModel model, IGuiView gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls to the end of the piece.
     */
    public void run() {
      if (this.gui.getPauseState()) {
        this.gui.goEnd();
      }
    }
  }

  /**
   * Scrolls to the Beginning of the Piece.
   */
  class ScrollToFront implements Runnable {
    IGuiView gui;
    IMusicModel model;

    /**
     * constructor for the runnable.
     *
     * @param model model being acted on.
     * @param gui   gui being acted on.
     */
    public ScrollToFront(IMusicModel model, IGuiView gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls to the beginning of the piece.
     */
    public void run() {
      if (this.gui.getPauseState()) {
        this.gui.goHome();
      }
    }
  }

  /**
   * Runnable for Selecting a Note.
   */
  class SelectNote implements Runnable {

    IMusicModel model;
    GuiViewFrame view;
    MouseHandler m;

    /**
     * runnable for selectNote.
     *
     * @param model the model being acted on.
     * @param view  view being acted on.
     * @param m     mouse handler providing x,y.
     */
    public SelectNote(IMusicModel model, GuiViewFrame view, MouseHandler m) {
      this.model = model;
      this.view = view;
      this.m = m;

    }

    /**
     * Selects a note from the GUI view. Stores the selected note into the model.
     */
    public void run() {
      int x = m.getCurrentMouse().getX();
      int y = m.getCurrentMouse().getY();
      int scrollX = view.getScroll().getHorizontalScrollBar().getValue();
      int scrollY = view.getScroll().getVerticalScrollBar().getValue();

      int clickX = x + scrollX - 40;

      int clickY = y + scrollY - 30;


      int fromTheTop = clickY / 21;
      int fromTheLeft = clickX / 21;


      Note n = model.getHighestPitch();

      for (int i = 0; i < fromTheTop; i++) {
        n = Note.prevNote(n);
      }
      Note nd = new Note(n.getPitchLetter(), n.getOctave(), fromTheLeft, fromTheLeft);
      Note noteToBeSelected = this.model.getOverlappedNote(nd);
      this.model.setSelectedNote(noteToBeSelected);

      view.setAllNotes(model.getMusic());
      view.setLength(model.getDuration());
      view.setTone(model.getTone(model.getNotes()));
      view.setTempo(model.getTempo());
    }

  }

  /**
   * Runnable for selecting a note to be added.
   */
  class SelectNoteToBeAdded implements Runnable {
    IMusicModel model;
    GuiViewFrame view;
    MouseHandler m;

    /**
     * constructor for the runnable.
     *
     * @param model model being acted on.
     * @param view  view being acted on.
     * @param m     handler providing coordinates.
     */
    public SelectNoteToBeAdded(IMusicModel model, GuiViewFrame view, MouseHandler m) {
      this.model = model;
      this.view = view;
      this.m = m;

    }

    /**
     * Selects a note from the GUI view. Stores the selected note into the model.
     */
    public void run() {
      int x = m.getCurrentMouse().getX();
      int y = m.getCurrentMouse().getY();
      int scrollX = view.getScroll().getHorizontalScrollBar().getValue();
      int scrollY = view.getScroll().getVerticalScrollBar().getValue();

      int clickX = x + scrollX - 40;

      int clickY = y + scrollY - 30;

      int fromTheTop = clickY / 21;
      int fromTheLeft = clickX / 21;


      Note n = model.getHighestPitch();

      for (int i = 0; i < fromTheTop; i++) {
        n = Note.prevNote(n);
      }
      this.model.setNoteToBeAdded(new Note(n.getPitchLetter(), n.getOctave(),
              fromTheLeft, fromTheLeft));

      view.setAllNotes(model.getMusic());
      view.setLength(model.getDuration());
      view.setTone(model.getTone(model.getNotes()));
      view.setTempo(model.getTempo());


    }

  }

  class AddNote implements Runnable {

    IMusicModel model;
    CompositeView cv;
    GuiViewFrame view;
    MouseHandler m;

    public AddNote(IMusicModel model, GuiViewFrame view, CompositeView cv, MouseHandler m) {
      this.model = model;
      this.view = view;
      this.cv = cv;
      this.m = m;
    }

    public void run() {

      if (cv.getPauseState() && model.getNoteToBeAdded() != null) {

        int x = m.getCurrentMouse().getX();
        int scrollX = view.getScroll().getHorizontalScrollBar().getValue();
        int clickX = x + scrollX - 20;

        int fromTheLeft = clickX / 21;

        if (model.getNoteToBeAdded().getStart() <= fromTheLeft) {

          Note toBeAdded = new Note(
                  model.getNoteToBeAdded().getPitchLetter(),
                  model.getNoteToBeAdded().getOctave(),
                  model.getNoteToBeAdded().getStart(),
                  fromTheLeft, 1, 60);

          try {
            model.addNote(toBeAdded);
          } catch (IllegalArgumentException e) {
            // do nothing. Cannot add a note overlapping.
          }
        }


        cv.setAllNotes(model.getMusic());
        cv.setLength(model.getDuration());
        cv.setTone(model.getTone(model.getNotes()));
        cv.setTempo(model.getTempo());
        this.model.setNoteToBeAdded(null);
      }
    }


  }

}
