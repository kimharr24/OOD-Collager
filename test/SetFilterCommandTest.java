import org.junit.Test;

import controller.commands.ProjectCommand;
import controller.commands.SetFilterCommand;
import model.colors.RGBAColor;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;

import static org.junit.Assert.assertEquals;

/**
 * Testing class to ensure that the set filter command behaves as expected.
 */
public class SetFilterCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new SetFilterCommand("default-layer", "red-component");
    command.executeCommand(null);
  }

  @Test
  public void testExecuteCommand() {
    ProjectModel<Pixel> project = new CollageProject( 300, 300);
    ProjectCommand command = new SetFilterCommand("default-layer", "red-component");
    for (int i = 0; i < project.getCanvasHeight(); i++) {
      for (int j = 0; j < project.getCanvasWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                        Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
                project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }
    command.executeCommand(project);
    for (int i = 0; i < project.getCanvasHeight(); i++) {
      for (int j = 0; j < project.getCanvasWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, Util.MAX_PROJECT_VALUE),
                project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }

    command = new SetFilterCommand("default-layer", "blue-component");
    command.executeCommand(project);
    for (int i = 0; i < project.getCanvasHeight(); i++) {
      for (int j = 0; j < project.getCanvasWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
                project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }
  }
}
