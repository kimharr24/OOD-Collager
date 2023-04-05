import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import controller.ControllerFeatures;
import controller.GUIController;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import view.CollageGUIView;

import static org.junit.Assert.assertEquals;

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
  private class GUIViewMock implements CollageGUIView<Pixel> {
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
        this.log.append("Error: " + message + "\n");
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
    public void refresh(ImageModel<Pixel> compositeImage, Map<String, String> layerNameToFilterName) {
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
            "Error: Selected layer does not exist.\n", this.log.toString());
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
}
