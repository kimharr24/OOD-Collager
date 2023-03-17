package model.filters;

import model.colors.ColorModel;

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

  /**
   * Applies the BrightenIntensityFilter() method to a given color.
   * @param color the color to apply the filter to.
   * @return a new color that has the BrightenIntensityFilter() method applied.
   * @throws IllegalArgumentException
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.brightenIntensityColor();
  }
}
