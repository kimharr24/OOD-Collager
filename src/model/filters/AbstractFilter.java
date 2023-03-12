package model.filters;

import model.colors.ColorModel;
import utils.Util;

/**
 * Abstract implementation of a concrete filter object. All filter objects
 * should instantiate their names using this abstract filter's constructor.
 */
public abstract class AbstractFilter implements Filter {
  private final String name;

  /**
   * Constructor for an abstract filter.
   * @param name the name of the filter.
   */
  public AbstractFilter(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Checks to ensure that the color given is not null.
   * @param color the color to check for nullity.
   * @throws IllegalArgumentException if the color is null.
   */
  protected void checkNullColor(ColorModel color) throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Cannot give a null color to image pixel when " +
            "applying a filter"), color);
  }
}
