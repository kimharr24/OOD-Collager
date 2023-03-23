package utils;

import model.colors.HSLAColor;
import model.colors.RGBAColor;

/**
 * Utility class for any common methods used by the model, controller, or
 * view in the image collage project.
 */
public class Util {
  public static int MAX_PROJECT_VALUE = 255;

  /**
   * Checks to see whether any of the provided arguments are null.
   *
   * @param exception the exception to throw if any of the arguments are null.
   * @param args      the arguments to test for null values.
   * @param <T>       any type for checking null values.
   * @throws RuntimeException if any of the provided args are null.
   */
  public static <T> void anyNull(RuntimeException exception, T... args) throws RuntimeException {
    for (T arg : args) {
      if (arg == null) {
        throw exception;
      }
    }
  }

  /**
   * Returns the extension of a file given a path to the file.
   *
   * @param path the path to the file.
   * @return the file extension.
   */
  public static String getFileExtension(String path) {
    return path.substring(path.lastIndexOf('.') + 1);
  }

  /**
   * Given a color whose value is oldValue, and whose scale is defined by [0, oldMaxValue],
   * converts the color to this collage project's scale [0, MAX_PROJECT_VALUE].
   * Modified equation referenced from https://stackoverflow.com/questions/929103/
   * convert-a-number-range-to-another-range-maintaining-ratio
   *
   * @param newMaxValue the maximum value of the current color scale.
   * @param oldMaxValue the maximum value of the old color scale.
   * @param oldValue    the value of the old color.
   * @return the updated version of the old color, scaled to this project's scale.
   * @throws IllegalArgumentException if oldMaxValue is negative or if oldValue is negative.
   */
  public static double convertColorScale(int newMaxValue, int oldMaxValue, int oldValue)
          throws IllegalArgumentException {
    if (oldMaxValue < 0 || oldValue < 0) {
      throw new IllegalArgumentException("Max value or given value cannot be negative.");
    }
    return ((double) oldValue * newMaxValue) / oldMaxValue;
  }

  /**
   * Converts an RGBAColor to an HSLAColor.
   * @param r the red component.
   * @param g the green component.
   * @param b the blue component.
   * @param a the alpha component.
   * @return the converted HSLA color.
   * @throws IllegalArgumentException if r, g, b, or a are less than 0 or r, g, b, or a are
   * greater than the maximum project value.
   */
  public static HSLAColor convertRGBAtoHSLA(double r, double g, double b, double a)
          throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || a < 0) {
      throw new IllegalArgumentException("RGBA must be greater than or equal to zero.");
    }
    if (r > Util.MAX_PROJECT_VALUE || g > Util.MAX_PROJECT_VALUE || b > Util.MAX_PROJECT_VALUE
            || a > Util.MAX_PROJECT_VALUE) {
      throw new IllegalArgumentException(String.format("RGBA must contain components with " +
              "values less than or equal to %d", Util.MAX_PROJECT_VALUE));
    }
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin)/2;
    double hue, saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b)/delta;
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r)/delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g)/delta;
        hue += 4;
      }
      hue = hue * 60;
    }
//    System.out.println("red:" + r + " green: " + g + " blue: " + b);
//    System.out.println("hue: " + hue + " saturation: " + saturation + " lightness: " + lightness);
    return new HSLAColor(hue, saturation, lightness, a);
  }

  /**
   * Converts an RGBAColor to an HSLAColor.
   * @param hue the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness the lightness of the color.
   * @param alpha the transparency of the color.
   * @return the RGBAColor conversion.
   * @throws IllegalArgumentException if the hue is not in the range [0, 360) or the saturation/
   * lightness is not in the range [0, 1], or the alpha is negative.
   */
  public static RGBAColor convertHSLAtoRGBA(double hue, double saturation, double lightness,
                                            double alpha) throws IllegalArgumentException {
    if (hue < 0 || hue >= 360) {
      throw new IllegalArgumentException("Hue must be in the range [0, 360).");
    }
    if (lightness < 0 || lightness > 1 || saturation < 0 || saturation > 1) {
      throw new IllegalArgumentException("Saturation and lightness must be in the range [0, 1].");
    }
    if (alpha < 0) {
      throw new IllegalArgumentException("Alpha must be greater than or equal to zero.");
    }

    double r = convertFn(hue, saturation, lightness, 0) * Util.MAX_PROJECT_VALUE;
    double g = convertFn(hue, saturation, lightness, 8) * Util.MAX_PROJECT_VALUE;
    double b = convertFn(hue, saturation, lightness, 4) * Util.MAX_PROJECT_VALUE;

    return new RGBAColor(r, g, b, alpha * Util.MAX_PROJECT_VALUE);
  }

  /**
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model
   * @param hue the hue of the color.
   * @param saturation the saturation of the color.
   * @param lightness the lightness of the color.
   * @param n number of sides.
   * @return the updated RGB value.
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue/30)) % 12;
    double a  = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }
}