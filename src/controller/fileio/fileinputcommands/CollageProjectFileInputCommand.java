package controller.fileio.fileinputcommands;

import controller.commands.ProjectCommand;
import controller.commands.SetFilterCommand;
import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.layers.LayerModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;

/**
 * Represents a collage project file input command. Expects the project file to be in the
 * format of a .collage file.
 */
public class CollageProjectFileInputCommand extends AbstractFileInputCommand
        implements ProjectFileInputCommand<Pixel> {
  @Override
  public ProjectModel<Pixel> extractProject(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);

    String token = this.scanner.next();
    if (!token.equals("C1")) {
      throw new IllegalArgumentException("Invalid project token.");
    }

    int width = Integer.parseInt(this.scanner.next(), 10);
    int height = Integer.parseInt(this.scanner.next(), 10);

    ProjectModel<Pixel> project = new CollageProject(height, width);
    Util.setMaxValue(Integer.parseInt(this.scanner.next(), 10));

    ProjectCommand command = null;

    int currentLayerPosition = 0;

    while (this.scanner.hasNext()) {
      String currentLayerName = this.scanner.next();
      project.addLayer(currentLayerName);
      command = new SetFilterCommand(currentLayerName, this.scanner.next());
      command.executeCommand(project);

      for (int i = 0; i < width * height; i++) {
        double red = Double.parseDouble(this.scanner.next());
        double green = Double.parseDouble(this.scanner.next());
        double blue = Double.parseDouble(this.scanner.next());
        double alpha = Double.parseDouble(this.scanner.next());

        ColorModel color = new RGBAColor(red, green, blue, alpha);
        LayerModel<Pixel> currentLayer = project.getLayerAtPosition(currentLayerPosition);
      }

      currentLayerPosition += 1;
    }

    return null;
  }
}
