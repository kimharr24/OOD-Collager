import org.junit.Test;

import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import model.filters.RedComponentFilter;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

/**
 * Tests the command to save a project to a .collage file.
 */
public class SaveProjectCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new SaveProjectCommand();
    command.executeCommand(null);
  }

  @Test
  public void testSaveProjectCommand() {
    ProjectModel<Pixel> project = new CollageProject("my-project", 100, 100);
    project.addLayer("first-layer");
    project.setLayerFilter("first-layer", new RedComponentFilter());

    ProjectCommand command = new SaveProjectCommand();
    command.executeCommand(project);
  }
}
