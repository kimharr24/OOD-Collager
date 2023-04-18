import org.junit.Before;
import org.junit.Test;

import java.io.File;

import controller.fileio.fileinputcommands.ImageFileInputCommand;
import controller.fileio.fileinputcommands.PPMFileInputCommand;
import controller.fileio.fileoutputcommands.FileOutputCommand;
import controller.fileio.fileoutputcommands.PPMFileOutputCommand;
import model.colors.RGBAColor;
import model.filters.GreenComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class to ensure that a PPMFileInputCommand can properly read PPM files
 * and convert their contents into an ImageModel.
 */
public class PPMFileInputCommandTest {
  private ImageFileInputCommand<Pixel> command;

  @Before
  public void init() {
    this.command = new PPMFileInputCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageWidthInvalidPath() {
    this.command.getImageWidth("./test/images/404.ppm");
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(300, this.command.getImageWidth("./test/images/cat.ppm"));
    assertEquals(300, this.command.getImageWidth("./test/images/emoji.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageHeightInvalidPath() {
    this.command.getImageHeight("./test/images/404.ppm");
  }

  @Test
  public void testGetHeightWidth() {
    assertEquals(300, this.command.getImageHeight("./test/images/cat.ppm"));
    assertEquals(300, this.command.getImageHeight("./test/images/emoji.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractImageInvalidPath() {
    this.command.extractImage("./test/images/404.ppm", new CollageProject(4, 5));
  }

  @Test
  public void testExtractImage() {
    ProjectModel<Pixel> project = new CollageProject(500, 600);
    project.setLayerFilter("default-layer", new GreenComponentFilter());

    ImageModel<Pixel> opaqueBlueImage = new Image(200,  300);
    opaqueBlueImage.colorBackground(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE));
    project.addImageToLayer("default-layer", opaqueBlueImage, 20, 30);
    ImageModel<Pixel> compositeImage = project.getCompositeImage();

    FileOutputCommand<Pixel> outputCommand = new PPMFileOutputCommand();
    outputCommand.saveCollageImage(compositeImage, "./test/images/ppm_test.ppm");

    ImageModel<Pixel> extractedImage = this.command.extractImage("./test/images/ppm_test.ppm",
            new CollageProject(44, 44));

    assertEquals(compositeImage, extractedImage);
    File file = new File("./test/images/ppm_test.ppm");
    assertTrue(file.delete());
  }
}