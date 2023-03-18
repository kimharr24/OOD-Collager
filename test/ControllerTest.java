import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.CommandController;
import controller.Controller;
import view.CollageView;
import view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for the controller.
 */
public class ControllerTest {
  private Controller controller;
  private CollageView view;

  @Before
  public void init() {
    this.view = new TextView();
  }

  @Test
  public void testImmediateQuit() {
    // Should not lead to an infinite loop
    this.controller = new CommandController(new StringReader("quit"), this.view);
  }

  @Test
  public void testCreateNewProject() {
    this.controller = new CommandController(new StringReader("new-project my-project 100 200"),
            this.view);
  }
}
