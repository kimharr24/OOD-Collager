import org.junit.Test;

import java.io.File;
import java.util.List;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.filters.BlueComponentFilter;
import model.filters.Filter;
import model.filters.NormalFilter;
import model.images.ImageModel;
import model.images.file_input_commands.PPMInputCommand;
import model.layers.Layer;
import model.layers.LayerModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import utils.Util;

import static org.junit.Assert.*;

public class CollageProjectTest {

  @Test
  public void testCollageProjectConstructor() {
    // Test input values
    String projectName = "My Project";
    int canvasHeight = 500;
    int canvasWidth = 500;

    // Create a new CollageProject instance
    CollageProject collageProject = new CollageProject(projectName, canvasHeight, canvasWidth);

    // Verify the instance variables are correctly set
    assertEquals(projectName, collageProject.getName());
    assertEquals(canvasHeight, collageProject.getCanvasHeight());
    assertEquals(canvasWidth, collageProject.getCanvasWidth());

    // Verify the default layer has been created
    assertEquals(1, collageProject.getLayerCount());

    LayerModel<Pixel> defaultLayer = collageProject.getLayerAtPosition(0);
    assertEquals("default-layer", defaultLayer.getLayerName());
    assertEquals(canvasWidth, defaultLayer.getImage().getImageWidth());
    assertEquals(canvasHeight, defaultLayer.getImage().getImageHeight());

    ColorModel backgroundColor = defaultLayer.getImage().getPixelAtCoord(0, 0).getColor();
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getRedComponent(), 0.0001);
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getGreenComponent(), 0.0001);
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getBlueComponent(), 0.0001);
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getAlphaComponent(), 0.0001);
  }

  @Test
  public void testSetLayerFilter() {
    CollageProject collageProject = new CollageProject("My Project", 500, 500);
    collageProject.addLayer("layer1");
    collageProject.addLayer("layer2");
    Filter filter = new NormalFilter();

    collageProject.setLayerFilter("layer2", filter);

    // Check that the filter was set correctly
    LayerModel<Pixel> layer2 = collageProject.getLayerAtPosition(1);
    assertEquals(filter.getName(), layer2.getFilter().getName());
  }

  @Test
  public void testSaveProjectImage() {
    // Create a new CollageProject instance and add a layer with a red background
    CollageProject project = new CollageProject("MyProject", 500, 500);
    LayerModel<Pixel> layer = new Layer("Layer1", new NormalFilter(), 500, 500);
    ImageModel<Pixel> image = layer.getImage();
    image.colorBackground(new RGBAColor(100, 0, 0, 255));
    project.addLayer("Layer1");

    // Save the resulting image to a file
    project.saveProjectImage("test-output.ppm");

    // Verify that the file was created and that it contains the expected data
    File outputFile = new File("test-output.ppm");
    assertTrue(outputFile.exists());
    ImageModel<Pixel> loadedImage = new PPMInputCommand("test-output.ppm").extractImage("test-output.ppm");
    assertEquals(project.getCanvasWidth(), loadedImage.getImageWidth());
    assertEquals(project.getCanvasHeight(), loadedImage.getImageHeight());
    assertEquals(new RGBAColor(255, 255, 255, 255), loadedImage.getPixelAtCoord(0, 0).getColor());
    assertTrue(outputFile.delete());
  }

  @Test
  public void testAddLayer() {
    CollageProject project = new CollageProject("My Project", 500, 500);

    assertEquals(1, project.getLayerCount());

    project.addLayer("Layer 1");
    assertEquals(2, project.getLayerCount());
    assertTrue(project.containsLayer("Layer 1"));

    project.addLayer("Layer 2");
    assertEquals(3, project.getLayerCount());
    assertTrue(project.containsLayer("Layer 2"));
  }

  @Test
  public void testGetMaxValue() {
    CollageProject project = new CollageProject("Max Project", 500, 500);
    int maxValue = project.getMaxValue();

    assertEquals(Util.MAX_PROJECT_VALUE, maxValue);
  }

  @Test
  public void testGetName() {
    CollageProject project = new CollageProject("Named Project", 500, 500);
    String projectName = "Named Project";

    assertEquals(projectName, project.getName());
  }

  @Test
  public void testGetCanvasWidth() {
    CollageProject project = new CollageProject("Named Project", 500, 500);
    int canvasWidth = 500;

    assertEquals(canvasWidth, project.getCanvasWidth());
  }

  @Test
  public void testGetCanvasHeight() {
    CollageProject project = new CollageProject("Named Project", 500, 500);
    int canvasHeight = 500;

    assertEquals(canvasHeight, project.getCanvasHeight());
  }

  @Test
  public void testGetLayerCount() {
    CollageProject project = new CollageProject("MyProject", 500, 500);
    project.addLayer("Layer1");
    assertEquals(2, project.getLayerCount());

    project.addLayer("Layer2");
    assertEquals(3, project.getLayerCount());

    project.addLayer("Layer3");
    assertEquals(4, project.getLayerCount());
  }

  @Test
  public void testGetLayerAtPosition() {
    CollageProject project = new CollageProject("test-project", 100, 100);
    LayerModel<Pixel> layer1 = new Layer("layer1", new NormalFilter(), 100, 100);
    LayerModel<Pixel> layer2 = new Layer("layer2", new NormalFilter(), 100, 100);
    LayerModel<Pixel> layer3 = new Layer("layer3", new NormalFilter(), 100, 100);
    project.addLayer("layer1");
    project.addLayer("layer2");
    project.addLayer("layer3");

    LayerModel<Pixel> resultLayer = project.getLayerAtPosition(1);
    assertEquals("Layer name should be layer1", "layer1", resultLayer.getLayerName());

    resultLayer = project.getLayerAtPosition(0);
    assertEquals("Layer name should be default-layer", "default-layer", resultLayer.getLayerName());

    resultLayer = project.getLayerAtPosition(2);
    assertEquals("Layer name should be layer2", "layer2", resultLayer.getLayerName());

    //assertThrows(IllegalArgumentException.class, () -> project.getLayerAtPosition(3));
  }

  @Test
  public void testContainsLayer() {
    CollageProject project = new CollageProject("MyProject", 500, 500);
    project.addLayer("Layer1");
    project.addLayer("Layer2");
    project.addLayer("Layer3");

    assertTrue(project.containsLayer("Layer1"));
    assertTrue(project.containsLayer("Layer2"));
    assertTrue(project.containsLayer("Layer3"));
    assertFalse(project.containsLayer("Layer4"));
  }
}


