package model.colors;

/**
 * Represents a color that is represented by a hue, saturation, lightness, and alpha.
 */
public final class HSLAColor {
  private final double hue;
  private final double saturation;
  private final double lightness;
  private final double alpha;

  /**
   * Constructor for an HSLA color.
   * @param hue the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness the lightness of the color.
   * @param alpha the transparency of the color.
   * @throws IllegalArgumentException if hue is not in the range [0, 360) or saturation is not
   * in the range [0, 1] or lightness is not in the range [0, 1] or alpha is less than 0.
   */
  public HSLAColor(double hue, double saturation, double lightness, double alpha)
          throws IllegalArgumentException {
    if (hue < 0 || hue >= 360) {
      throw new IllegalArgumentException("Hue must be in the range [0, 360).");
    }
    if (lightness < 0 || lightness > 1 || saturation < 0 || saturation > 1) {
      throw new IllegalArgumentException("Saturation and lightness must be in the range [0, 1].");
    }
    if (alpha < 0) {
      throw new IllegalArgumentException("Alpha must be greater than or equal to zero.");
    }
    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
    this.alpha = alpha;
  }

  /**
   * Returns the hue of the color.
   * @return the hue of the color.
   */
  public double getHue() {
    return this.hue;
  }

  /**
   * Returns the saturation of the color.
   * @return the saturation of the color.
   */
  public double getSaturation() {
    return this.saturation;
  }

  /**
   * Returns the lightness of the color.
   * @return the lightness of the color.
   */
  public double getLightness() {
    return this.lightness;
  }

  /**
   * Returns the alpha of the color.
   * @return the alpha of the color.
   */
  public double getAlpha() {
    return this.alpha;
  }
}
