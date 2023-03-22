package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that darkens the layer by subtracting the average value of the three color
 * components from each individual color component.
 */
public class DarkenIntensityFilter extends AbstractFilter {

  /**
   * Default constructor for a darkening intensity by value filter. Sets the name of the
   * filter to "Darken Intensity Filter."
   */
  public DarkenIntensityFilter() {
    super("Darken Intensity Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {

  }
}
