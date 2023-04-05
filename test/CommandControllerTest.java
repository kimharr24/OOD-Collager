import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import controller.Controller;
import controller.CommandController;
import model.projects.CollageProject;
import view.CollageView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandControllerTest {

  private Controller controller;

  private Appendable log;

  @Before
  public void init() {
    this.log = new StringBuilder();
  }

  private static class TextViewMock implements CollageView {

    private final Appendable log;

    public TextViewMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void renderMessage(String message) {
      try {
        this.log.append(message + "\n");
      } catch(IOException ignored) {

      }
    }

    @Override
    public void renderCommandOptions() {
      try {
        this.log.append("rendered command options\n");
      } catch(IOException ignored) {
      }
    }

    @Override
    public void renderFilterOptions() {
      try {
        this.log.append("rendered filter options\n");
      } catch(IOException ignored) {
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    Controller controller = new CommandController(null,
            new CommandControllerTest.TextViewMock(new StringBuilder()));
  }

  @Test
  public void testExecuteProgram() {
    this.controller = new CommandController(new StringReader(
            "new-project 200 200 save-image saving.ppm"),
            new TextViewMock(this.log));
    this.controller.executeProgram();
    assertEquals("rendered command options\n", this.log.toString());
    File file = new File("saving.ppm");
    assertTrue(file.delete());
  }

  @Test
  public void testExecuteProgramWithInvalidCommand() {

    this.controller = new CommandController(new StringReader("do-a-flip"),
            new TextViewMock(this.log));
    this.controller.executeProgram();
    assertEquals("rendered command options\nUnknown command!\n",
            this.log.toString());

  }
}
