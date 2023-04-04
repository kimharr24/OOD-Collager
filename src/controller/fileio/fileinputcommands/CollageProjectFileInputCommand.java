package controller.fileio.fileinputcommands;

import controller.commands.ProjectCommand;
import controller.commands.SetFilterCommand;
import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.layers.LayerModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Position2D;
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
    project.deleteLayer("default-layer");
    Util.setMaxValue(Integer.parseInt(this.scanner.next(), 10));

    int currentLayerPosition = 0;

    while (this.scanner.hasNext()) {
      String currentLayerName = this.scanner.next();
      project.addLayer(currentLayerName);
      ProjectCommand command = new SetFilterCommand(currentLayerName, this.scanner.next());
      command.executeCommand(project);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          double red = Double.parseDouble(this.scanner.next());
          double green = Double.parseDouble(this.scanner.next());
          double blue = Double.parseDouble(this.scanner.next());
          double alpha = Double.parseDouble(this.scanner.next());

          ColorModel color = new RGBAColor(red, green, blue, alpha);
          LayerModel<Pixel> currentLayer = project.getLayerAtPosition(currentLayerPosition);
          currentLayer.getImage().setImagePixelAtCoord(new ImagePixel(new Position2D(i, j),
                  color), i, j);
        }
      }
      currentLayerPosition += 1;
    }
    return project;
  }
}
