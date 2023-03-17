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

  /**
   * Filters the color by only keeping its green component. All other components (besides alpha)
   * are set to zero.
   * @return the updated color with only its green component.
   */
  ColorModel filterGreenChannel();

  /**
   * Filters the color by only keeping its blue component. All other components (besides alpha)
   * are set to zero.
   * @return the updated color with only its blue component.
   */
  ColorModel filterBlueChannel();

  /**
   * Brightens the color by increasing each component by the maximum value across all components.
   * Each component will never become larger than the number of bits used for representation.
   * @return the updated color after the brightening operation is applied.
   */
  ColorModel brightenValueColor();

  /**
   * Darkens the color by decreasing each component by the minimum value across all components.
   * Each component will never become larger than the number of bits used for representation.
   * @return the updated color after the darkening operation is applied.
   */
  ColorModel darkenValueColor();

  /**
   * Increases the intensity of the color.
   * @return the updated color after the brightening operation is applied.
   */
  ColorModel brightenIntensityColor();

  /**
   * Decreases the intensity of the color.
   * @return the updated color after the darkening operation is applied.
   */
  ColorModel darkenIntensityColor();

  /**
   * brightens the luma by increasing the luma value of the color.
   * @return the updated color after the darkening operation is applied.
   */
  ColorModel brightenLumaColor();

  /**
   *
   * @return
   */
  ColorModel darkenLumaColor();

  /**
   * Given the background color, updates this color based on the values of alpha.
   * @param color the background color.
   * @return the updated color.
   * @throws IllegalArgumentException if the given color is null.
   */
  ColorModel getUpdatedColor(ColorModel color);
}