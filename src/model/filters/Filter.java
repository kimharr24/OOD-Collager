package model.filters;

import model.colors.Color;

/**
 * Represents a generic filter operation that can be applied to a layer
 * in an image. All filters have a name and a method for execution.
 */
public interface Filter {
  /**
   * Retrieves the name of the filter operation.
   * @return the name of the filter operation.
   */
  String getName();

  /**
   * Given a color, applies the filter to the color.
   * @param color the color to apply the filter to.
   * @throws IllegalArgumentException if the given color is null.
   */
  void applyFilter(Color color) throws IllegalArgumentException;
}
