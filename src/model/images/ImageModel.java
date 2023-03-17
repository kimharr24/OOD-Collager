package model.images;

/**
 * Represents a mutable image stored in a collage project.
 *
 * @param <T> the image's representation of a pixel.
 */
public interface ImageModel<T> extends ImageState<T> {
  /**
   * Overlays an image onto the current image, updating the current image based
   * on the given image. The given image's top left corner is placed at (row, col).
   *
   * @param filePath the file path to the image to overlay onto the current image.
   * @param row      the row coordinate of the position to place the given image's top left corner.
   * @param col      the col coordinate of the position to place the given image's top left corner.
   * @throws IllegalArgumentException if row or column are negative, or
   *                                  if row or column are out-of-bounds.
   */
  void overlayImage(String filePath, int row, int col) throws IllegalArgumentException;

  /**
   * Sets the pixel at the given coordinate (row, col) to the provided pixel.
   *
   * @param pixel the updated pixel.
   * @param row   the row coordinate of the position to place the updated pixel.
   * @param col   the col coordinate of the position to place the updated pixel.
   * @throws IllegalArgumentException if the given pixel is null, the row or column are negative,
   *                                  or if the row or column are out-of-bounds.
   */
  void setImagePixelAtCoord(T pixel, int row, int col) throws IllegalArgumentException;
}
