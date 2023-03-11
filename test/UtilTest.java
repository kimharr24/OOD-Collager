import org.junit.Test;

import utils.Util;

import static org.junit.Assert.fail;

/**
 * Testing class to ensure that methods in Util work as expected.
 */
public class UtilTest {
  @Test(expected = IllegalArgumentException.class)
  public void testOneNullArgument() {
    RuntimeException exception = new IllegalArgumentException("Null arguments not allowed.");
    Util.anyNull(exception, null, 3, 4, "hello");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMultipleNullArguments() {
    RuntimeException exception = new IllegalArgumentException("Null arguments not allowed.");
    Util.anyNull(exception, null, null, null);
  }

  @Test
  public void testAnyNullPass() {
    try {
      RuntimeException exception = new IllegalArgumentException("Null arguments not allowed.");
      Util.anyNull(exception, 1, -1, 3, 4, 4, 4);
      Util.anyNull(exception, "hello", "world");
    } catch (RuntimeException e) {
      fail("Expected code to run without runtime errors!");
    }
  }
}
