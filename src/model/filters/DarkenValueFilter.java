package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that darkens the given layer by subtracting the minimum value across all
 * color components, to each individual color component.
 */
public class DarkenValueFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening by value filter. Sets the name of the
   * filter to "Darken Value Filter."
   */
  public DarkenValueFilter() {
    super("Darken Value Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {

  }
}
