package model.layers;

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
   * Returns a copy of the image stored in the current layer.
   * @return the stored image.
   */
  ImageModel<T> getImage();
}
