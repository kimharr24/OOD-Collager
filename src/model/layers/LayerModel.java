package model.layers;

import model.filters.Filter;

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
  void setFilter(Filter filter) throws IllegalArgumentException;

  /**
   * Renames the current layer to the given name.
   * @param name the name to change this layer's name to.
   */
  void setName(String name);
}
