import org.junit.Before;
import org.junit.Test;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.filters.BlueComponentFilter;
import model.filters.BrightenLumaFilter;
import model.filters.DarkeningBlendingFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Position2D;
import utils.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Testing class for a collage project ensures that mutator and observer methods for
 * a CollageProject behave as expected.
 */
public class CollageProjectTest {
  private ProjectModel<Pixel> project;
  private int canvasWidth;
  private int canvasHeight;

  @Before
  public void init() {
    this.canvasHeight = 500;
    this.canvasWidth = 800;
    this.project = new CollageProject(this.canvasHeight, this.canvasWidth);
  }

  /**
   * Creates a copy of the given image. Used solely for testing purposes.
   * @param image the image to copy.
   * @return the copied image.
   */
  private ImageModel<Pixel> createImageCopy(ImageModel<Pixel> image) {
    ImageModel<Pixel> copy = new Image(image.getImageWidth(), image.getImageHeight());

    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        ColorModel currentColor = image.getPixelAtCoord(i, j).getColor();
        copy.setImagePixelAtCoord(new ImagePixel(new Position2D(i, j),
                new RGBAColor(currentColor.getRedComponent(), currentColor.getGreenComponent(),
                        currentColor.getBlueComponent(), currentColor.getAlphaComponent())), i, j);
      }
    }
    return copy;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollageProjectConstructorInvalidWidth() {
    ProjectModel<Pixel> project = new CollageProject(100, -20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCollageProjectConstructorInvalidHeight() {
    ProjectModel<Pixel> project = new CollageProject(-300, 500);
  }

  @Test
  public void testCollageProjectValidConstructor() {
    ProjectModel<Pixel> project = new CollageProject(400, 700);

    // There should only be 1 layer in the project
    assertEquals(1, project.getLayerCount());

    // It should be called "default-layer"
    assertEquals("default-layer", project.getLayerAtPosition(0).getLayerName());

    // It should have the normal filter
    assertEquals("normal", project.getLayerAtPosition(0).getFilterName());

    // It should store a fully white, opaque image
    for (int i = 0; i < project.getCanvasHeight(); i++) {
      for (int j = 0; j < project.getCanvasWidth(); j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
                project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }

    // The image should be the same dimensions as the project canvas width and height
    assertEquals(400, project.getLayerAtPosition(0).getImage().getImageHeight());
    assertEquals(700, project.getLayerAtPosition(0).getImage().getImageWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLayerNullFilter() {
    this.project.setLayerFilter("default-layer", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetLayerFilterNonexistentLayer() {
    this.project.setLayerFilter("foo", new RedComponentFilter());
  }

  @Test
  public void testSetLayerFilter() {
    this.project.addLayer("layer1");
    this.project.addLayer("layer2");

    // Check that the layers start off with the default normal filters
    assertEquals("layer1", this.project.getLayerAtPosition(1).getLayerName());
    assertEquals("normal", this.project.getLayerAtPosition(1).getFilterName());
    assertEquals("layer2", this.project.getLayerAtPosition(2).getLayerName());
    assertEquals("normal", this.project.getLayerAtPosition(2).getFilterName());

    ImageModel<Pixel> originalLayer1Image = this.createImageCopy(
            this.project.getLayerAtPosition(1).getImage());
    ImageModel<Pixel> originalLayer2Image = this.createImageCopy(
            this.project.getLayerAtPosition(2).getImage());

    // Check that setting the layer filter changes the filter stored in the layer
    this.project.setLayerFilter("layer1", new DarkeningBlendingFilter());
    assertEquals("darkening-blending", this.project.getLayerAtPosition(1).getFilterName());

    this.project.setLayerFilter("layer2", new BlueComponentFilter());
    assertEquals("blue-component", this.project.getLayerAtPosition(2).getFilterName());

    // Check that setting the layer filter DOES NOT mutate the image stored in the layer
    assertEquals(originalLayer1Image, this.project.getLayerAtPosition(1).getImage());
    assertEquals(originalLayer2Image, this.project.getLayerAtPosition(2).getImage());

    // Check that a layer's filter can be reset multiple times
    this.project.setLayerFilter("layer2", new BrightenLumaFilter());
    assertEquals("brighten-luma", this.project.getLayerAtPosition(2).getFilterName());
    assertEquals(originalLayer2Image, this.project.getLayerAtPosition(2).getImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerNullImage() {
    this.project.addImageToLayer("default-layer", null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerNonexistentLayer() {
    this.project.addImageToLayer("foo", new Image(10, 10), 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerNegativeRow() {
    this.project.addImageToLayer("default-layer", new Image(10, 10), -3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerNegativeCol() {
    this.project.addImageToLayer("default-layer", new Image(10, 10), 0, -12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerRowTooLarge() {
    this.project.addImageToLayer("default-layer", new Image(10, 10),
            this.canvasHeight + 10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerColTooLarge() {
    this.project.addImageToLayer("default-layer", new Image(10, 10),
            0, this.canvasWidth + 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerImageSpillsHorizontally() {
    this.project.addImageToLayer("default-layer",
            new Image(this.canvasWidth - 25, this.canvasHeight - 25), 0, 26);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerImageSpillsVertically() {
    this.project.addImageToLayer("default-layer",
            new Image(this.canvasWidth - 25, this.canvasHeight - 25), 26, 0);
  }

  @Test
  public void testAddImageToLayerSuccess() {
    ImageModel<Pixel> redImage = new Image(this.canvasWidth - 5, this.canvasHeight - 5);
    redImage.colorBackground(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, Util.MAX_PROJECT_VALUE));

    // Add the red image to the default layer with an offset of (1, 1)
    this.project.addImageToLayer("default-layer", redImage, 1, 1);

    // Check that the first column is still entirely white
    for (int i = 0; i < this.canvasHeight; i++) {
      assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
              Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
              this.project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, 0).getColor());
    }
    // Check that the first row is still entirely white
    for (int i = 0; i < this.canvasWidth; i++) {
      assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                      Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
              this.project.getLayerAtPosition(0).getImage().getPixelAtCoord(0, i).getColor());
    }
    // Check that the rest of the image is now red
    for (int i = 1; i < this.canvasHeight - 5; i++) {
      for (int j = 1; j < this.canvasWidth - 5; j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, Util.MAX_PROJECT_VALUE),
                this.project.getLayerAtPosition(0).getImage().getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerNameAlreadyExists() {
    this.project.addLayer("default-layer");
  }

  @Test
  public void testAddLayerSuccessful() {
    assertFalse(this.project.containsLayer("layer 1"));
    assertEquals(1, this.project.getLayerCount());

    this.project.addLayer("layer 1");
    assertTrue(this.project.containsLayer("layer 1"));
    assertEquals(2, this.project.getLayerCount());

    assertEquals("layer 1", this.project.getLayerAtPosition(1).getLayerName());

    // Check that the layer is fully white and transparent by default
    for (int i = 0; i < this.canvasHeight; i++) {
      for (int j = 0; j < this.canvasWidth; j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
                Util.MAX_PROJECT_VALUE, 0),
                this.project.getLayerAtPosition(1).getImage().getPixelAtCoord(i, j).getColor());
      }
    }

    this.project.addLayer("layer 2");
    assertTrue(this.project.containsLayer("layer 2"));
    assertEquals(3, this.project.getLayerCount());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(Util.MAX_PROJECT_VALUE, this.project.getMaxValue());
  }

  @Test
  public void testGetCanvasWidth() {
    assertEquals(this.canvasWidth, this.project.getCanvasWidth());
  }

  @Test
  public void testGetCanvasHeight() {
    assertEquals(this.canvasHeight, this.project.getCanvasHeight());
  }

  @Test
  public void testGetLayerCount() {
    assertEquals(1, this.project.getLayerCount());
    this.project.addLayer("Layer1");
    assertEquals(2, this.project.getLayerCount());

    this.project.addLayer("Layer2");
    assertEquals(3, this.project.getLayerCount());

    this.project.addLayer("Layer3");
    assertEquals(4, this.project.getLayerCount());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerAtPositionNegativeIndices() {
    this.project.getLayerAtPosition(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetLayerAtPositionLargeIndex() {
    this.project.getLayerAtPosition(1);
  }

  @Test
  public void testGetLayerAtPositionSuccess() {
    assertEquals("default-layer", this.project.getLayerAtPosition(0).getLayerName());
    this.project.addLayer("layer-1");
    assertEquals("layer-1", this.project.getLayerAtPosition(1).getLayerName());
    this.project.addLayer("layer-2");
    assertEquals("layer-2", this.project.getLayerAtPosition(2).getLayerName());
  }

  @Test
  public void testContainsLayer() {
    this.project.addLayer("Layer1");
    this.project.addLayer("Layer2");
    this.project.addLayer("Layer3");

    assertTrue(this.project.containsLayer("default-layer"));
    assertTrue(this.project.containsLayer("Layer1"));
    assertTrue(this.project.containsLayer("Layer2"));
    assertTrue(this.project.containsLayer("Layer3"));
    assertFalse(this.project.containsLayer("Layer4"));
  }

  @Test
  public void testGetCompositeImageInvariant() {
    assertEquals("normal", this.project.getLayerAtPosition(0).getFilterName());
    assertEquals(1, this.project.getLayerCount());

    this.project.addLayer("layer one");
    this.project.addLayer("layer two");
    assertEquals(3, this.project.getLayerCount());

    // Get copies of the original, unmodified images in each of the layers
    ImageModel<Pixel> unmodifiedDefaultLayerImage = this.createImageCopy(
            this.project.getLayerAtPosition(0).getImage());
    ImageModel<Pixel> unmodifiedLayerOneImage = this.createImageCopy(
            this.project.getLayerAtPosition(1).getImage());
    ImageModel<Pixel> unmodifiedLayerTwoImage = this.createImageCopy(
            this.project.getLayerAtPosition(2).getImage());

    assertEquals("normal", this.project.getLayerAtPosition(0).getFilterName());

    this.project.setLayerFilter("layer one", new BlueComponentFilter());
    assertEquals("blue-component",
            this.project.getLayerAtPosition(1).getFilterName());

    this.project.setLayerFilter("layer two", new RedComponentFilter());
    assertEquals("red-component",
            this.project.getLayerAtPosition(2).getFilterName());

    // Apply filters to the unmodified images to get a collapsed image
    this.project.getCompositeImage();

    // Ensure that the layers are still storing the unmodified images after applying filters
    assertEquals(unmodifiedDefaultLayerImage, this.project.getLayerAtPosition(0).getImage());
    assertEquals(unmodifiedLayerOneImage, this.project.getLayerAtPosition(1).getImage());
    assertEquals(unmodifiedLayerTwoImage, this.project.getLayerAtPosition(2).getImage());

    // Ensure that the layers have the correct filter fields
    assertEquals("normal", this.project.getLayerAtPosition(0).getFilterName());
    assertEquals("blue-component",
            this.project.getLayerAtPosition(1).getFilterName());
    assertEquals("red-component",
            this.project.getLayerAtPosition(2).getFilterName());
  }

  @Test
  public void testGetCompositeImage() {
    this.project.setLayerFilter("default-layer", new RedComponentFilter());
    ImageModel<Pixel> compositeImage = this.project.getCompositeImage();

    // Simple case where the composite image is generated from the default layer with a red filter
    for (int i = 0; i < this.canvasHeight; i++) {
      for (int j = 0; j < this.canvasWidth; j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, Util.MAX_PROJECT_VALUE),
                compositeImage.getPixelAtCoord(i, j).getColor());
      }
    }

    this.project.addLayer("layer 1");
    this.project.setLayerFilter("layer 1", new BlueComponentFilter());
    ImageModel<Pixel> opaqueImage = new Image(this.canvasWidth - 5, this.canvasHeight - 5);
    opaqueImage.colorBackground(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE));

    this.project.addImageToLayer("layer 1", opaqueImage, 0, 0);

    // Now a second blue layer is placed on the first layer at (0,0) and is 5 less than the
    // maximum image dimensions
    compositeImage = this.project.getCompositeImage();
    for (int i = 0; i < this.canvasHeight - 5; i++) {
      for (int j = 0; j < this.canvasWidth - 5; j++) {
        assertEquals(new RGBAColor(0, 0, Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE),
                compositeImage.getPixelAtCoord(i, j).getColor());
      }
    }
    // The uncovered first layer should still be red
    for (int i = this.canvasHeight - 5; i < this.canvasHeight; i++) {
      for (int j = this.canvasWidth - 5; j < this.canvasWidth; j++) {
        assertEquals(new RGBAColor(Util.MAX_PROJECT_VALUE, 0, 0, Util.MAX_PROJECT_VALUE),
                compositeImage.getPixelAtCoord(i, j).getColor());
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteLayerNonexistentLayer() {
    this.project.deleteLayer("foo");
  }

  @Test
  public void testDeleteLayer() {
    assertTrue(this.project.containsLayer("default-layer"));
    this.project.deleteLayer("default-layer");
    assertFalse(this.project.containsLayer("default-layer"));
  }
}


