package model.colors;

import utils.Util;

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
   *
   * @param hue        the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness  the lightness of the color.
   * @param alpha      the transparency of the color.
   * @throws IllegalArgumentException if hue is not in the range [0, 360) or saturation is not
   *                                  in the range [0, 1] or lightness is not in the range [0, 1]
   *                                  or alpha is less than 0.
   */
  public HSLAColor(double hue, double saturation, double lightness, double alpha)
          throws IllegalArgumentException {
    hue = this.snapHSLAParameter(hue);
    saturation = this.snapHSLAParameter(saturation);
    lightness = this.snapHSLAParameter(lightness);
    alpha = this.snapHSLAParameter(alpha);
    this.validateHSLAParameters(hue, saturation, lightness, alpha);

    this.hue = hue;
    this.saturation = saturation;
    this.lightness = lightness;
    this.alpha = alpha;
  }

  /**
   * If the inputted HSLA component is close enough to a known boundary condition, snaps
   * the HSLA component to that boundary condition.
   *
   * @param parameter the HSLA component.
   * @return the potentially snapped HSLA component.
   */
  private double snapHSLAParameter(double parameter) {
    double epsilon = 0.00001;
    if (Math.abs(parameter) < epsilon) {
      return 0.0;
    }
    if (Math.abs(parameter - 1) < epsilon) {
      return 1.0;
    }
    return parameter;
  }

  private void validateHSLAParameters(double hue, double saturation, double lightness, double alpha)
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
  }

  /**
   * Converts an RGBAColor to an HSLAColor.
   *
   * @param hue        the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness  the lightness of the color.
   * @param alpha      the transparency of the color.
   * @return the RGBAColor conversion.
   * @throws IllegalArgumentException if the hue is not in the range [0, 360) or the saturation/
   *                                  lightness is not in the range [0, 1], or the alpha is
   *                                  negative.
   */
  public static RGBAColor convertHSLAtoRGBA(double hue, double saturation, double lightness,
                                            double alpha) throws IllegalArgumentException {

    double r = convertFn(hue, saturation, lightness, 0) * Util.MAX_PROJECT_VALUE;
    double g = convertFn(hue, saturation, lightness, 8) * Util.MAX_PROJECT_VALUE;
    double b = convertFn(hue, saturation, lightness, 4) * Util.MAX_PROJECT_VALUE;

    return new RGBAColor(r, g, b, alpha * Util.MAX_PROJECT_VALUE);
  }

  /**
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model.
   *
   * @param hue        the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness  the lightness of the color.
   * @param n          number of sides.
   * @return the updated RGB value.
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }

  /**
   * Returns the hue of the color.
   *
   * @return the hue of the color.
   */
  public double getHue() {
    return this.hue;
  }

  /**
   * Returns the saturation of the color.
   *
   * @return the saturation of the color.
   */
  public double getSaturation() {
    return this.saturation;
  }

  /**
   * Returns the lightness of the color.
   *
   * @return the lightness of the color.
   */
  public double getLightness() {
    return this.lightness;
  }

  /**
   * Returns the alpha of the color.
   *
   * @return the alpha of the color.
   */
  public double getAlpha() {
    return this.alpha;
  }
}
