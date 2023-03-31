import org.junit.Test;

import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import model.filters.BlueComponentFilter;
import model.filters.RedComponentFilter;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

import static org.junit.Assert.fail;

/**
 * Testing class to ensure that the image saving command works as expected.
 */
public class SaveImageCommandTest {
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteCommandNullProject() {
    ProjectCommand command = new SaveImageCommand("./test/images/test-save-command.ppm");
    command.executeCommand(null);
  }

//  @Test
//  public void testExecuteCommand() {
//    ProjectModel<Pixel> project = new CollageProject(500, 500);
//    ProjectCommand command = new SaveImageCommand("./test/images/test-save-command.ppm");
//    project.addLayer("first-layer");
//    project.addImageToLayer("first-layer", "./test/images/cat.ppm",0, 0);
//    project.setLayerFilter("first-layer", new RedComponentFilter());
//    project.addLayer("second-layer");
//    project.addImageToLayer("second-layer", "./test/images/cat.ppm",100, 100);
//    project.setLayerFilter("second-layer", new BlueComponentFilter());
//
//    command.executeCommand(project);
//    try {
//      ImageFileInputCommand<Pixel> in = new PPMInputCommand("./test/images/test-save-command.ppm");
//      ImageModel<Pixel> extractedImage = in.extractImage("./test/images/test-save-command.ppm");
//    } catch (RuntimeException e) {
//      fail("Expected extraction to work!");
//    }
//  }
}
