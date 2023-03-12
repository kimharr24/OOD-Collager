package model.colors;

/**
 * Represents all operations that can be performed on a color, including brightening and
 * darkening operations. All filter-like operations are delegated to the ColorModel.
 */
public interface ColorModel extends ColorState {
  /**
   * Creates a copy of the color to prevent accidental mutations when passing the color
   * between multiple methods.
   * @return a copy of the color's representation.
   */
  ColorModel createCopy();

  /**
   * Filters the color by only keeping its red component. All other components (besides alpha)
   * are set to zero.
   * @return the updated color with only its red component.
   */
  ColorModel filterRedChannel();
}
