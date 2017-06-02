package cs3500.music.controller;

import java.io.IOException;

import cs3500.music.model.IMusicModel;

/**
 * Created by KevinLiang on 11/22/16.
 */
public class MockCompositeController extends CompositeController {

  Appendable app;

  public MockCompositeController(IMusicModel model) {
    super(model);
    this.app = new StringBuffer();
  }

  @Override
  public void initiateKeyboard() {
    super.initiateKeyboard();
    try {
      app.append("keyboard initiated \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void initiateMouse() {
    super.initiateMouse();
    try {
      app.append("keyboard initiated \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void begin() {
    super.begin();
  }
}
