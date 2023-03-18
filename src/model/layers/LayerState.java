package model.layers;

import model.filters.Filter;
import model.images.ImageModel;

/**
 * Represents an observable layer object. Methods can be used to view the state of a layer
 * without performing any modifications.
 * @param <T> the representation of an image's pixel.
 */
public interface LayerState<T> {
  /**
   * Returns the name of the layer.
   * @return the name of the layer.
   */
  String getLayerName();

  /**
   * Returns the name of the filter associated with this layer.
   * @return the name of the filter.
   */
  String getFilterName();

  /**
   * Returns a copy of the image stored in the current layer.
   * @return the stored image.
   */
  ImageModel<T> getImage();

  /**
   * Returns the filter associated with the current layer.
   * @return the filter.
   */
  Filter getFilter();
}
