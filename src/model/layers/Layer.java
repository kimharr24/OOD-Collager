package model.layers;

import model.filters.Filter;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Represents a layer in the image collage application. A layer has a name, a filter applied
 * to it, and a stored image.
 */
public class Layer implements LayerModel<Pixel> {
  private String layerName;
  private Filter filter;
  private ImageModel<Pixel> image;

  /**
   * Constructor for a layer in the image collage application.
   * @param layerName the name of this layer.
   * @param filter the filter that should be applied to this layer.
   * @throws IllegalArgumentException if filter or image are null or image is of size 0.
   */
  public Layer(String layerName, Filter filter)
          throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Filter given to layer cannot be null!"), filter);
    this.layerName = layerName;
    this.filter = filter;
    // initialize the image here to a white transparent image
  }

  public String getLayerName() {
    return this.layerName;
  }

  @Override
  public ImageModel<Pixel> getImage() {
    return this.image;
  }

  public String getFilterName() {
    return this.filter.getName();
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }

  @Override
  public void setName(String name) {
    this.layerName = name;
  }
}
