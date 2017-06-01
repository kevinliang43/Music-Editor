package cs3500.music.controller;

import java.util.Objects;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.Note;
import cs3500.music.provider.CompositeView;
import cs3500.music.provider.GuiViewFrame;

/**
 * Created by KevinLiang on 11/22/16.
 */
public class GuiController implements IController {

  IMusicModel model;
  GuiViewFrame view;

  /**
   * Constructor for the GUI view controller.
   * @param model represents the model that is being used in the controller.
   */
  public GuiController(IMusicModel model) {
    this.model = model;
    this.view = new GuiViewFrame();

    view.setAllNotes(model.getMusic());
    view.setLength(model.getDuration());
    view.setTone(model.getTone(model.getNotes()));
    view.setTempo(model.getTempo());
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
    kb.addRunnablePress(8, new RemoveNote(this.model, this.view));
    kb.addRunnablePress(65, new ResetQueue(kb));
    kb.addRunnablePress(83, new AddNote(this.model, this.view, kb));
    view.addKeyListener(kb);

  }

  /**
   * Loads all of the Mouse runnables onto the Mouse Handler.
   * Adds the handler to the view Listener.
   */
  @Override
  public void initiateMouse() {
    MouseHandler m = new MouseHandler();
    m.addRunnablePress(1, new SelectNote(this.model, this.view, m));
    m.addRunnablePress(3, new SelectNoteToBeAdded(this.model, this.view, m));
    this.view.getScroll().addMouseListener(m);
  }

  /**
   * Begins the Controller for the GUI View.
   */
  @Override
  public void begin() {

    Objects.requireNonNull(this.model);
    Objects.requireNonNull(this.view);
    initiateKeyboard();
    initiateMouse();
    view.showView();

  }

  /**
   * Adds the currently selected note to the model, with duration stored separately.
   */
  class AddNote implements Runnable {
    IMusicModel model;
    GuiViewFrame view;
    KeyboardHandler k;

    /**
     * constructor for the AddNote runnable.
     * @param model represents the model being acted on.
     * @param view represents the view being acted on.
     * @param k the keyhandler providing extra information.
     */
    public AddNote(IMusicModel model, GuiViewFrame view, KeyboardHandler k) {
      this.model = model;
      this.view = view;
      this.k = k;
    }

    /**
     * Runs the add Note runnable.
     */
    public void run() {
      if (this.model.isPaused() && this.model.getNoteToBeAdded() != null
              && !k.getQueueType().equals("")) {
        Note n = model.getNoteToBeAdded();


        int duration = Integer.parseInt(k.getQueueType());
        if (duration > 0) {
          try {
            model.addNote(new Note(n.getPitchLetter(), n.getOctave(),
                    n.getStart(), n.getStart() + duration - 1));
          } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
          }

          model.setNoteToBeAdded(null);
          view.setAllNotes(model.getMusic());
          view.setLength(model.getDuration());
          view.setTone(model.getTone(model.getNotes()));
          view.setTempo(model.getTempo());

        }
      }

    }

  }

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
    GuiViewFrame view;

    /**
     * Constructor for a RemoveNote runnable.
     * @param model represents the model being acted on.
     * @param view represents the view being acted on.
     */
    public RemoveNote(IMusicModel model, GuiViewFrame view) {
      this.model = model;
      this.view = view;
    }

    /**
     * Runs the Remove Note runnable. Removes a note from the model and updates it on the views.
     */
    public void run() {
      if (model.isPaused() && model.getSelectedNote() != null) {
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
    GuiViewFrame gui;

    /**
     * Constructor for the Scrolling left Runnable.
     * @param model represents the model being acted on.
     * @param gui represents the gui being acted on.
     */
    public ScrollLeft(IMusicModel model, GuiViewFrame gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls left on the GUI.
     */
    public void run() {
      if (model.isPaused()) {
        this.gui.scroll(false);
      }
    }
  }

  /**
   * Runnable for Scrolling Right on the GUI.
   */
  class ScrollRight implements Runnable {
    IMusicModel model;
    GuiViewFrame gui;

    /**
     * the constructor.
     * @param model represents the model being acted on.
     * @param gui represents the gui being acted on.
     */
    public ScrollRight(IMusicModel model, GuiViewFrame gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls Right on the GUI.
     */
    public void run() {
      if (model.isPaused()) {
        this.gui.scroll(true);
      }
    }
  }

  /**
   * Runnable for Scrolling to the End of the Piece on the GUI.
   */
  class ScrollToEnd implements Runnable {
    GuiViewFrame gui;
    IMusicModel model;

    /**
     * constructor for the runnable.
     * @param model the model being acted on.
     * @param gui the gui being acted on.
     */
    public ScrollToEnd(IMusicModel model, GuiViewFrame gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls to the end of the piece.
     */
    public void run() {
      if (this.model.isPaused()) {
        this.gui.getScroll().getHorizontalScrollBar().setValue(model.getDuration());
      }
    }
  }

  /**
   * Scrolls to the Beginning of the Piece.
   */
  class ScrollToFront implements Runnable {
    GuiViewFrame gui;
    IMusicModel model;

    /**
     * constructor for the runnable.
     * @param model model being acted on.
     * @param gui gui being acted on.
     */
    public ScrollToFront(IMusicModel model, GuiViewFrame gui) {
      this.model = model;
      this.gui = gui;
    }

    /**
     * Scrolls to the beginning of the piece.
     */
    public void run() {
      if (this.model.isPaused()) {
        this.gui.getScroll().getHorizontalScrollBar().setValue(0);
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
     * @param model the model being acted on.
     * @param view view being acted on.
     * @param m mouse handler providing x,y.
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

      int clickX = x + scrollX - 20;

      int clickY = y + scrollY - 30;

      int fromTheTop = clickY / 20;
      int fromTheLeft = clickX / 20;


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
     * @param model model being acted on.
     * @param view view being acted on.
     * @param m handler providing coordinates.
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

      int clickX = x + scrollX - 20;

      int clickY = y + scrollY - 30;

      int fromTheTop = clickY / 20;
      int fromTheLeft = clickX / 20;


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
}
