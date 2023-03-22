package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that brightens the layer by adding the luma value to each of the
 * individual color components.
 */
public class BrightenLumaFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening luma by value filter. Sets the name of the
   * filter to "Brighten Luma Filter."
   */
  public BrightenLumaFilter() {
    super("Brighten Luma Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {

  }
}
