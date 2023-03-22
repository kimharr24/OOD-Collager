import org.junit.Before;
import org.junit.Test;

import model.colors.RGBAColor;
import model.filters.BrightenLumaFilter;
import model.filters.GreenComponentFilter;
import model.filters.NormalFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import utils.Util;

import static org.junit.Assert.assertEquals;

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
    this.image3 = new Image(305, 305);
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

//  @Test
//  public void testCreateCopy() {
//    ImageModel<Pixel> image1Copy = this.image1.createCopy();
//
//    // Check that the copy and the original have the same initial conditions
//    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
//                    Util.MAX_PROJECT_VALUE, 0),
//            this.image1.getPixelAtCoord(3, 5).getColor());
//    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
//            Util.MAX_PROJECT_VALUE, 0),
//            image1Copy.getPixelAtCoord(3, 5).getColor());
//    assertEquals(100, this.image1.getImageWidth());
//    assertEquals(100, image1Copy.getImageWidth());
//    assertEquals(200, this.image1.getImageHeight());
//    assertEquals(200, image1Copy.getImageHeight());
//
//    // Change a single pixel in the copy
//    image1Copy.setImagePixelAtCoord(new ImagePixel(new Position2D(3, 5),
//            new RGBAColor(1, 2, 3, 4)), 3, 5);
//    assertEquals(new RGBAColor(1, 2, 3, 4),
//            image1Copy.getPixelAtCoord(3, 5).getColor());
//    // Check that the original is unmodified
//    for (int i = 0; i < this.image1.getImageHeight(); i++) {
//      for (int j = 0; j < this.image1.getImageWidth(); j++) {
//        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
//                        Util.MAX_PROJECT_VALUE, 0),
//                this.image1.getPixelAtCoord(i, j).getColor());
//      }
//    }
//  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollapseImageNullImage() {
    this.image1.collapseImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollapseImageDifferentImageSize() {
    this.image1.collapseImage(new Image(3, 3));
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
        assertEquals(10,
                image1.getPixelAtCoord(i, j).getColor().getRedComponent(), 0.001);
        assertEquals(20,
                image1.getPixelAtCoord(i, j).getColor().getGreenComponent(), 0.001);
        assertEquals(30,
                image1.getPixelAtCoord(i, j).getColor().getBlueComponent(), 0.001);
        assertEquals(40,
                image1.getPixelAtCoord(i, j).getColor().getAlphaComponent(), 0.001);
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidRow() {
    this.image1.overlayImage("hello.ppm", -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCol() {
    this.image1.overlayImage("hello.ppm", 1, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageFileNotFound() {
    this.image1.overlayImage("hello.ppm", 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidFileExtension() {
    this.image1.overlayImage("hello.xyz", 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCoordinateDueToImageSpillingOutOfBoundsVertically() {
    this.image2.overlayImage("./test/images/emoji.ppm", 101, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlayImageInvalidCoordinateDueToImageSpillingOutOfBoundsHorizontally() {
    this.image2.overlayImage("./test/images/emoji.ppm", 0, 1);
  }

  @Test
  public void testOverlayImageNoOffset() {
    this.image3.overlayImage("./test/images/cat.ppm", 0, 0);

    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(0, 0).getColor());
    assertEquals(new RGBAColor(88, 72, 57, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(0, 1).getColor());

    assertEquals(new RGBAColor(92, 76, 61, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 0).getColor());
    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 1).getColor());

    assertEquals(new RGBAColor(95, 78, 62, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 0).getColor());
    assertEquals(new RGBAColor(93, 76, 60, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 1).getColor());
  }

  @Test
  public void testOverlayImageOffset() {
    this.image3.overlayImage("./test/images/cat.ppm", 1, 1);

    // Check that the first row is still the default values
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(0, 0).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(0, 1).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(0, 2).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(0, 3).getColor());

    // Check that the first column is still the default values
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(1, 0).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(2, 0).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(3, 0).getColor());
    assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                    Util.MAX_PROJECT_VALUE, 0),
            this.image3.getPixelAtCoord(4, 0).getColor());

    // Check that the image is shifted properly
    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 1).getColor());
    assertEquals(new RGBAColor(88, 72, 57, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 2).getColor());

    assertEquals(new RGBAColor(92, 76, 61, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 1).getColor());
    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 2).getColor());

    assertEquals(new RGBAColor(95, 78, 62, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(3, 1).getColor());
    assertEquals(new RGBAColor(93, 76, 60, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(3, 2).getColor());
  }

  @Test
  public void testOverlayMultipleImages() {
    this.image3.overlayImage("./test/images/emoji.ppm", 0, 0);
    this.image3.overlayImage("./test/images/cat.ppm",1,0);

    // PPMs are fully opaque by default. The first row of the image should show the emoji PPM
    // whereas the rest of the image should show the cat PPM
    assertEquals(new RGBAColor(255, 255, 255, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(0, 0).getColor());
    assertEquals(new RGBAColor(255, 255, 255, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(0, 1).getColor());
    assertEquals(new RGBAColor(255, 255, 255, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(0, 2).getColor());

    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 0).getColor());
    assertEquals(new RGBAColor(88, 72, 57, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(1, 1).getColor());

    assertEquals(new RGBAColor(92, 76, 61, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 0).getColor());
    assertEquals(new RGBAColor(90, 74, 59, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(2, 1).getColor());

    assertEquals(new RGBAColor(95, 78, 62, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(3, 0).getColor());
    assertEquals(new RGBAColor(93, 76, 60, Util.MAX_PROJECT_VALUE),
            this.image3.getPixelAtCoord(3, 1).getColor());
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
  public void testOutOfBoundsRowSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 200, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeColSetImagePixelAtCoord() {
    this.image1.setImagePixelAtCoord(new ImagePixel(new Position2D(0, 0),
            new RGBAColor(1, 2, 3, 4)), 2, -4);
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
}
