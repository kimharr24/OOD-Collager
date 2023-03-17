package model.images.file_input_commands;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the class for a PPM input list of commands.
 */
public class PPMInputCommand extends AbstractFileInputCommand {

  /**
   * Extracts the given image from wherever it is being imported from, and stores it in the filepath
   * @param filepath the path to the image to load.
   * @return null
   * @throws IllegalArgumentException
   */
  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    this.validateFilePath(filepath);
    return null;
  }

  /**
   * Gets the width of the image.
   * @param filepath the path to the image to laod
   * @return the width value of the image
   * @throws IllegalArgumentException
   */
  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    return 0;
  }

  /**
   * Gets the height of the image.
   * @param filepath the path to the image to load
   * @return the height value of the image
   * @throws IllegalArgumentException
   */
  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    return 0;
  }
}
