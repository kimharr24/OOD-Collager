import org.junit.Before;
import org.junit.Test;

import model.colors.RGBAColor;
import model.filters.BrightenIntensityFilter;
import model.filters.DarkenLumaFilter;
import model.filters.NormalFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.layers.Layer;
import model.layers.LayerModel;
import model.pixels.Pixel;
import utils.Util;

import static org.junit.Assert.assertEquals;

/**
 * Testing class for a layer.
 */
public class LayerTest {
  private LayerModel<Pixel> layer1;
  private LayerModel<Pixel> layer2;

  @Before
  public void init() {
    this.layer1 = new Layer("layer-one", new RedComponentFilter(), 20, 30);
    this.layer2 = new Layer("layer-two", new BrightenIntensityFilter(), 60, 90);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullFilter() {
    LayerModel<Pixel> layer = new Layer("hello", null, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidWidth() {
    LayerModel<Pixel> layer = new Layer("hello", new NormalFilter(), -1, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorInvalidHeight() {
    LayerModel<Pixel> layer = new Layer("hello", new NormalFilter(), 1, -3);
  }

  @Test
  public void testGetLayerName() {
    assertEquals("layer-one", this.layer1.getLayerName());
    assertEquals("layer-two", this.layer2.getLayerName());
  }

  @Test
  public void testGetFilterName() {
    assertEquals("red-component", this.layer1.getFilterName());
    assertEquals("brighten-intensity", this.layer2.getFilterName());
  }

  @Test
  public void testSetFilter() {
    this.layer1.setFilter(new DarkenLumaFilter());
    assertEquals("darken-luma", this.layer1.getFilterName());
    this.layer1.setFilter(new NormalFilter());
    assertEquals("normal", this.layer1.getFilterName());
  }

  @Test
  public void testSetName() {
    assertEquals("layer-two", this.layer2.getLayerName());
    this.layer2.setName("hello world");
    assertEquals("hello world", this.layer2.getLayerName());
    this.layer2.setName("goodbye world");
    assertEquals("goodbye world", this.layer2.getLayerName());
  }

  @Test
  public void testGetImage() {
    // When a layer is created, the default image should be fully white and transparent.
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);
    assertEquals(0,
            this.layer1.getImage().getPixelAtCoord(0, 0).getColor().getAlphaComponent(), 0.001);

    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(10, 0).getColor().getRedComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(10, 6).getColor().getGreenComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.layer1.getImage().getPixelAtCoord(12, 6).getColor().getBlueComponent(), 0.001);
    assertEquals(0,
            this.layer1.getImage().getPixelAtCoord(3, 5).getColor().getAlphaComponent(), 0.001);
  }

  @Test
  public void testGetFilter() {
    assertEquals("red-component", this.layer1.getFilter().getName());
    assertEquals("brighten-intensity", this.layer2.getFilter().getName());
  }

  @Test
  public void testApplyFilter() {
    this.layer1.applyFilter(new Image(this.layer1.getImage().getImageWidth(),
            this.layer1.getImage().getImageHeight()));

    for (int i = 0; i < this.layer1.getImage().getImageHeight(); i++) {
      for (int j = 0; j < this.layer1.getImage().getImageWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, 0),
                this.layer1.getImage().getPixelAtCoord(i, j).getColor());
      }
    }
  }
}
