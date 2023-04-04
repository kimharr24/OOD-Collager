import org.junit.Test;

import java.io.File;

import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import controller.fileio.fileinputcommands.CollageProjectFileInputCommand;
import controller.fileio.fileinputcommands.ProjectFileInputCommand;
import model.filters.BrighteningBlendingFilter;
import model.filters.RedComponentFilter;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class to ensure that the collage project is able to load in projects successfully.
 */
public class CollageProjectFileInputCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExtractProjectInvalidPath() {
    ProjectFileInputCommand<Pixel> command = new CollageProjectFileInputCommand();
    command.extractProject("foo");
  }

  @Test
  public void testExtractProject() {
    ProjectModel<Pixel> project = new CollageProject(100, 100);
    project.addLayer("first-layer");
    project.setLayerFilter("first-layer", new RedComponentFilter());
    project.addLayer("second-layer");
    project.setLayerFilter("second-layer", new BrighteningBlendingFilter());

    ProjectCommand command = new SaveProjectCommand("my-project.collage");
    command.executeCommand(project);

    ProjectFileInputCommand<Pixel> loadCommand = new CollageProjectFileInputCommand();
    ProjectModel<Pixel> loadedProject = loadCommand.extractProject("my-project.collage");

    assertEquals(project.getLayerCount(), loadedProject.getLayerCount());
    assertEquals("default-layer", project.getLayerAtPosition(0).getLayerName());
    assertEquals("default-layer", loadedProject.getLayerAtPosition(0).getLayerName());
    assertEquals("first-layer", project.getLayerAtPosition(1).getLayerName());
    assertEquals("first-layer", loadedProject.getLayerAtPosition(1).getLayerName());
    assertEquals("second-layer", project.getLayerAtPosition(2).getLayerName());
    assertEquals("second-layer", loadedProject.getLayerAtPosition(2).getLayerName());

    assertEquals("red-component", loadedProject.getLayerAtPosition(1).getFilterName());
    assertEquals("brightening-blending", loadedProject.getLayerAtPosition(2).getFilterName());
    assertEquals("normal", loadedProject.getLayerAtPosition(0).getFilterName());

    assertEquals(project.getCanvasHeight(), loadedProject.getCanvasHeight());
    assertEquals(project.getCanvasWidth(), loadedProject.getCanvasWidth());

    for (int layerIndex = 0; layerIndex < project.getLayerCount(); layerIndex++) {
      for (int i = 0; i < project.getCanvasHeight(); i++) {
        for (int j = 0; j < project.getCanvasWidth(); j++) {
          assertEquals(project.getLayerAtPosition(layerIndex).getImage().getPixelAtCoord(i,
                          j).getColor(),
                  loadedProject.getLayerAtPosition(layerIndex).getImage().getPixelAtCoord(i,
                          j).getColor());
        }
      }
    }
    File file = new File("my-project.collage");
    assertTrue(file.delete());
  }
}
