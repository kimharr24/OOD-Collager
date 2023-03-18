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

  /**
   * Applies the normalFilter() filter to the given layer.
   * @param color the color to apply the filter to.
   * @return a white and transparent layer.
   * @throws IllegalArgumentException if the color is null.
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color;
  }
}
