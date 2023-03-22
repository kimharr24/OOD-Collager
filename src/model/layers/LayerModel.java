package model.layers;

import model.filters.Filter;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a mutable layer object. Methods can be used to update a layer's properties.
 * @param <T> the representation of an image's pixel.
 */
public interface LayerModel<T> extends LayerState<T> {
  /**
   * Sets the current layer to the given filter.
   * @param filter the filter to set the current layer to.
   * @throws IllegalArgumentException if the given filter is null.
   */
  void setFilter(Filter<T> filter) throws IllegalArgumentException;

  /**
   * Renames the current layer to the given name.
   * @param name the name to change this layer's name to.
   */
  void setName(String name);

  /**
   * Applies the filter in a layer given the composite image of the layers below
   * the current layer.
   * @param compositeImage the composite image below the current layer.
   * @throws IllegalArgumentException if compositeImage is null or the compositeImage is not
   * the same size as the current layer's image.
   */
  void applyFilter(ImageModel<Pixel> compositeImage);
}
