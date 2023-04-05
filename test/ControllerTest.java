import org.junit.Before;
import org.junit.Test;
import controller.CommandController;
import java.io.StringReader;
import controller.TextController;
import controller.TextController;
import view.CollageView;
import view.TextView;

import static org.junit.Assert.fail;

/**
 * Testing class for the controller.
 */
public class ControllerTest {
  private TextController controller;
  private CollageView view;

  @Before
  public void init() {
    this.view = new TextView();
  }

  @Test
  public void testImmediateQuit() {
    // Should not lead to an infinite loop
    try {
      this.controller = new CommandController(new StringReader("quit"), this.view);
    } catch (RuntimeException e) {
      fail("Expected the constructor to work.");
    }
  }

  @Test
  public void testCreateNewProject() {
    try {
      this.controller = new CommandController(new StringReader("new-project my-project 100 200"),
              this.view);
    } catch (RuntimeException e) {
      fail("Expected project to be created successfully!");
    }
  }
}
