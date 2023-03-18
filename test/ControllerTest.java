import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import controller.CommandController;
import model.projects.CollageProject;

import static org.junit.Assert.*;

public class ControllerTest {

//  @Test
//  public void testExecuteProgram() {
//    // Test input values
//    String input = "new-project MyProject 600 800\n" +
//            "add-layer Layer1\n" +
//            "set-filter Layer1 blue-component\n" +
//            "add-image-to-layer Layer1 image1.ppm 0 0\n" +
//            "add-layer Layer2\n" +
//            "set-filter Layer2 red-component\n" +
//            "add-image-to-layer Layer2 image2.ppm 0 0\n" +
//            "save-project\n" +
//            "save-image output.ppm\n" +
//            "quit\n";
//    InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    System.setIn(inputStream);
//
//    // Create a new CollageCLI instance and run executeProgram()
//    CollageProject project = new CollageProject("My Project", 600,
//            800);
//    CommandController.executeProgram();
//
//    // Verify the expected commands were executed
//    CollageProject model = project.getModel();
//    assertEquals("MyProject", model.getName());
//    assertEquals(600, model.getCanvasHeight());
//    assertEquals(800, model.getCanvasWidth());
//    assertEquals(2, model.getLayerCount());
//    assertEquals("Layer1", model.getLayerAtPosition(1).getLayerName());
//    assertEquals(setLayerFilter().blueComponent, model.getLayerCount());
//    assertEquals("image1.ppm", model.getLayerAtPosition().get(0).getImage().getImageFilePath());
//    assertEquals("Layer2", model.getLayerAtPosition(2).getLayerName());
//    assertEquals(filter.redComponent, model.getLayers().get(1).getFilter().getType());
//    assertEquals("image2.ppm", model.getLayers().get(1).getImage().getImageFilePath());
//  }
}

