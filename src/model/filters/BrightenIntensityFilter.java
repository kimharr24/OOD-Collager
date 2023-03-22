package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that brightens the layer by adding the average value of the three color
 * components to each individual color component.
 */
public class BrightenIntensityFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening intensity by value filter. Sets the name of the
   * filter to "Brighten Intensity Filter."
   */
  public BrightenIntensityFilter() {
    super("Brighten Intensity Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {

  }
}
