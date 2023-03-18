import org.junit.Test;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.layers.Layer;
import model.projects.CollageProject;
import utils.Util;

import static org.junit.Assert.*;

public class CollageProjectTest {

  @Test
  public void testCollageProjectConstructor() {
    // Test input values
    String projectName = "My Project";
    int canvasHeight = 800;
    int canvasWidth = 1200;

    // Create a new CollageProject instance
    CollageProject collageProject = new CollageProject(projectName, canvasHeight, canvasWidth);

    // Verify the instance variables are correctly set
    assertEquals(projectName, collageProject.getProjectName());
    assertEquals(canvasHeight, collageProject.getCanvasHeight());
    assertEquals(canvasWidth, collageProject.getCanvasWidth());

    // Verify the default layer has been created
    assertEquals(1, collageProject.getLayers().size());
    Layer defaultLayer = collageProject.getLayers().get(0);
    assertEquals("default-layer", defaultLayer.getLayerName());
    assertEquals(canvasWidth, defaultLayer.getImageWidth());
    assertEquals(canvasHeight, defaultLayer.getImageHeight());

    // Verify the default layer has a white background
    ColorModel backgroundColor = defaultLayer.getImage().getPixelAtCoord(0, 0).getColor();
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getRedComponent());
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getGreenComponent());
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getBlueComponent());
    assertEquals(Util.MAX_PROJECT_VALUE, backgroundColor.getAlphaComponent());
  }
}

