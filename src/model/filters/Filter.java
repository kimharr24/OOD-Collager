package model.filters;

import model.images.ImageModel;

/**
 * Represents a generic filter operation that can be applied to a layer
 * in an image. All filters have a name and a method for execution.
 */
public interface Filter<T> {
  /**
   * Retrieves the name of the filter operation.
   *
   * @return the name of the filter operation.
   */
  String getName();

  /**
   * Given an image to apply a filter to, applies the filter to the image using information
   * from the composite image if necessary.
   *
   * @param image          the image to apply a filter to.
   * @param compositeImage the composite image of all layers below the given image.
   * @throws IllegalArgumentException if either image is null or the images do not have
   *                                  the same dimensions.
   */
  void apply(ImageModel<T> image, ImageModel<T> compositeImage);
}
