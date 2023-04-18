package controller.fileio.fileinputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.ProjectModel;

/**
 * Represents a PNG file input command. Capable of extracting images from PNG files.
 */
public class PNGFileInputCommand extends AbstractImageFileInputCommand {
  @Override
  public ImageModel<Pixel> extractImage(String filepath, ProjectModel<Pixel> project)
          throws IllegalArgumentException {
    return super.extractImage(filepath, project);
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    return super.getImageWidth(filepath);
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    return super.getImageHeight(filepath);
  }
}
