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
    assertEquals("normal", project.getLayerAtPosition(0).getFilterName());
    command.executeCommand(project);
    assertEquals("red-component", project.getLayerAtPosition(0).getFilterName());

    command = new SetFilterCommand("default-layer", "blue-component");

    command.executeCommand(project);
    assertEquals("blue-component", project.getLayerAtPosition(0).getFilterName());
  }
}
