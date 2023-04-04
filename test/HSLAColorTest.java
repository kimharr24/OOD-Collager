import org.junit.Before;
import org.junit.Test;

import model.colors.HSLAColor;
import model.colors.RGBAColor;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class to ensure that an HSLAColor behaves as expected.
 */
public class HSLAColorTest {
  private HSLAColor color1;
  private HSLAColor color2;

  @Before
  public void init() {
    this.color1 = new HSLAColor(120, 0.22, 0.55, 1);
    this.color2 = new HSLAColor(65, 0.99, 0.365,
            Util.MAX_PROJECT_VALUE / 2.0);
  }

  @Test
  public void testConstructorInvalidHue() {
    try {
      HSLAColor color = new HSLAColor(-0.01, 0.5, 0.5, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      HSLAColor color = new HSLAColor(361, 0.5, 0.5, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testConstructorInvalidSaturation() {
    try {
      HSLAColor color = new HSLAColor(10, -0.025, 0.5, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      HSLAColor color = new HSLAColor(10, 1.05, 0.5, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testConstructorInvalidLightness() {
    try {
      HSLAColor color = new HSLAColor(10, 0.2, 1.5, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
    try {
      HSLAColor color = new HSLAColor(10, 0.2, -0.3, 10);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testConstructorInvalidAlpha() {
    try {
      HSLAColor color = new HSLAColor(10, 0.2, 0.2, -5);
      fail("Expected IllegalArgumentException.");
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testConstructorHueFloatingPointRounding() {
    try {
      HSLAColor color = new HSLAColor(-0.000000000001, 0.2, 0.3, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected hue to be validated.");
    }
    try {
      HSLAColor color = new HSLAColor(359.99999999999, 0.2, 0.3, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected hue to be validated.");
    }
  }

  @Test
  public void testConstructorSaturationFloatingPointRounding() {
    try {
      HSLAColor color = new HSLAColor(65, -0.0000000001, 0.3, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected saturation to be validated.");
    }
    try {
      HSLAColor color = new HSLAColor(65, 1.0000000000001, 0.3, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected saturation to be validated.");
    }
  }

  @Test
  public void testConstructorLightnessFloatingPointRounding() {
    try {
      HSLAColor color = new HSLAColor(65, 0.25, -0.0000000001, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected lightness to be validated.");
    }
    try {
      HSLAColor color = new HSLAColor(65, 0.36, 1.0000000000001, 10);
    } catch (IllegalArgumentException e) {
      fail("Expected lightness to be validated.");
    }
  }

  @Test
  public void testConstructorAlphaFloatingPointRounding() {
    try {
      HSLAColor color = new HSLAColor(65, 0.25, 0.5, -0.0000000001);
    } catch (IllegalArgumentException e) {
      fail("Expected lightness to be validated.");
    }
  }

  @Test
  public void testGetHue() {
    assertEquals(120, this.color1.getHue(), 0.00001);
    assertEquals(65, this.color2.getHue(), 0.00001);
  }

  @Test
  public void testGetSaturation() {
    assertEquals(0.22, this.color1.getSaturation(), 0.00001);
    assertEquals(0.99, this.color2.getSaturation(), 0.00001);
  }

  @Test
  public void testGetLightness() {
    assertEquals(0.55, this.color1.getLightness(), 0.00001);
    assertEquals(0.365, this.color2.getLightness(), 0.00001);
  }

  @Test
  public void testGetAlpha() {
    assertEquals(1, this.color1.getAlpha(), 0.00001);
    assertEquals(Util.MAX_PROJECT_VALUE / 2.0, this.color2.getAlpha(), 0.00001);
  }

  @Test
  public void testConvertHSLAToRGBA() {
    assertEquals(new RGBAColor(115.005, 165.495, 115.005, 255.0),
            HSLAColor.convertHSLAtoRGBA(this.color1.getHue(),
                    this.color1.getSaturation(), this.color1.getLightness(),
                    this.color1.getAlpha()));
  }
}

