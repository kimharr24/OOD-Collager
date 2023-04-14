package controller.fileio.fileoutputcommands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Represents a general image file input command. Supports saving to arbitrary image formats
 * using the ImageIO class.
 */
public abstract class AbstractImageFileOutputCommand implements FileOutputCommand<Pixel> {
  private final String formatName;

  /**
   * Constructor for initializing the format name to save an image as. See ImageIO accepted
   * formats to determine valid format names.
   *
   * @param formatName the format to save the image to.
   */
  public AbstractImageFileOutputCommand(String formatName) {
    this.formatName = formatName;
  }

  @Override
  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    BufferedImage bufferedImage = Util.createImageFromScratch(image,
            new BufferedImage(image.getImageWidth(), image.getImageHeight(),
                    BufferedImage.TYPE_INT_RGB));

    File imageFile = new File(filePath);
    try {
      ImageIO.write(bufferedImage, this.formatName, imageFile);
    } catch (IOException ignored) {
    }
  }
}
