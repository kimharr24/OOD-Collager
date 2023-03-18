import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.images.Image;
import model.layers.Layer;
import model.pixels.Pixel;
import model.projects.ProjectModel;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

//  @Test
//  public void testExecuteCommand() {
//    StringBuilder userInput = new StringBuilder();
//    StringBuilder expectedOutput = new StringBuilder();
//
//    StringReader input = new StringReader(userInput.toString());
//    StringBuilder actualOutput = new StringBuilder();
//
//    ModuleLayer.Controller controller = new ModuleLayer.Controller(model, input, actualOutput);
//    controller.run();
//
//    assertEquals(expectedOutput.toString(), actualOutput.toString());
//    ProjectModel<Pixel> project = new ProjectModel<>();
//    Layer<Pixel> layer = new Layer<>("layer1");
//    Pixel[][] pixels = new Pixel[10][10];
//    layer.setPixels(pixels);
//    project.addLayer(layer);
//
//    // create a controller and set the layer name and filter option
//    ModuleLayer.Controller controller = new ModuleLayer.Controller();
//    controller.setLayerName("layer1");
//    controller.setFilterOption("normal");
//
//    // execute the command
//    controller.executeCommand(project);
//
//    // check that the layer filter was set to the normal filter
//    Assertions.assertTrue(project.getLayerFilter("layer1") instanceof NormalFilter);
//  }
  }
