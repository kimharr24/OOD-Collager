package model.filters;

import model.colors.ColorModel;

/**
 * Represents a red component filter, that is, a filter which keeps only the red channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (10, 0, 0, 200).
 */
public class RedComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a red component filter names the red component filter
   * "Red Component Filter."
   */
  public RedComponentFilter() {
    super("Red Component Filter");
  }

  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.filterRedChannel();
  }
}
