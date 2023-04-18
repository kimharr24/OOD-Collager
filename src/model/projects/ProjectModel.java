package model.projects;

import model.colors.ColorModel;
import model.filters.Filter;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Position2D;

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
   * For any arbitrary project, it is assumed that placing an image at a location such that
   * portions of the image are out-of-bounds of the project's canvas
   * is considered an invalid operation.
   *
   * @param layerName the name of the layer.
   * @param image     the image to add to the layer.
   * @param row       the row coordinate of the starting position.
   * @param col       the column coordinate of the starting position.
   * @throws IllegalArgumentException if the given layer does not exist, the image is null,
   *                                  the image placed at (row, col) results in portions
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

  /**
   * Given the name of a layer to delete, deletes the layer from the collage project.
   *
   * @param layerName the name of the layer to delete.
   * @throws IllegalArgumentException if the layer does not exist.
   */
  void deleteLayer(String layerName) throws IllegalArgumentException;

  /**
   * Creates a new instance of the project, erasing all previous data stored in this project.
   *
   * @param width the width of the new project's canvas.
   * @param height the height of the new project's canvas.
   * @return the new project.
   * @throws IllegalArgumentException if the width or height are negative or zero.
   */
  ProjectModel<T> createProject(int width, int height) throws IllegalArgumentException;

  /**
   * Returns a collage project's default representation of an image.
   *
   * @param width the width of the image.
   * @param height the height of the image.
   * @return the default image representation.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */
  ImageModel<T> createDefaultImage(int width, int height) throws IllegalArgumentException;

  /**
   * Returns a collage project's representation of a pixel.
   *
   * @param position the position of the pixel.
   * @param color the color of the pixel.
   * @return the pixel.
   * @throws IllegalArgumentException if the position or color are null.
   */
  Pixel createPixel(Position2D position, ColorModel color) throws IllegalArgumentException;

  /**
   * Returns the filter based on the inputted name. A collage project can choose which filters
   * it will support in its implementation.
   *
   * @param name the name of the filter.
   * @return the filter.
   * @throws IllegalArgumentException if the given name does not exist because the collage
   * project does not support that filter.
   */
  Filter<T> getFilterFromName(String name) throws IllegalArgumentException;
}
