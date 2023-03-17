package model.colors;

/**
 * Represents an observable, generic color. This color cannot be modified by any of the methods
 * in this interface.
 *
 * Assumes that all implementations of colors have red, green, blue, and alpha components.
 * Every color also has a finite number of bits used for representation, implying that the color
 * has a corresponding maximum integer value.
 */
public interface ColorState {
  /**
   * Retrieves the red component of the color.
   * @return the integer value of the red component.
   */
  double getRedComponent();

  /**
   * Retrieves the green component of the color.
   * @return the integer value of the green component.
   */
  double getGreenComponent();

  /**
   * Retrieves the blue component of the color.
   * @return the integer value of the blue component.
   */
  double getBlueComponent();

  /**
   * Retrieves the alpha component of the color.
   * @return the integer value of the alpha component.
   */
  double getAlphaComponent();
}
