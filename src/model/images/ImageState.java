package model.images;

/**
 * Represents an observable image stored in a collage project.
 * @param <T> the image's representation of a pixel.
 */
public interface ImageState<T> {
  /**
   * Returns the height of the image stored in the layer.
   * @return the height of the image.
   */
  int getImageHeight();

  /**
   * Returns the width of the image stored in the layer.
   * @return the width of the image.
   */
  int getImageWidth();

  /**
   * Returns the image pixel at the coordinate (row, col).
   *
   * For example, the coordinate (0, 0) will return the pixel in the top-left
   * corner of the image.
   * @param row the row of the pixel to return.
   * @param col the column of the pixel to return
   * @return the pixel at the specified coordinate.
   * @throws IllegalArgumentException if row or column are negative or if the specified
   * coordinate is out-of-bounds.
   */
  T getPixelAtCoord(int row, int col) throws IllegalArgumentException;
}
