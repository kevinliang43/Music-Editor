package cs3500.music.view;

import cs3500.music.controller.CompositeController;
import cs3500.music.controller.GuiController;
import cs3500.music.controller.IController;
import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view2.ViewFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.sound.midi.InvalidMidiDataException;


/**
 * Contains the main method for the View portion of the MVC.
 * Displays certain views depending on input.
 */
public class MusicEditor {

  /**
   * The main method. Looks for two inputs:
   * The first input being the text file.
   * The second input being the view2 type.
   * @param args represents a List of String representing input.
   * @throws IOException loss of I/O stream.
   * @throws InvalidMidiDataException Outside of MIDI reciever parameters.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {



    Scanner input = new Scanner(System.in);
    CompositionBuilder<IMusicModel> builder = new MusicModel.Builder();
    Readable fileReader = null;
    String fileName;
    int idx = 0;


    while (idx < args.length) {
      if (fileReader == null) {
        try {
          fileName = args[idx];
          fileReader = new FileReader(new File(fileName));
          idx++;
          break;
        } catch (FileNotFoundException e) {
          System.out.println("File not Found. Please Try again: ");
        } catch (NoSuchElementException e) {
          System.out.println("Please Try again: ");
        }
        idx++;
      }
    }


    IMusicModel modelRead = MusicReader.parseFile(fileReader, builder);
    IController controller = null;
    IView view = null;
    while (idx < args.length && (controller == null && view == null)) {

      switch (args[idx]) {
        case "console":
          view = new ViewFactory(modelRead).createView(ViewFactory.ViewType.CONSOLE);
          view.showView();
          break;
        case "gui":
          modelRead.setPaused(true);
          controller = new GuiController(modelRead);
          controller.begin();
          break;
        case "midi":
          view = new ViewFactory(modelRead).createView(ViewFactory.ViewType.MIDI);
          view.showView();
          break;
        case "composite":
          controller = new CompositeController(modelRead);
          controller.begin();
          break;
        default:
          System.out.println("Not a valid view2 type.");
          break;
      }
      idx++;
    }
  }
}
