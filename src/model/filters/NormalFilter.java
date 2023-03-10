package model.filters;

import model.colors.Color;

/**
 * Represents a normal filter, that is, a filter which does not do
 * anything to the layer it is applied to.
 */
public class NormalFilter implements Filter {
  private final String name;

  /**
   * Default constructor for a normal filter. The default constructor
   * names this filter "Normal."
   */
  public NormalFilter() {
    this.name = "Normal";
  }

  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Executes the normal filter on a given color. The normal filter does nothing.
   * @param color the color to apply the filter to.
   * @throws IllegalArgumentException if the color is null.
   */
  @Override
  public void applyFilter(Color color) throws IllegalArgumentException {

  }
}
