import org.junit.Before;
import org.junit.Test;

import model.colors.RGBAColor;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Testing class for an image pixel.
 */
public class PixelTest {
  Pixel pixel1;
  Pixel pixel2;

  @Before
  public void init() {
    this.pixel1 = new ImagePixel(new Position2D(10, 20), new RGBAColor(1, 2, 3, 4));
    this.pixel2 = new ImagePixel(new Position2D(66, 34), new RGBAColor(100, 25, 65, 120));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPosition() {
    Pixel pixel = new ImagePixel(null, new RGBAColor(1, 2, 3, 4));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullColor() {
    Pixel pixel = new ImagePixel(new Position2D(10, 20), null);
  }

  @Test
  public void testConstructorValidArguments() {
    try {
      Pixel pixel = new ImagePixel(new Position2D(30, 60), new RGBAColor(5, 2, 3, 4));
    } catch (RuntimeException e) {
      fail("Expected successful initialization.");
    }
  }

  @Test
  public void testGetPosition() {
    assertEquals(10, this.pixel1.getPosition().getRow());
    assertEquals(20, this.pixel1.getPosition().getCol());
    assertEquals(66, this.pixel2.getPosition().getRow());
    assertEquals(34, this.pixel2.getPosition().getCol());
  }

  @Test
  public void testGetColor() {
    assertEquals(new RGBAColor(1, 2, 3, 4), this.pixel1.getColor());
    assertEquals(new RGBAColor(100, 25, 65, 120), this.pixel2.getColor());
  }

  @Test
  public void testCreateCopy() {
    Pixel pixel1Copy = this.pixel1.createCopy();
    this.pixel1 = null;
    assertNotNull(pixel1Copy);
    assertEquals(new RGBAColor(1, 2, 3, 4), pixel1Copy.getColor());
    assertEquals(10, pixel1Copy.getPosition().getRow());
    assertEquals(20, pixel1Copy.getPosition().getCol());
  }

  @Test
  public void testGetOriginalColor() {
    assertEquals(new RGBAColor(1, 2, 3, 4), this.pixel1.getOriginalColor());
    assertEquals(new RGBAColor(100, 25, 65, 120), this.pixel2.getOriginalColor());
  }
}
