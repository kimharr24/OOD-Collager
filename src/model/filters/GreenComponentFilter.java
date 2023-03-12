package model.filters;

import model.colors.ColorModel;

/**
 * Represents a green component filter, that is, a filter which keeps only the green channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (0, 20, 0, 200).
 */
public class GreenComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a green component filter names the green component filter
   * "Green Component Filter."
   */
  public GreenComponentFilter() {
    super("Green Component Filter");
  }

  public ColorModel apply(ColorModel color) {
    this.checkNullColor(color);
    return color.filterGreenChannel();
  }
}
