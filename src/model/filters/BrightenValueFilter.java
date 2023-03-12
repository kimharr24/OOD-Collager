package model.filters;

import model.colors.ColorModel;

/**
 * Represents a filter that brightens by the maximum value across all color components.
 * For example, the RGBA color (10, 20, 30, 40) becomes (40, 50, 60, 40) since
 * max(10, 20, 30) = 30. The alpha value is unchanged.
 */
public class BrightenValueFilter extends AbstractFilter {
  /**
   * Default constructor for a brightening by value filter. Sets the name of the
   * filter to "Brighten Value Filter."
   */
  public BrightenValueFilter() {
    super("Brighten Value Filter");
  }

  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.brightenValueColor();
  }
}
