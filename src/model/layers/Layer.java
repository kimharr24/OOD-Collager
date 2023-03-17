package model.layers;

import model.filters.Filter;
import model.images.Image;
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
   * Constructor for a layer in the image collage application. By default, when a layer is
   * constructed, the image is fully white and transparent. This default behavior is ensured
   * by the constructor of the Image class.
   *
   * @param layerName the name of this layer.
   * @param filter the filter that should be applied to this layer.
   * @param width the width of the layer.
   * @param height the height of the layer.
   * @throws IllegalArgumentException if filter is null or the width and height are less than
   * or equal to zero.
   */
  public Layer(String layerName, Filter filter, int width, int height)
          throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Filter given to layer cannot be null!"), filter);
    this.layerName = layerName;
    this.filter = filter;
    this.image = new Image(width, height);
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
