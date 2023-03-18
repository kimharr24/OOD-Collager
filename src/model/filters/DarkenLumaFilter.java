package model.filters;

import model.colors.ColorModel;

/**
 * Represents the filter that darkens the layer by subtracting the luma value from each of the
 * individual color components.
 */
public class DarkenLumaFilter extends AbstractFilter {

  /**
   * Default constructor for a darkening using the luma value filter. Sets the name of the
   * filter to "Darken Luma Filter."
   */
  public DarkenLumaFilter() {
    super("Darken Luma Filter");
  }

  /**
   * Applies the darkenLumaColor() filter to a given layer
   * @param color the color to apply the filter to.
   * @return the new color of the pixel
   * @throws IllegalArgumentException if the color is null.
   */
  @Override
  public ColorModel apply(ColorModel color) throws IllegalArgumentException {
    this.checkNullColor(color);
    return color.darkenLumaColor();
  }
}