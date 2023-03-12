package model.filters;

import model.colors.ColorModel;

/**
 * Represents a normal filter, that is, a filter which does not do
 * anything to the layer it is applied to.
 */
public class NormalFilter extends AbstractFilter {
  /**
   * Default constructor for a normal filter. The default constructor
   * names this filter "Normal Filter."
   */
  public NormalFilter() {
    super("Normal Filter");
  }

  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color;
  }
}
