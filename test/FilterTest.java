import org.junit.Test;

import model.colors.RGBAColor;
import model.filters.BlueComponentFilter;
import model.filters.BrightenIntensityFilter;
import model.filters.BrightenLumaFilter;
import model.filters.BrightenValueFilter;
import model.filters.BrighteningBlendingFilter;
import model.filters.DarkenIntensityFilter;
import model.filters.DarkenLumaFilter;
import model.filters.DarkenValueFilter;
import model.filters.DarkeningBlendingFilter;
import model.filters.Filter;
import model.filters.GreenComponentFilter;
import model.filters.InversionBlendingFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

import static org.junit.Assert.assertEquals;

/**
 * Represents the test for each filter supported by this program.
 */
public class FilterTest {


  @Test
  public void testGetRedComponentFilterGetName() {
    assertEquals("red-component", new RedComponentFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRedComponentFilterNullImages() {
    Filter<Pixel> filter = new RedComponentFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRedComponentFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new RedComponentFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testRedComponentFilterApply() {
    Filter<Pixel> filter = new RedComponentFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                Util.MAX_PROJECT_VALUE, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetGreenComponentFilterGetName() {
    assertEquals("green-component", new GreenComponentFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetGreenComponentFilterNullImages() {
    Filter<Pixel> filter = new GreenComponentFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetGreenComponentFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new GreenComponentFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testGreenComponentFilterApply() {
    Filter<Pixel> filter = new GreenComponentFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                        Util.MAX_PROJECT_VALUE, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, Util.MAX_PROJECT_VALUE, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetBlueComponentFilterGetName() {
    assertEquals("blue-component", new BlueComponentFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBlueComponentFilterNullImages() {
    Filter<Pixel> filter = new BlueComponentFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBlueComponentFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new BlueComponentFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testBlueComponentFilterApply() {
    Filter<Pixel> filter = new BlueComponentFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                        Util.MAX_PROJECT_VALUE, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetBrightenValueFilterGetName() {
    assertEquals("brighten-value", new BrightenValueFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenValueFilterNullImages() {
    Filter<Pixel> filter = new BrightenValueFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenValueFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new BrightenValueFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testBrightenValueFilterApply() {
    Filter<Pixel> filter = new BrightenValueFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(200, 200, 200, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetDarkenValueFilterGetName() {
    assertEquals("darken-value", new DarkenValueFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenValueFilterNullImages() {
    Filter<Pixel> filter = new DarkenValueFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenValueFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new DarkenValueFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testDarkenValueFilterApply() {
    Filter<Pixel> filter = new DarkenValueFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetBrightenIntensityFilterGetName() {
    assertEquals("brighten-intensity", new BrightenIntensityFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenIntensityFilterNullImages() {
    Filter<Pixel> filter = new BrightenIntensityFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenIntensityFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new BrightenIntensityFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testBrightenIntensityFilterApply() {
    Filter<Pixel> filter = new BrightenIntensityFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(200, 200, 200, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetDarkenIntensityFilterGetName() {
    assertEquals("darken-intensity", new DarkenIntensityFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenIntensityFilterNullImages() {
    Filter<Pixel> filter = new DarkenIntensityFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenIntensityFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new DarkenIntensityFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testDarkenIntensityFilterApply() {
    Filter<Pixel> filter = new DarkenIntensityFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetBrightenLumaFilterGetName() {
    assertEquals("brighten-luma", new BrightenLumaFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenLumaFilterNullImages() {
    Filter<Pixel> filter = new BrightenLumaFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrightenLumaFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new BrightenLumaFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testBrightenLumaFilterApply() {
    Filter<Pixel> filter = new BrightenLumaFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(200, 200, 200, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetDarkenLumaFilterGetName() {
    assertEquals("darken-luma", new DarkenLumaFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenLumaFilterNullImages() {
    Filter<Pixel> filter = new DarkenLumaFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkenLumaFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new DarkenLumaFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testDarkenLumaFilterApply() {
    Filter<Pixel> filter = new DarkenLumaFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetBrightenBlendingFilterGetName() {
    assertEquals("brightening-blending", new BrighteningBlendingFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrighteningBlendingFilterNullImages() {
    Filter<Pixel> filter = new BrighteningBlendingFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetBrighteningBlendingFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new BrighteningBlendingFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testBrighteningBlendingFilterApply() {
    Filter<Pixel> filter = new BrighteningBlendingFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(200, 200, 200, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetDarkeningBlendingFilterGetName() {
    assertEquals("darkening-blending", new DarkeningBlendingFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkeningBlendingFilterNullImages() {
    Filter<Pixel> filter = new DarkeningBlendingFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetDarkeningBlendingFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new DarkeningBlendingFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testDarkeningBlendingFilterApply() {
    Filter<Pixel> filter = new DarkeningBlendingFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test
  public void testGetInversionBlendingFilterGetName() {
    assertEquals("inversion-blending Filter", new InversionBlendingFilter().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetInversionBlendingFilterNullImages() {
    Filter<Pixel> filter = new InversionBlendingFilter();
    filter.apply(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetInversionBlendingFilterUnequalImageDimensions() {
    Filter<Pixel> filter = new InversionBlendingFilter();
    filter.apply(new Image(100, 200), new Image(100, 300));
  }

  @Test
  public void testInversionBlendingFilterApply() {
    Filter<Pixel> filter = new InversionBlendingFilter();
    ImageModel<Pixel> image = new Image(200, 300);
    ImageModel<Pixel> compositeImage = new Image(200, 300);
    image.colorBackground(new RGBAColor(100,100,100,0));

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(100, 100,
                        100, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }

    filter.apply(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(new RGBAColor(0, 0, 0, 0),
                image.getPixelAtCoord(i, j).getColor());
      }
    }
  }

}

