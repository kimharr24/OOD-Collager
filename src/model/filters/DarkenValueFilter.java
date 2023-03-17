package model.filters;

import model.colors.ColorModel;

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

  /**
   * Applies the darkenValueColor() filter to a given layer
   * @param color the color to apply the filter to.
   * @return the new color of the pixel
   * @throws IllegalArgumentException
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.darkenValueColor();
  }

}
