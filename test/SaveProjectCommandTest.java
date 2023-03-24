import org.junit.Test;

import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import model.filters.RedComponentFilter;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.fail;

/**
 * Tests the command to save a project to a .collage file.
 */
public class SaveProjectCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new SaveProjectCommand("my-project.collage");
    command.executeCommand(null);
  }

  @Test
  public void testSaveProjectCommand() {
    try {
      ProjectModel<Pixel> project = new CollageProject(100, 100);
      project.addLayer("first-layer");
      project.setLayerFilter("first-layer", new RedComponentFilter());

      ProjectCommand command = new SaveProjectCommand("my-project.collage");
      command.executeCommand(project);
    } catch (RuntimeException e) {
      fail("Expected project saving to work!");
    }
  }
}
