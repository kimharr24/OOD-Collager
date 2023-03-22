import org.junit.Test;

import controller.commands.AddLayerCommand;
import controller.commands.ProjectCommand;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing class to ensure that add layer command behaves as expected.
 */
public class AddLayerCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new AddLayerCommand("default-layer");
    command.executeCommand(null);
  }

  @Test
  public void testExecuteCommand() {
    ProjectModel<Pixel> project = new CollageProject("my-project", 300, 300);
    ProjectCommand command = new AddLayerCommand("first-layer");
    assertTrue(project.containsLayer("default-layer"));
    assertFalse(project.containsLayer("first-layer"));
    assertEquals(1, project.getLayerCount());

    command.executeCommand(project);
    assertTrue(project.containsLayer("first-layer"));
    assertEquals(2, project.getLayerCount());
    command = new AddLayerCommand("second-layer");
    command.executeCommand(project);
    assertTrue(project.containsLayer("second-layer"));
    assertEquals(3, project.getLayerCount());

    assertTrue(project.getLayerAtPosition(0).getImage().getImageHeight() ==
            project.getLayerAtPosition(1).getImage().getImageHeight());
    assertTrue(project.getLayerAtPosition(1).getImage().getImageHeight() ==
            project.getLayerAtPosition(2).getImage().getImageHeight());
    assertTrue(project.getLayerAtPosition(0).getImage().getImageWidth() ==
            project.getLayerAtPosition(1).getImage().getImageWidth());
    assertTrue(project.getLayerAtPosition(1).getImage().getImageWidth() ==
            project.getLayerAtPosition(2).getImage().getImageWidth());
  }
}
