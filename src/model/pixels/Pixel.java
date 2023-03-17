package model.pixels;

import utils.Position2D;
import model.colors.ColorModel;
import model.filters.Filter;

/**
 * Represents a generic computer graphical pixel. All pixels should contain a color and
 * a position on the screen.
 */
public interface Pixel {
  /**
   * Returns the position of the pixel on the screen.
   * @return the position of the pixel.
   */
  Position2D getPosition();

  /**
   * Returns the color of the pixel.
   * @return the color of the pixel.
   */
  ColorModel getColor();

  /**
   * Applies the given filter to the pixel.
   * @param filter the filter to apply on the current pixel.
   * @throws IllegalArgumentException if the given filter is null.
   */
  void applyFilter(Filter filter) throws IllegalArgumentException;
}
