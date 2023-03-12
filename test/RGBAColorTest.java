import org.junit.Before;
import org.junit.Test;

import model.colors.ColorModel;
import model.colors.RGBAColor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing class for an RGBA color to ensure that all methods work as expected.
 */
public class RGBAColorTest {
  ColorModel color1;
  ColorModel color2;

  @Before
  public void init() {
    this.color1 = new RGBAColor(100, 150, 65, 99, 8);
    this.color2 = new RGBAColor(260, 450, 500, 442, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRedComponent() {
    ColorModel color = new RGBAColor(-6, 100, 200, 100,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeGreenComponent() {
    ColorModel color = new RGBAColor(6, -100, 200, 100,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBlueComponent() {
    ColorModel color = new RGBAColor(6, 100, -200, 100,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAlphaComponent() {
    ColorModel color = new RGBAColor(6, 100, 200, -100,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorZeroBits() {
    ColorModel color = new RGBAColor(6, 100, 200, 100,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeBits() {
    ColorModel color = new RGBAColor(6, 100, 200, 100,-10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 100, 200, 100,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 256, 200, 100,8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 100, 512, 100,9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAlphaComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 1, 1, 4,2);
  }

  @Test
  public void testGetRedComponent() {
    assertEquals(100, this.color1.getRedComponent());
    assertEquals(260, this.color2.getRedComponent());
  }

  @Test
  public void testGetGreenComponent() {
    assertEquals(150, this.color1.getGreenComponent());
    assertEquals(450, this.color2.getGreenComponent());
  }

  @Test
  public void testGetBlueComponent() {
    assertEquals(65, this.color1.getBlueComponent());
    assertEquals(500, this.color2.getBlueComponent());
  }

  @Test
  public void testGetAlphaComponent() {
    assertEquals(99, this.color1.getAlphaComponent());
    assertEquals(442, this.color2.getAlphaComponent());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, this.color1.getMaxValue());
    assertEquals(511, this.color2.getMaxValue());
  }

  @Test
  public void testCreateCopy() {
    ColorModel color = this.color1.createCopy();
    assertEquals(color.getRedComponent(), this.color1.getRedComponent());
    assertEquals(color.getBlueComponent(), this.color1.getBlueComponent());
    assertEquals(color.getGreenComponent(), this.color1.getGreenComponent());
    assertEquals(color.getAlphaComponent(), this.color1.getAlphaComponent());
    assertEquals(color.getMaxValue(), this.color1.getMaxValue());

    color = null;
    assertNotNull(this.color1);
  }

  @Test
  public void testFilterRedComponent() {
    assertEquals(100, this.color1.filterRedChannel().getRedComponent());
    assertEquals(0, this.color1.filterRedChannel().getGreenComponent());
    assertEquals(0, this.color1.filterRedChannel().getBlueComponent());
    assertEquals(99, this.color1.filterRedChannel().getAlphaComponent());
    assertEquals(260, this.color2.filterRedChannel().getRedComponent());
    assertEquals(0, this.color2.filterRedChannel().getGreenComponent());
    assertEquals(0, this.color2.filterRedChannel().getBlueComponent());
    assertEquals(442, this.color2.filterRedChannel().getAlphaComponent());
  }
}
