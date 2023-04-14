package controller.fileio.fileoutputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Saves images stored in the collage project to a PNG format.
 */
public class PNGFileOutputCommand extends AbstractImageFileOutputCommand {
  /**
   * Default constructor for a PNG command initiates the format name to "png."
   */
  public PNGFileOutputCommand() {
    super("png");
  }

  /**
   * Saves the image to a PNG format.
   *
   * @param image the image to save.
   * @param filePath the path to save the image to.
   */
  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    super.saveCollageImage(image, filePath);
  }
}
