import org.junit.Test;

import model.images.ImageModel;
import model.images.fileinputcommands.AbstractFileInputCommand;
import model.images.fileinputcommands.PPMInputCommand;
import model.pixels.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for a PPM input command.
 */
public class PPMInputCommandTest {
  @Test
  public void testGetImageWidth() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    assertEquals(300, command.getImageWidth("./test/images/cat.ppm"));
    command = new PPMInputCommand("./test/images/emoji.ppm");
    assertEquals(300, command.getImageWidth("./test/images/emoji.ppm"));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotFoundGetImageWidth() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    command.getImageWidth("bye.ppm");
  }

  @Test
  public void testGetImageHeight() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    assertEquals(300, command.getImageHeight("./test/images/cat.ppm"));
    command = new PPMInputCommand("./test/images/emoji.ppm");
    assertEquals(300, command.getImageHeight("./test/images/emoji.ppm"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotFoundGetImageHeight() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    command.getImageWidth("hello.ppm");
  }

  @Test
  public void testExtractImage() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    ImageModel<Pixel> extractedImage = command.extractImage("./test/images/cat.ppm");

    assertEquals(300, extractedImage.getImageHeight());
    assertEquals(300, extractedImage.getImageWidth());

    command = new PPMInputCommand("./test/images/emoji.ppm");
    extractedImage = command.extractImage("./test/images/emoji.ppm");

    assertEquals(255,
            extractedImage.getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(255,
            extractedImage.getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(255,
            extractedImage.getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);

    assertEquals(300, extractedImage.getImageHeight());
    assertEquals(300, extractedImage.getImageWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExtractImageFileNotFound() {
    AbstractFileInputCommand command = new PPMInputCommand("./test/images/cat.ppm");
    ImageModel<Pixel> extractedImage = command.extractImage("ood.ppm");
  }
}
