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
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import model.images.ImageModel;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing class to ensure that the PPMFileOutputCommandTest can save images to PPM files.
 */
public class PPMFileOutputCommandTest {
  private FileOutputCommand<Pixel> command;

  @Before
  public void init() {
    this.command = new PPMFileOutputCommand();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveCollageImageNullImage() {
    this.command.saveCollageImage(null, "./test/images/test.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveCollageImageInvalidFileExtension() {
    this.command.saveCollageImage(new Image(500, 500), "./test/images/foo.png");
  }

  @Test
  public void testSaveCollageImageSuccess() {
    ProjectModel<Pixel> project = new CollageProject(500, 600);
    project.setLayerFilter("default-layer", new GreenComponentFilter());

    ImageModel<Pixel> opaqueBlueImage = new Image(200,  300);
    opaqueBlueImage.colorBackground(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE));
    project.addImageToLayer("default-layer", opaqueBlueImage, 20, 30);

    ImageModel<Pixel> compositeImage = project.getCompositeImage();

    this.command.saveCollageImage(compositeImage, "./test/images/ppm_test.ppm");

    ImageFileInputCommand<Pixel> inputCommand = new PPMFileInputCommand();
    ImageModel<Pixel> recoveredCompositeImage = inputCommand.extractImage(
            "./test/images/ppm_test.ppm");

    assertEquals(compositeImage, recoveredCompositeImage);

    File testFile = new File("./test/images/ppm_test.ppm");
    assertTrue(testFile.delete());
  }
}