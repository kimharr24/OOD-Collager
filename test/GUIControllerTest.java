import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import controller.ControllerFeatures;
import controller.GUIController;
import model.filters.RedComponentFilter;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import view.CollageGUIView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the controller to ensure that it can handle inputs from the view correctly.
 */
public class GUIControllerTest {
  private ControllerFeatures controller;
  private Appendable log;

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.controller = new GUIController(new CollageProject(500, 600),
            new GUIViewMock(this.log));
  }

  /**
   * Mock GUI view to check that controller-view interactions are as expected.
   */
  private static class GUIViewMock implements CollageGUIView<Pixel> {
    private final Appendable log;

    /**
     * Allows the tester to specify an appendable to log method calling information.
     * @param log the appendable log.
     */
    public GUIViewMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void renderErrorMessage(String message) {
      try {
        this.log.append("Error: ").append(message).append("\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public void addFeatures(ControllerFeatures features) {
      try {
        this.log.append("Controller features added to the view\n");
      } catch (IOException ignored) {
      }
    }

    @Override
    public void refresh(ImageModel<Pixel> compositeImage,
                        Map<String, String> layerNameToFilterName) {
      try {
        this.log.append("Layer->Filter Mapping:\n");
        for (Map.Entry<String, String> entry : layerNameToFilterName.entrySet()) {
          this.log.append(String.format("%s %s\n", entry.getKey(), entry.getValue()));
        }
      } catch (IOException ignored) {
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    ControllerFeatures controller = new GUIController(null,
            new GUIViewMock(new StringBuilder()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    ControllerFeatures controller = new GUIController(new CollageProject(10, 10), null);
  }

  @Test
  public void testApplyFilterInvalidLayer() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.applyFilter("blah", "red-component");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Selected layer or filter does not exist.\n", this.log.toString());
  }

  @Test
  public void testApplyFilterInvalidFilter() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.applyFilter("default-layer", "foo");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Selected layer or filter does not exist.\n", this.log.toString());
  }

  @Test
  public void testApplyFilter() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.applyFilter("default-layer", "red-component");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Layer->Filter Mapping:\ndefault-layer red-component\n", this.log.toString());
  }

  @Test
  public void testMakeNewProjectInvalidDimensions() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.makeNewProject(-1, 100);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Please enter non-negative, non-zero integers.\n", this.log.toString());
    this.controller.makeNewProject(300, -20);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Please enter non-negative, non-zero integers.\n" +
            "Error: Please enter non-negative, non-zero integers.\n", this.log.toString());
  }

  @Test
  public void testMakeNewProject() {
    Appendable log = new StringBuilder();
    ProjectModel<Pixel> project = new CollageProject(200, 300);
    project.addLayer("layer-one");
    project.addLayer("layer-two");
    project.setLayerFilter("layer-two", new RedComponentFilter());
    this.controller = new GUIController(project, new GUIViewMock(log));
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\n" +
            "layer-one normal\n" +
            "layer-two red-component\n" +
            "default-layer normal\n", log.toString());
    this.controller.makeNewProject(100, 100);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\n" +
            "layer-one normal\n" +
            "layer-two red-component\n" +
            "default-layer normal\n" +
            "Layer->Filter Mapping:\n" +
            "default-layer normal\n", log.toString());
  }

  @Test
  public void testAddImageToLayerInvalidRowCol() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.addImageToLayer("./test/images/cat.ppm", "default-layer",
            -3, 5);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Please enter non-negative numbers for the displacement.\n",
            this.log.toString());
  }

  @Test
  public void testAddImageToLayerInvalidFile() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.addImageToLayer("./test/images/cat.xyz", "default-layer",
            0, 0);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Unsupported file extension or unsupported file format or " +
            "image will be out-of-bounds of the screen.\n", this.log.toString());
  }

  @Test
  public void testAddImageToLayer() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.addImageToLayer("./test/images/cat.ppm", "default-layer",
            0, 0);
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
  }

  @Test
  public void testAddLayerAlreadyExists() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.addLayer("default-layer");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Error: Layer default-layer already exists!\n", this.log.toString());
  }

  @Test
  public void testAddLayer() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.addLayer("layer-one");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n" +
            "Layer->Filter Mapping:\nlayer-one normal\n" +
            "default-layer normal\n", this.log.toString());
  }

  @Test
  public void testSaveProject() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.saveProject("./test/saved-project.collage");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    File file = new File("./test/saved-project.collage");
    assertTrue(file.delete());
  }

  @Test
  public void testSaveImage() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    this.controller.saveImage("./test/saved-image.ppm");
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
    File file = new File("./test/saved-image.ppm");
    assertTrue(file.delete());
  }

  @Test
  public void testLoadProject() {
    assertEquals("Controller features added to the view\n" +
            "Layer->Filter Mapping:\ndefault-layer normal\n", this.log.toString());
  }
}
