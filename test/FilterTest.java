import org.junit.Test;

import model.colors.RGBAColor;
import model.filters.Filter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

import static org.junit.Assert.assertEquals;

public class FilterTest {
  @Test
  public void testGetRedComponentFilterGetName() {
    assertEquals("Red Component Filter", new RedComponentFilter().getName());
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
}

