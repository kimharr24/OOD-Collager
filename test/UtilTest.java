import org.junit.Test;

import utils.Util;

import static org.junit.Assert.assertEquals;
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

  @Test
  public void testGetFileExtension() {
    assertEquals("ppm", Util.getFileExtension("./src/game.ppm"));
    assertEquals("png", Util.getFileExtension("./src/algo/hello/something.png"));
    assertEquals("jpg", Util.getFileExtension("../../../Desktop/lol.jpg"));
  }

  @Test
  public void testConvertColorScale() {
    assertEquals(255, Util.convertColorScale(Util.MAX_PROJECT_VALUE, 24, 24), 0.001);
    assertEquals(0, Util.convertColorScale(Util.MAX_PROJECT_VALUE, 30, 0), 0.001);
    assertEquals(211.0344, Util.convertColorScale(Util.MAX_PROJECT_VALUE, 29, 24), 0.001);
    assertEquals(42.5, Util.convertColorScale(Util.MAX_PROJECT_VALUE, 60, 10), 0.001);
  }

//  @Test
//  public void testValidateRGBAParametersPass() {
//    try {
//      Util.validateRGBAParameters(Util.MAX_PROJECT_VALUE + 0.000000001,
//              Util.MAX_PROJECT_VALUE + 0.000000001,
//              Util.MAX_PROJECT_VALUE + 0.000000001,
//              Util.MAX_PROJECT_VALUE + 0.000000001);
//    } catch (IllegalArgumentException e) {
//      fail("Expected RGBA parameters to pass validation.");
//    }
//    try {
//      Util.validateRGBAParameters(Util.MAX_PROJECT_VALUE,
//              Util.MAX_PROJECT_VALUE,
//              Util.MAX_PROJECT_VALUE,
//              Util.MAX_PROJECT_VALUE);
//    } catch (IllegalArgumentException e) {
//      fail("Expected RGBA parameters to pass validation.");
//    }
//    try {
//      Util.validateRGBAParameters(0.0000001, Util.MAX_PROJECT_VALUE / 2.0,
//              Util.MAX_PROJECT_VALUE / 3.0, Util.MAX_PROJECT_VALUE / 4.0);
//    } catch (IllegalArgumentException e) {
//      fail("Expected RGBA parameters to pass validation.");
//    }
//  }
//
//  @Test
//  public void testValidateRGBAParametersFail() {
//    try {
//      Util.validateRGBAParameters(-0.00000000001, 0, 0, Util.MAX_PROJECT_VALUE);
//      fail("Expected RGBA parameters to fail validation.");
//    } catch (RuntimeException ignored) {
//    }
//    try {
//      Util.validateRGBAParameters(0, 0, 0, Util.MAX_PROJECT_VALUE + 0.2);
//      fail("Expected RGBA parameters to fail validation.");
//    } catch (RuntimeException ignored) {
//    }
//    try {
//      Util.validateRGBAParameters(-10, -20, -30, Util.MAX_PROJECT_VALUE);
//      fail("Expected RGBA parameters to fail validation.");
//    } catch (RuntimeException ignored) {
//    }
//    try {
//      Util.validateRGBAParameters(Util.MAX_PROJECT_VALUE * 2, 0, 0, Util.MAX_PROJECT_VALUE);
//      fail("Expected RGBA parameters to fail validation.");
//    } catch (RuntimeException ignored) {
//    }
//  }
}
