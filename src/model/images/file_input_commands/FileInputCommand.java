package model.images.file_input_commands;

import model.images.ImageModel;

/**
 * Represents a generic file input command. The file input command is responsible for providing
 * support for reading in arbitrary image file types and extracting an image which is
 * compatible with the collage project.
 *
 * @param <T> the image's pixel representation.
 */
public interface FileInputCommand<T> {
  /**
   * Returns the image stored in the file.
   *
   * @param filepath the path to the image to load.
   * @return the stored image.
   * @throws IllegalArgumentException if the file does not exist.
   */
  ImageModel<T> extractImage(String filepath) throws IllegalArgumentException;

  /**
   * Returns the width of the image that is being read by the input command.
   * @return the width of the image.
   * @throws IllegalArgumentException if the file does not exist.
   */
  int getImageWidth(String filepath) throws IllegalArgumentException;

  /**
   * Returns the height of the image that is being read by the input command.
   * @return the height of the image.
   * @throws IllegalArgumentException if the file does not exist.
   */
  int getImageHeight(String filepath) throws IllegalArgumentException;
}
