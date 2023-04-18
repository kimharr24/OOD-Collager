package controller.fileio.fileinputcommands;

import model.images.ImageModel;
import model.projects.ProjectModel;

/**
 * Represents a generic file input command. The file input command is responsible for providing
 * support for reading in arbitrary image file types and extracting an image which is
 * compatible with the collage project.
 *
 * @param <T> the image's pixel representation.
 */
public interface ImageFileInputCommand<T> {
  /**
   * Returns the image stored in the file.
   *
   * @param filepath the path to the image to load.
   * @param project the collage project model.
   * @return the stored image.
   * @throws IllegalArgumentException if the file does not exist or the project model is null.
   */
  ImageModel<T> extractImage(String filepath, ProjectModel<T> project)
          throws IllegalArgumentException;

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
