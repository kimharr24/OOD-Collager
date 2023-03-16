package utils;

/**
 * Utility class for any common methods used by the model, controller, or
 * view in the image collage project.
 */
public class Util {
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

  public static int getMaxValueFromBits(int bits) {
    return (1 << bits) - 1;
  }
}
