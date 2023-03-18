import org.junit.Test;

import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import model.filters.BlueComponentFilter;
import model.filters.RedComponentFilter;
import model.images.ImageModel;
import model.images.file_input_commands.FileInputCommand;
import model.images.file_input_commands.PPMInputCommand;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;

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
    ProjectModel<Pixel> project = new CollageProject("my-project", 500, 500);
    ProjectCommand command = new SaveImageCommand("./test/images/test-save-command.ppm");
    project.addLayer("first-layer");
    project.addImageToLayer("first-layer", "./test/images/cat.ppm",0, 0);
    project.setLayerFilter("first-layer", new RedComponentFilter());
    project.addLayer("second-layer");
    project.addImageToLayer("second-layer", "./test/images/cat.ppm",100, 100);
    project.setLayerFilter("second-layer", new BlueComponentFilter());

    command.executeCommand(project);

    FileInputCommand<Pixel> in = new PPMInputCommand("./test/images/test-save-command.ppm");
    ImageModel<Pixel> extractedImage = in.extractImage("./test/images/test-save-command.ppm");

    in = new PPMInputCommand("./test/images/rainbow-cat.ppm");
    ImageModel<Pixel> expectedImage = in.extractImage("./test/images/rainbow-cat.ppm");

    for (int i = 0; i < expectedImage.getImageHeight(); i++) {
      for (int j = 0; j < expectedImage.getImageWidth(); j++) {
        assertEquals(expectedImage.getPixelAtCoord(i, j).getColor(),
                extractedImage.getPixelAtCoord(i, j).getColor());
      }
    }
  }
}
