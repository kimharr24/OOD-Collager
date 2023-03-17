package model.filters;

import model.colors.ColorModel;

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

  /**
   * Applies the BrightenLumaFilter() method to a given color.
   * @param color the color to apply the filter to.
   * @return a new color that has the BrightenLumaFilter() method applied.
   * @throws IllegalArgumentException
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.brightenLumaColor();
  }
}
