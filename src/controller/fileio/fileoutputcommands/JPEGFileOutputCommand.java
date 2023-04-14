package controller.fileio.fileoutputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a command for saving images in the collage project to a JPG format.
 */
public class JPEGFileOutputCommand extends AbstractImageFileOutputCommand {
  /**
   * Default constructor for a PNG command initiates the format name to "jpg."
   */
  public JPEGFileOutputCommand() {
    super("jpg");
  }

  /**
   * Saves the image to a JPG format.
   *
   * @param image the image to save.
   * @param filePath the path to save the image to.
   */
  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    super.saveCollageImage(image, filePath);
  }
}
