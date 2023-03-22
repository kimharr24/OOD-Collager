package model.pixels;

import utils.Position2D;
import model.colors.ColorModel;

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
   * Creates a copy of this pixel.
   * @return a copy of the pixel.
   */
  Pixel createCopy();

  /**
   * Sets the color of the pixel to the given pixel.
   * @param color the color to update the current pixel with.
   * @throws IllegalArgumentException if the given color is null.
   */
  void setColor(ColorModel color) throws IllegalArgumentException;

  /**
   * Returns the original color of the pixel.
   * @return the original pixel color.
   */
  ColorModel getOriginalColor();
}
