package controller.fileio.fileinputcommands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

/**
 * Represents a collage project file input command. Expects the project file to be in the
 * format of a .collage file.
 */
public class CollageProjectFileInputCommand extends AbstractFileInputCommand
        implements ProjectFileInputCommand<Pixel> {
  @Override
  public ProjectModel<Pixel> extractProject(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);

    return null;
  }
}
