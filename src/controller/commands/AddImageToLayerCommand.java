package controller.commands;

import controller.fileio.fileinputcommands.ImageFileInputCommand;
import controller.fileio.fileinputcommands.PPMFileInputCommand;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.ProjectModel;
import utils.Util;

/**
 * Represents a command that allows a user to add an image to a given layer.
 */
public class AddImageToLayerCommand extends AbstractProjectCommand {
  private final String layerName;
  private final String filePath;
  private final int row;
  private final int col;

  /**
   * The default constructor to add an image to a layer.
   * @param layerName the name of the layer.
   * @param filePath the filepath to the image.
   * @param row the row to place the image.
   * @param col the column to place the image.
   * @throws IllegalArgumentException if the given row/col is negative.
   */
  public AddImageToLayerCommand(String layerName, String filePath, int row, int col) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column cannot be negative");
    }
    this.layerName = layerName;
    this.filePath = filePath;
    this.row = row;
    this.col = col;
  }

  /**
   * Adds an image to a layer in the collage project.
   * @param project the project to add an image to.
   * @throws IllegalArgumentException if the file does not exist.
   */
  public void executeCommand(ProjectModel<Pixel> project) throws IllegalArgumentException {
    this.checkNullProject(project);
    ImageFileInputCommand<Pixel> command = null;

    switch (Util.getFileExtension(filePath)) {
      case "ppm":
        command = new PPMFileInputCommand();
        break;
      case "png":
        break;
      default:
        throw new IllegalArgumentException("Unsupported file extension!");
    }

    if (command != null) {
      ImageModel<Pixel> extractedImage = command.extractImage(this.filePath);
      project.addImageToLayer(this.layerName, extractedImage, this.row, this.col);
    }
  }
}
