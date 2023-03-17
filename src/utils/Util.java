package utils;

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
   * @param path the path to the file.
   * @return the file extension.
   */
  public static String getFileExtension(String path) {
    return path.substring(path.lastIndexOf('.') + 1);
  }

  public static int getMaxValueFromBits(int bits) {
    return (1 << bits) - 1;
  }

  public static int getMinValueFromBits(int bits) {
    return (1 >> bits) - 1;
  }
}
