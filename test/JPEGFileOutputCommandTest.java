import org.junit.Test;

import java.io.File;

import controller.fileio.fileinputcommands.ImageFileInputCommand;
import controller.fileio.fileinputcommands.JPEGFileInputCommand;
import controller.fileio.fileoutputcommands.FileOutputCommand;
import controller.fileio.fileoutputcommands.JPEGFileOutputCommand;
import model.filters.BlueComponentFilter;
import model.filters.RedComponentFilter;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests to ensure that the JPEG file output command test works as expected.
 */
public class JPEGFileOutputCommandTest {
  @Test
  public void testSaveCollageImage() {
    ProjectModel<Pixel> project = new CollageProject(300, 400);

    project.addLayer("layer-1");
    project.setLayerFilter("layer-1", new BlueComponentFilter());

    project.addLayer("layer-2");
    project.setLayerFilter("layer-2", new RedComponentFilter());

    FileOutputCommand<Pixel> outputCommand = new JPEGFileOutputCommand();
    outputCommand.saveCollageImage(project.getCompositeImage(), "test.png");

    ImageFileInputCommand<Pixel> inputCommand = new JPEGFileInputCommand();

    ImageModel<Pixel> extractedImage = inputCommand.extractImage("test.png",
            new CollageProject(111, 222));

    assertEquals(project.getCompositeImage(), extractedImage);
    File file = new File("test.png");
    assertTrue(file.delete());
  }
}
