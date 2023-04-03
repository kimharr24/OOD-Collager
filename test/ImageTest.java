import org.junit.Before;
import org.junit.Test;

import model.colors.RGBAColor;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing class for images in the collage project application.
 */
public class ImageTest {
  private ImageModel<Pixel> image1;
  private ImageModel<Pixel> image2;
  private ImageModel<Pixel> image3;

  @Before
  public void init() {
    this.image1 = new Image(100, 200);
    this.image2 = new Image(300, 400);
    this.image3 = new Image(300, 300);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeWidth() {
    ImageModel<Pixel> image = new Image(-30, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeHeight() {
    ImageModel<Pixel> image = new Image(30, -10);
  }

  @Test
  public void testConstructorGoodInputs() {
    ImageModel<Pixel> image = new Image(20, 30);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        assertEquals(Util.MAX_PROJECT_VALUE,
                image.getPixelAtCoord(i, j).getColor().getRedComponent(), 0.001);
        assertEquals(Util.MAX_PROJECT_VALUE,
                image.getPixelAtCoord(i, j).getColor().getGreenComponent(), 0.001);
        assertEquals(Util.MAX_PROJECT_VALUE,
                image.getPixelAtCoord(i, j).getColor().getBlueComponent(), 0.001);
        assertEquals(0,
                image.getPixelAtCoord(i, j).getColor().getAlphaComponent(), 0.001);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollapseImageNullImage() {
    this.image1.collapseImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollapseImageDifferentImageSize() {
    this.image1.collapseImage(new Image(3, 3));
  }

  @Test
  public void testCollapseImage() {
    int width = 300;
    int height = 400;

    // Bottom image is blue
    ImageModel<Pixel> bottomImage = new Image(width, height);
    bottomImage.colorBackground(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE));
    // Top image is red
    ImageModel<Pixel> topImage = new Image(width, height);
    topImage.colorBackground(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0,
            Util.MAX_PROJECT_VALUE));

    // Red should fully cover the blue
    ImageModel<Pixel> collapsedImage1 = bottomImage.collapseImage(topImage);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0,
                Util.MAX_PROJECT_VALUE),
                collapsedImage1.getPixelAtCoord(i, j).getColor());
      }
    }
    // Blue should fully cover the red
    ImageModel<Pixel> collapsedImage2 = topImage.collapseImage(bottomImage);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE,
                        Util.MAX_PROJECT_VALUE),
                collapsedImage2.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullColorBackground() {
    this.image1.colorBackground(null);
  }

  @Test
  public void testColorBackground() {
    this.image1.colorBackground(new RGBAColor(10, 20, 30, 40));
    for (int i = 0; i < image1.getImageHeight(); i++) {
      for (int j = 0; j < image1.getImageWidth(); j++) {
        assertEquals(new RGBAColor(10, 20, 30, 40),
                this.image1.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidRow() {
    this.image1.overlayImage(new Image(20, 30), -1, 2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCol() {
    this.image1.overlayImage(new Image(20, 30), 1, -2);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCoordinateDueToImageSpillingOutOfBoundsVertically() {
    this.image2.overlayImage(new Image(10, 10), 392, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCoordinateDueToImageSpillingOutOfBoundsHorizontally() {
    this.image2.overlayImage(new Image(10, 10), 0, 292);
  }

  @Test
  public void testOverlayImage() {
    ImageModel<Pixel> image = new Image(300, 300);
    image.colorBackground(new RGBAColor(10, 20, 30, 40));
    this.image3.overlayImage(image, 0, 0);

    for (int i = 0; i < 300; i++) {
      for (int j = 0; j < 300; j++) {
        assertEquals(new RGBAColor(10, 20, 30, 40),
                this.image3.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPixelSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRowSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), -2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 2, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsRowSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 200, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOutOfBoundsColSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 10, 200);
  }

  @Test
  public void testSetImagePixelAtCoord() {
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);
    assertEquals(0,
            this.image1.getPixelAtCoord(0, 0).getColor().getAlphaComponent(), 0.001);

    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 0, 0);

    assertEquals(1,
            this.image1.getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(2,
            this.image1.getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(3,
            this.image1.getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);
    assertEquals(4,
            this.image1.getPixelAtCoord(0, 0).getColor().getAlphaComponent(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRowGetPixelAtCoord() {
    this.image1.getPixelAtCoord(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColGetPixelAtCoord() {
    this.image1.getPixelAtCoord(1, -2);
  }

  @Test
  public void testGetPixelAtCoord() {
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getRedComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getGreenComponent(), 0.001);
    assertEquals(Util.MAX_PROJECT_VALUE,
            this.image1.getPixelAtCoord(0, 0).getColor().getBlueComponent(), 0.001);
    assertEquals(0,
            this.image1.getPixelAtCoord(0, 0).getColor().getAlphaComponent(), 0.001);
  }

  @Test
  public void testGetImageHeight() {
    assertEquals(200, this.image1.getImageHeight());
  }

  @Test
  public void testGetImageWidth() {
    assertEquals(100, this.image1.getImageWidth());
  }

  @Test
  public void testEquals() {
    ImageModel<Pixel> image1Copy = new Image(100, 200);
    assertEquals(image1Copy, this.image1);

    this.image1.colorBackground(new RGBAColor(1, 2, 3, 4));
    assertNotEquals(image1Copy, this.image1);

    image1Copy.colorBackground(new RGBAColor(1, 2, 3, 4));
    assertEquals(image1Copy, this.image1);
  }

  @Test
  public void testHashCode() {
    assertEquals(4261, this.image1.hashCode());
    assertEquals(10661, this.image2.hashCode());
  }
}
