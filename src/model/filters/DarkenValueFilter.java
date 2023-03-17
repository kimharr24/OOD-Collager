package model.filters;

import model.colors.ColorModel;

public class DarkenValueFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening by value filter. Sets the name of the
   * filter to "Darken Value Filter."
   */
  public DarkenValueFilter() {
    super("Darken Value Filter");
  }

  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.darkenValueColor();
  }

}
