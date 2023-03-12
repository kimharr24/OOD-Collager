package model;

import model.colors.ColorModel;
import model.filters.Filter;
import utils.Util;

/**
 * Represents a single pixel of an image. Combining multiple pixels into a grid will recover
 * the image representation.
 */
public class ImagePixel {
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

  /**
   * Returns the position of this pixel within the image grid.
   * @return this pixel's position.
   */
  public Position2D getPosition() {
    return new Position2D(this.position.getRow(), this.position.getCol());
  }

  /**
   * Returns the color of this pixel.
   * @return the color of the pixel.
   */
  public ColorModel getColor() {
    return this.color.createCopy();
  }

  /**
   * Applies the provided filter on this image pixel, changing its color.
   *
   * The filter should never modify this class's originalColor field. Hence, a defensive
   * copy of the original color is passed to the filter.
   *
   * @param filter the filter to be applied to this image pixel.
   * @throws IllegalArgumentException if the filter is null.
   */
  public void applyFilter(Filter filter) throws IllegalArgumentException {
    RuntimeException exception = new IllegalArgumentException("Filter applied to image pixel " +
            "cannot be null.");
    Util.anyNull(exception, filter);
    this.color = filter.apply(this.originalColor.createCopy());
  }
}
