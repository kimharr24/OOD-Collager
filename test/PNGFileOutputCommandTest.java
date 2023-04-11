import org.junit.Test;

import java.io.File;

import controller.fileio.fileinputcommands.ImageFileInputCommand;
import controller.fileio.fileinputcommands.PNGFileInputCommand;
import controller.fileio.fileoutputcommands.FileOutputCommand;
import controller.fileio.fileoutputcommands.PNGFileOutputCommand;
import model.filters.BlueComponentFilter;
import model.filters.RedComponentFilter;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class to ensure that a collage project can save images to a PNG file.
 */
public class PNGFileOutputCommandTest {
  @Test
  public void testSaveCollageImage() {
    ProjectModel<Pixel> project = new CollageProject(300, 400);

    project.addLayer("layer-1");
    project.setLayerFilter("layer-1", new BlueComponentFilter());

    project.addLayer("layer-2");
    project.setLayerFilter("layer-2", new RedComponentFilter());

    FileOutputCommand<Pixel> outputCommand = new PNGFileOutputCommand();
    outputCommand.saveCollageImage(project.getCompositeImage(), "./test/images/test.png");

    ImageFileInputCommand<Pixel> inputCommand = new PNGFileInputCommand();

    ImageModel<Pixel> extractedImage = inputCommand.extractImage("./test/images/test.png");

    assertEquals(project.getCompositeImage(), extractedImage);
    File file = new File("./test/images/test.png");
    assertTrue(file.delete());
  }
}
