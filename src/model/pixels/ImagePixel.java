package model.pixels;

import utils.Position2D;
import model.colors.ColorModel;
import model.filters.Filter;
import utils.Util;

/**
 * Represents a single pixel of an image. Combining multiple pixels into a grid will recover
 * the image representation.
 */
public class ImagePixel implements Pixel {
  private final Position2D position;
  private final ColorModel originalColor;
  private ColorModel color;

  /**
   * Instantiates an image pixel at a given position in a 2D grid with a color.
   * @param position the position on the image grid that this pixel is located.
   * @param color the color of this pixel.
   * @throws IllegalArgumentException if the position or color is null.
   */
  public ImagePixel(Position2D position, ColorModel color) {
    RuntimeException exception = new IllegalArgumentException("Null position or color given " +
            "to the constructor of image pixel.");
    Util.anyNull(exception, position, color);
    this.position = position;
    this.originalColor = color.createCopy();
    this.color = color;
  }

  public Position2D getPosition() {
    return new Position2D(this.position.getRow(), this.position.getCol());
  }

  public ColorModel getColor() {
    return this.color.createCopy();
  }

  public Pixel createCopy() {
    return new ImagePixel(this.getPosition(), this.getColor());
  }

  public void applyFilter(Filter filter) throws IllegalArgumentException {
    RuntimeException exception = new IllegalArgumentException("Filter applied to image pixel " +
            "cannot be null.");
    Util.anyNull(exception, filter);
    this.color = filter.apply(this.originalColor.createCopy());
  }
}
