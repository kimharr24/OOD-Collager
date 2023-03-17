import org.junit.Test;

import model.images.ImageModel;
import model.images.file_input_commands.AbstractFileInputCommand;
import model.images.file_input_commands.PPMInputCommand;
import model.pixels.Pixel;

import static org.junit.Assert.assertEquals;

// MISSING: Extract image with different max value
public class PPMInputCommandTest {
  @Test
  public void testGetImageWidth() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    assertEquals(600, command.getImageWidth("./test/images/tako.ppm"));
    command = new PPMInputCommand("./test/images/small-image-255-max.ppm");
    assertEquals(9, command.getImageWidth("./test/images/small-image-255-max.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotFoundGetImageWidth() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    command.getImageWidth("bye.ppm");
  }

  @Test
  public void testGetImageHeight() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    assertEquals(800, command.getImageHeight("./test/images/tako.ppm"));
    command = new PPMInputCommand("./test/images/small-image-255-max.ppm");
    assertEquals(4, command.getImageHeight("./test/images/small-image-255-max.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotFoundGetImageHeight() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    command.getImageWidth("hello.ppm");
  }

  @Test
  public void testExtractImage() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    ImageModel<Pixel> extractedImage = command.extractImage("./test/images/tako.ppm");

    assertEquals(173,
            extractedImage.getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(179,
            extractedImage.getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(151,
            extractedImage.getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);
    assertEquals(174,
            extractedImage.getPixelAtCoord(1, 4).getColor().getRedComponent(), 0.001);
    assertEquals(180,
            extractedImage.getPixelAtCoord(1, 4).getColor().getGreenComponent(), 0.001);
    assertEquals(152,
            extractedImage.getPixelAtCoord(1, 4).getColor().getBlueComponent(), 0.001);

    assertEquals(800, extractedImage.getImageHeight());
    assertEquals(600, extractedImage.getImageWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractImageFileNotFound() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/tako.ppm");
    ImageModel<Pixel> extractedImage = command.extractImage("ood.ppm");
  }
}
