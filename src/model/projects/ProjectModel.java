package model.projects;

import model.filters.Filter;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a mutable collage project. All interactive actions a user can perform on a collage
 * project are located in this interface.
 *
 * @param <T> the collage project's representation of an image's pixel.
 */
public interface ProjectModel<T> extends ProjectState<T> {
  /**
   * Given a layer's name, sets that layer to have the given filter.
   *
   * @param layerName the name of the layer.
   * @param filter    the filter to give to the layer.
   * @throws IllegalArgumentException if the given layer does not exist or the filter is null.
   */
  void setLayerFilter(String layerName, Filter<T> filter) throws IllegalArgumentException;

  /**
   * Given a layer's name, places the image at the provided filepath, where the top-left corner
   * of the image is placed at the coordinate (row, col) of the layer.
   *
   * For any arbitrary project, it is assumed that placing an image at a location such that
   * portions of the image are out-of-bounds of the project's canvas
   * is considered an invalid operation.
   *
   * @param layerName the name of the layer.
   * @param image     the image to add to the layer.
   * @param row       the row coordinate of the starting position.
   * @param col       the column coordinate of the starting position.
   * @throws IllegalArgumentException if the given layer does not exist, the given filepath is
   *                                  invalid, the image placed at (row, col) results in portions
   *                                  of the image spilling out of the dimensions of the layer,
   *                                  the row or col are negative, or the row or col are
   *                                  out-of-bounds of the given layer.
   */
  void addImageToLayer(String layerName, ImageModel<Pixel> image, int row, int col)
          throws IllegalArgumentException;

  /**
   * Adds a new layer to the top of the collage project. By default, this layer has a fully
   * transparent white image with the normal filter.
   *
   * @param layerName the name of the new layer.
   */
  void addLayer(String layerName);
}
