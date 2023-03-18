import org.junit.Test;

import controller.commands.AddImageToLayerCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import model.colors.RGBAColor;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for the add image to layer command.
 */
public class AddImageToLayerCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new AddImageToLayerCommand("default-layer",
            "./test/images/cat.ppm", 0, 0);
    command.executeCommand(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeRow() {
    ProjectCommand command = new AddImageToLayerCommand("default-layer",
            "./test/images/cat.ppm", -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeColumn() {
    ProjectCommand command = new AddImageToLayerCommand("default-layer",
            "./test/images/cat.ppm", 1, -1);
  }

  @Test
  public void testGoodConstructor() {
    try {
      ProjectCommand command = new AddImageToLayerCommand("default-layer",
              "./test/images/cat.ppm", 0, 0);
    } catch (RuntimeException e) {
      fail("Expected valid construction");
    }
  }

  @Test
  public void testExecuteCommand() {
    ProjectModel<Pixel> project = new CollageProject("my-project", 300, 300);
    ProjectCommand command = new AddImageToLayerCommand("default-layer",
            "./test/images/cat.ppm", 0, 0);
    for (int i = 0; i < project.getCanvasHeight(); i++) {
      for (int j = 0; j < project.getCanvasWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                        Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
                project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }
    command.executeCommand(project);
    assertEquals(new RGBAColor(90, 74, 59, 255),
            project.getLayerAtPosition(0).getImage().getPixelAtCoord(0, 0).getColor());
    assertEquals(new RGBAColor(88, 72, 57, 255),
            project.getLayerAtPosition(0).getImage().getPixelAtCoord(0, 1).getColor());
  }
}
