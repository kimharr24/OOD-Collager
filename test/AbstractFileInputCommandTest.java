import org.junit.Test;

import controller.fileio.fileinputcommands.AbstractFileInputCommand;
import controller.fileio.fileinputcommands.FileInputCommand;

import static org.junit.Assert.fail;

/**
 * Testing class to ensure that the shared behaviors abstracted into AbstractFileInputCommand
 * behave as expected.
 */
public class AbstractFileInputCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testInitializeScannerInvalidPath() {
    FileInputCommand command = new AbstractFileInputCommand() {
      @Override
      public void initializeScanner(String filepath) throws IllegalArgumentException {
        super.initializeScanner(filepath);
      }
    };
    command.initializeScanner("./test/images/404.ppm");
  }

  @Test
  public void testInitializeScanner() {
    FileInputCommand command = new AbstractFileInputCommand() {
      @Override
      public void initializeScanner(String filepath) throws IllegalArgumentException {
        super.initializeScanner(filepath);
      }
    };
    try {
      command.initializeScanner("./test/images/cat.ppm");
    } catch (RuntimeException ignored) {
      fail("Expected scanner initialization to succeed.");
    }
  }
}
