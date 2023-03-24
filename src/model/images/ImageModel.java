package model.images;

import model.colors.ColorModel;
import model.pixels.Pixel;

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
   * @param image the image to add to the layer.
   * @param row      the row coordinate of the position to place the given image's top left corner.
   * @param col      the col coordinate of the position to place the given image's top left corner.
   * @throws IllegalArgumentException if row or column are negative, or
   *                                  if row or column are out-of-bounds or
   *                                  if the file path is invalid or
   *                                  contains an unsupported extension or
   *                                  if the image stored at the filepath results in portions
   *                                  of the image spilling out-of-bounds of the current image's
   *                                  boundaries.
   */
  void overlayImage(ImageModel<T> image, int row, int col) throws IllegalArgumentException;

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

  /**
   * Given a color, colors the entire image with that color.
   * @param color the color to color the entire image with.
   * @throws IllegalArgumentException if the provided color is null.
   */
  void colorBackground(ColorModel color) throws IllegalArgumentException;

  /**
   * Given an image to place above this image, collapses these two images into a single image
   * by computing the result of collapsing its pairwise pixel values.
   * @param aboveImage the image to place on top of this image.
   * @return the collapsed image.
   * @throws IllegalArgumentException if the given image is null or not the same size as this image.
   */
  ImageModel<Pixel> collapseImage(ImageModel<Pixel> aboveImage) throws IllegalArgumentException;
}
