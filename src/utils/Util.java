package utils;

import java.awt.image.BufferedImage;

import model.colors.ColorModel;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Utility class for any common methods used by the model, controller, or
 * view in the image collage project.
 */
public class Util {
  public static int MAX_PROJECT_VALUE = 255;
  public static String[] FILTER_OPTIONS = {"normal", "red-component", "blue-component",
      "green-component", "brighten-value", "darken-value", "brighten-intensity",
      "darken-intensity", "brighten-luma", "darken-luma", "inversion-blending",
      "darkening-blending", "brightening-blending"};

  public static void setMaxValue(int maxValue) {
    Util.MAX_PROJECT_VALUE = maxValue;
  }

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
   * Creates a java.awt.BufferedImage from integer RGBA values.
   * @param compositeImage the image to convert into a java.awt buffered image.
   * @param bufferedImage a blank buffered image to populate with the composite image pixels.
   * @return the java.awt.Image.
   */
  public static BufferedImage createImageFromScratch(ImageModel<Pixel> compositeImage,
                                                     BufferedImage bufferedImage) {
    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++) {
        ColorModel currentPixelColor = compositeImage.getPixelAtCoord(y, x).getColor();
        int r = (int) currentPixelColor.getRedComponent();
        int g = (int) currentPixelColor.getGreenComponent();
        int b = (int) currentPixelColor.getBlueComponent();
        int a = (int) currentPixelColor.getAlphaComponent();

        int argb = a << 24;
        argb |= r << 16;
        argb |= g << 8;
        argb |= b;
        bufferedImage.setRGB(x, y, argb);
      }
    }
    return bufferedImage;
  }
}