package model.filters;

import model.colors.ColorModel;

/**
 * Represents a blue component filter, that is, a filter which keeps only the blue channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (0, 0, 30, 200).
 */
public class BlueComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a blue component filter names the blue component filter
   * "Blue Component Filter."
   */
  public BlueComponentFilter() {
    super("Blue Component Filter");
  }

  /**
   * Applies the BlueComponentFilter() method to a given color.
   * @param color the color to apply the filter to.
   * @return a new color that has the BlueComponentFilter() method applied.
   * @throws IllegalArgumentException if the color is null.
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.filterBlueChannel();
  }
}
