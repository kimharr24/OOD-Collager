import org.junit.Test;

import java.io.File;

import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import controller.fileio.fileinputcommands.ImageFileInputCommand;
import controller.fileio.fileinputcommands.PPMFileInputCommand;
import model.filters.BlueComponentFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Testing class to ensure that the image saving command works as expected.
 */
public class SaveImageCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new SaveImageCommand("./test/images/test-save-command.ppm");
    command.executeCommand(null);
  }

  @Test
  public void testExecuteCommand() {
    ProjectModel<Pixel> project = new CollageProject(500, 500);
    ImageModel<Pixel> image = new Image(200,300);
    ImageModel<Pixel> compositeImage = new Image(200,300);
    ProjectCommand command = new SaveImageCommand("test-save-command.ppm");
    project.addLayer("first-layer");
    project.addImageToLayer("first-layer", image,0, 0);
    project.setLayerFilter("first-layer", new RedComponentFilter());
    project.addLayer("second-layer");
    project.addImageToLayer("second-layer", compositeImage,50, 50);
    project.setLayerFilter("second-layer", new BlueComponentFilter());

    command.executeCommand(project);
    try {
      ImageFileInputCommand<Pixel> in = new PPMFileInputCommand();
      ImageModel<Pixel> extractedImage = in.extractImage("test-save-command.ppm",
              new CollageProject(99, 100));
    } catch (RuntimeException e) {
      fail("Expected extraction to work!");
    }
    File file = new File("test-save-command.ppm");
    assertTrue(file.delete());
  }
}
