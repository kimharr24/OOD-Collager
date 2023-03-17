package model.colors;

import utils.Util;

/**
 * Represents an RGBA (red, blue, green, alpha) color. Color formats that do not
 * have alpha channels can adhere to the RGBA color by using a default value of alpha or
 * inferring the value of alpha.
 */
public class RGBAColor implements ColorModel {
  private double red;
  private double green;
  private double blue;
  private double alpha;

  /**
   * Validates the inputs to the RGBA constructor.
   *
   * @param red   the red component of the color.
   * @param green the green component of the color.
   * @param blue  the blue component of the color.
   * @param alpha the alpha component of the color.
   * @throws IllegalArgumentException if red, green, blue, or alpha are less than 0, the number
   *                                  of bits is less than or equal to 0, or RGBA uses an invalid
   *                                  integer given the number of bits.
   */
  private void validateConstructor(double red, double green, double blue, double alpha) {
    if (red < 0 || green < 0 || blue < 0 || alpha < 0) {
      throw new IllegalArgumentException("Negative values of RGBA not allowed.");
    }
    int maxValue = Util.MAX_PROJECT_VALUE;
    if (red > maxValue || green > maxValue || blue > maxValue || alpha > maxValue) {
      throw new IllegalArgumentException(String.format("RGBA cannot have a value greater than %d",
              maxValue));
    }
  }

  /**
   * Constructor for an RGBA color. The number of bits used to represent a color
   * can be specified by a client.
   *
   * @param red   the red component of the color.
   * @param green the green component of the color.
   * @param blue  the blue component of the color.
   * @param alpha the alpha component of the color.
   * @throws IllegalArgumentException if red, green, blue, or alpha are less than 0, the number
   *                                  of bits is less than or equal to 0, or RGBA uses an
   *                                  invalid integer given the number of bits.
   */
  public RGBAColor(double red, double green, double blue, double alpha)
          throws IllegalArgumentException {
    this.validateConstructor(red, green, blue, alpha);
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Gets the red component of this color.
   * @return the red component.
   */
  @Override
  public double getRedComponent() {
    return this.red;
  }

  /**
   * Gets the green component of this color.
   * @return the green component.
   */
  @Override
  public double getGreenComponent() {
    return this.green;
  }

  /**
   * Gets the blue component from this color.
   * @return the blue component.
   */
  @Override
  public double getBlueComponent() {
    return this.blue;
  }

  /**
   * Gets the alpha component of this color.
   * @return the alpha component.
   */
  @Override
  public double getAlphaComponent() {
    return this.alpha;
  }

  /**
   * Creates a copy of a given color.
   * @return a new Color with the same color components as the specified color.
   */
  @Override
  public ColorModel createCopy() {
    return new RGBAColor(this.red, this.green, this.blue, this.alpha);
  }

  /**
   * Creates a new color based purely off of the red color component of the specific color.
   * @return a new color identical to the given color, but with the green and blue components
   *       set to zero.
   */
  @Override
  public ColorModel filterRedChannel() {
    return new RGBAColor(this.red, 0, 0, this.alpha);
  }

  /**
   * Creates a new color based purely off of the blue color component of the specific color.
   * @return a new color identical to the given color, but with the green and red components
   *       set to zero.
   */
  @Override
  public ColorModel filterGreenChannel() {
    return new RGBAColor(0, this.green, 0, this.alpha);
  }

  /**
   * Creates a new color based purely off of the green color component of the specific color.
   * @return a new color identical to the given color, but with the red and blue components
   *       set to zero.
   */
  @Override
  public ColorModel filterBlueChannel() {
    return new RGBAColor(0, 0, this.blue, this.alpha);
  }

  /**
   * brightens the layer by increasing the value of each of the pixel's color components.
   * @return a new color for each pixel in the given layer.
   */
  @Override
  public ColorModel brightenValueColor() {
    double maxValue = Math.max(Math.max(this.red, this.green), this.blue);

    return new RGBAColor(Math.min(this.red + maxValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.green + maxValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.blue + maxValue, Util.MAX_PROJECT_VALUE),
            this.alpha);
  }

  /**
   * Darkens the layer by decreasing the value of each of the pixel's color components.
   * @return a new color for each pixel in the given layer.
   */
  @Override
  public ColorModel darkenValueColor() {
    double maxValue = Math.max(Math.max(this.red, this.green), this.blue);

    return new RGBAColor(Math.max(this.red - maxValue, 0),
            Math.max(this.green - maxValue, 0),
            Math.max(this.blue - maxValue, 0),
            this.alpha);
  }

  /**
   * Increases the intensity of the color.
   * @return the updated color after the brightening operation is applied.
   */
  public ColorModel brightenIntensityColor() {
    double avgValue = (this.red + this.blue + this.green) / 3;

    return new RGBAColor(Math.min(this.red + avgValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.green + avgValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.blue + avgValue, Util.MAX_PROJECT_VALUE),
            this.alpha);
  }

  /**
   * Decreases the intensity of the color.
   * @return the updated color after the darkening operation is applied.
   */
  public ColorModel darkenIntensityColor() {
    double avgValue = (this.red + this.blue + this.green) / 3;

    return new RGBAColor(Math.max(this.red - avgValue, 0),
            Math.max(this.green - avgValue, 0),
            Math.max(this.blue - avgValue, 0),
            this.alpha);
  }

  /**
   * Brightens the color of each pixel in a layer using luma by increasing the luma value of each
   *       color component.
   * @return the updated color after the brightening operation is applied.
   */
  public ColorModel brightenLumaColor() {
    double lumaValue = (this.red * 0.2126 + this.blue * 0.0722 + this.green * 0.7152);

    return new RGBAColor(Math.min(this.red + lumaValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.green + lumaValue, Util.MAX_PROJECT_VALUE),
            Math.min(this.blue + lumaValue, Util.MAX_PROJECT_VALUE),
            this.alpha);
  }

  /**
   * Darkens the color of each pixel in a layer using luma by decreasing the luma value of each
   *       color component.
   * @return the updated color after the darkening operation is applied.
   */
  public ColorModel darkenLumaColor() {
    double lumaValue = (this.red * 0.2126 + this.blue * 0.0722 + this.green * 0.7152);

    return new RGBAColor(Math.max(this.red + lumaValue, 0),
            Math.max(this.green + lumaValue, 0),
            Math.max(this.blue + lumaValue, 0),
            this.alpha);
  }
}
