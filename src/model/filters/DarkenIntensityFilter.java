package model.filters;

import model.colors.ColorModel;

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

  /**
   * Applies the DarkenIntensityFilter() method to a given color.
   * @param color the color to apply the filter to.
   * @return a new color that has the DarkenIntensityFilter() method applied.
   * @throws IllegalArgumentException if the color is null.
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.darkenIntensityColor();
  }
}
