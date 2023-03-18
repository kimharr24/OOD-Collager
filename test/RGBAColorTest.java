import org.junit.Before;
import org.junit.Test;

import model.colors.ColorModel;
import model.colors.RGBAColor;
//import model.layers.Layer;
//import model.pixels.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing class for an RGBA color to ensure that all methods work as expected.
 */
public class RGBAColorTest {
  ColorModel color1;
  ColorModel color2;

  ColorModel color3;

  @Before
  public void init() {
    this.color1 = new RGBAColor(100, 150, 65, 99);
    this.color2 = new RGBAColor(140, 50, 50, 155);
    this.color3 = new RGBAColor(1, 1, 1, 20);
  }

  /**
   *
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRedComponent() {
    ColorModel color = new RGBAColor(-6, 100, 200, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeGreenComponent() {
    ColorModel color = new RGBAColor(6, -100, 200, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeBlueComponent() {
    ColorModel color = new RGBAColor(6, 100, -200, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAlphaComponent() {
    ColorModel color = new RGBAColor(6, 100, 200, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 256, 200, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueComponentExceedsBitRange() {
    ColorModel color = new RGBAColor(2, 100, 512, 100);
  }

  @Test
  public void testGetRedComponent() {
    assertEquals(100, this.color1.getRedComponent(), 0.0001);
    assertEquals(140, this.color2.getRedComponent(), 0.0001);
  }

  @Test
  public void testGetGreenComponent() {
    assertEquals(150, this.color1.getGreenComponent(), 0.0001);
    assertEquals(50, this.color2.getGreenComponent(), 0.0001);
  }

  @Test
  public void testGetBlueComponent() {
    assertEquals(65, this.color1.getBlueComponent(), 0.0001);
    assertEquals(50, this.color2.getBlueComponent(), 0.0001);
  }

  @Test
  public void testGetAlphaComponent() {
    assertEquals(99, this.color1.getAlphaComponent(), 0.0001);
    assertEquals(155, this.color2.getAlphaComponent(), 0.0001);
  }

  @Test
  public void testCreateCopy() {
    ColorModel color = this.color1.createCopy();
    assertEquals(color.getRedComponent(), this.color1.getRedComponent(), 0.0001);
    assertEquals(color.getBlueComponent(), this.color1.getBlueComponent(), 0.0001);
    assertEquals(color.getGreenComponent(), this.color1.getGreenComponent(), 0.0001);
    assertEquals(color.getAlphaComponent(), this.color1.getAlphaComponent(), 0.0001);

    color = null;
    assertNotNull(this.color1);
  }

  @Test
  public void testFilterRedComponent() {
    assertEquals(100, this.color1.filterRedChannel().getRedComponent(), 0.0001);
    assertEquals(0, this.color1.filterRedChannel().getGreenComponent(), 0.0001);
    assertEquals(0, this.color1.filterRedChannel().getBlueComponent(), 0.0001);
    assertEquals(99, this.color1.filterRedChannel().getAlphaComponent(), 0.0001);

    assertEquals(140, this.color2.filterRedChannel().getRedComponent(), 0.0001);
    assertEquals(0, this.color2.filterRedChannel().getGreenComponent(), 0.0001);
    assertEquals(0, this.color2.filterRedChannel().getBlueComponent(), 0.0001);
    assertEquals(155, this.color2.filterRedChannel().getAlphaComponent(), 0.0001);
  }

  @Test
  public void testFilterBlueComponent() {
    assertEquals(65, this.color1.filterBlueChannel().getBlueComponent(), 0.0001);
    assertEquals(0, this.color1.filterBlueChannel().getGreenComponent(), 0.0001);
    assertEquals(0, this.color1.filterBlueChannel().getRedComponent(), 0.0001);
    assertEquals(99, this.color1.filterBlueChannel().getAlphaComponent(), 0.0001);

    assertEquals(0, this.color2.filterBlueChannel().getRedComponent(), 0.0001);
    assertEquals(0, this.color2.filterBlueChannel().getGreenComponent(), 0.0001);
    assertEquals(50, this.color2.filterBlueChannel().getBlueComponent(), 0.0001);
    assertEquals(155, this.color2.filterBlueChannel().getAlphaComponent(), 0.0001);
  }

  @Test
  public void testFilterGreenComponent() {
    assertEquals(0, this.color1.filterGreenChannel().getRedComponent(), 0.0001);
    assertEquals(150, this.color1.filterGreenChannel().getGreenComponent(), 0.0001);
    assertEquals(0, this.color1.filterGreenChannel().getBlueComponent(), 0.0001);
    assertEquals(99, this.color1.filterGreenChannel().getAlphaComponent(), 0.0001);

    assertEquals(0, this.color2.filterGreenChannel().getRedComponent(), 0.0001);
    assertEquals(50, this.color2.filterGreenChannel().getGreenComponent(), 0.0001);
    assertEquals(0, this.color2.filterGreenChannel().getBlueComponent(), 0.0001);
    assertEquals(155, this.color2.filterGreenChannel().getAlphaComponent(), 0.0001);
  }

  @Test
  public void testBrightenIntensity() {
    RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
    ColorModel updatedColor = model.brightenIntensityColor();
    assertEquals(200, updatedColor.getRedComponent(), 0.0001);
    assertEquals(200, updatedColor.getBlueComponent(), 0.0001);
    assertEquals(200, updatedColor.getGreenComponent(), 0.0001);
  }

    @Test
    public void testDarkenIntensity() {
      RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
      ColorModel updatedColor = model.darkenIntensityColor();
      assertEquals(0, updatedColor.getRedComponent(), 0.0001);
      assertEquals(0, updatedColor.getBlueComponent(), 0.0001);
      assertEquals(0, updatedColor.getGreenComponent(), 0.0001);
  }

  @Test
  public void testBrightenLuma() {
      RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
      ColorModel updatedColor = model.brightenLumaColor();
      assertEquals(200, updatedColor.getRedComponent(), 0.0001);
      assertEquals(200, updatedColor.getBlueComponent(), 0.0001);
      assertEquals(200, updatedColor.getGreenComponent(), 0.0001);
      assertEquals(100, updatedColor.getAlphaComponent(), 0.0001);
  }

  @Test
  public void testDarkenLuma() {
    RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
    ColorModel updatedColor = model.darkenLumaColor();
    assertEquals(0, updatedColor.getRedComponent(), 0.0001);
    assertEquals(0, updatedColor.getBlueComponent(), 0.0001);
    assertEquals(0, updatedColor.getGreenComponent(), 0.0001);
    assertEquals(100, updatedColor.getAlphaComponent(), 0.0001);
  }

  @Test
  public void testBrightenValue() {
    RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
    ColorModel updatedColor = model.brightenValueColor();
    assertEquals(200, updatedColor.getRedComponent(), 0.0001);
    assertEquals(200, updatedColor.getBlueComponent(), 0.0001);
    assertEquals(200, updatedColor.getGreenComponent(), 0.0001);
    assertEquals(100, updatedColor.getAlphaComponent(), 0.0001);
  }

  @Test
  public void testDarkenValue() {
    RGBAColor model = new RGBAColor(100.0, 100, 100, 100);
    ColorModel updatedColor = model.darkenValueColor();
    assertEquals(0, updatedColor.getRedComponent(), 0.0001);
    assertEquals(0, updatedColor.getBlueComponent(), 0.0001);
    assertEquals(0, updatedColor.getGreenComponent(), 0.0001);
    assertEquals(100, updatedColor.getAlphaComponent(), 0.0001);
  }
}
