import org.junit.Before;
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
 * Testing class to ensure that JPEGFileInputCommands can properly read in JPG images
 * and load them into the collage project.
 */
public class JPEGFileInputCommandTest {
  private ImageFileInputCommand<Pixel> command;

  @Before
  public void init() {
    this.command = new JPEGFileInputCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageWidthInvalidPath() {
    this.command.getImageWidth("./test/images/404.png");
  }

  @Test
  public void testGetImageWidth() {
    ProjectModel<Pixel> project = new CollageProject(300, 400);

    project.addLayer("layer-1");
    project.setLayerFilter("layer-1", new BlueComponentFilter());

    project.addLayer("layer-2");
    project.setLayerFilter("layer-2", new RedComponentFilter());

    FileOutputCommand<Pixel> outputCommand = new JPEGFileOutputCommand();
    outputCommand.saveCollageImage(project.getCompositeImage(), "test.png");
    assertEquals(400, this.command.getImageWidth("test.png"));
    File file = new File("test.png");
    assertTrue(file.delete());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageHeightInvalidPath() {
    this.command.getImageHeight("./test/images/404.png");
  }

  @Test
  public void testGetImageHeight() {
    ProjectModel<Pixel> project = new CollageProject(300, 400);

    project.addLayer("layer-1");
    project.setLayerFilter("layer-1", new BlueComponentFilter());

    project.addLayer("layer-2");
    project.setLayerFilter("layer-2", new RedComponentFilter());

    FileOutputCommand<Pixel> outputCommand = new JPEGFileOutputCommand();
    outputCommand.saveCollageImage(project.getCompositeImage(), "test.png");
    assertEquals(300, this.command.getImageHeight("test.png"));
    File file = new File("test.png");
    assertTrue(file.delete());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractImageInvalidPath() {
    this.command.extractImage("./test/images/404.png");
  }

  @Test
  public void testExtractImage() {
    ProjectModel<Pixel> project = new CollageProject(300, 400);

    project.addLayer("layer-1");
    project.setLayerFilter("layer-1", new BlueComponentFilter());

    project.addLayer("layer-2");
    project.setLayerFilter("layer-2", new RedComponentFilter());

    FileOutputCommand<Pixel> outputCommand = new JPEGFileOutputCommand();
    outputCommand.saveCollageImage(project.getCompositeImage(), "test.png");

    ImageFileInputCommand<Pixel> inputCommand = new JPEGFileInputCommand();

    ImageModel<Pixel> extractedImage = inputCommand.extractImage("test.png");

    assertEquals(project.getCompositeImage(), extractedImage);
    File file = new File("test.png");
    assertTrue(file.delete());
  }
}
