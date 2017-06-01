package cs3500.music.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.model.IMusicModel;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;


/**
 * Created by KevinLiang on 11/22/16.
 */
public class ControllerTests {

  CompositionBuilder<IMusicModel> builder = new MusicModel.Builder();

  Readable fileReader = null;
  IMusicModel modelRead = null;

  /**
   * initializes the tests conditions.
   */
  public void initialize() {
    try {
      fileReader = new FileReader(new File("portal-ending.txt"));
    } catch (
            FileNotFoundException e) {
      e.printStackTrace();
    }

    modelRead = MusicReader.parseFile(fileReader, builder);
  }

//  @Test
//  public void testComposite() {
//    initialize();
//    MockCompositeController c = new MockCompositeController(modelRead);
//    assertEquals(c.app.toString(), "");
//    c.begin();
//    assertEquals(c.app.toString(), "keyboard initiated \nmouse initiated ");
//
//
//  }

}
