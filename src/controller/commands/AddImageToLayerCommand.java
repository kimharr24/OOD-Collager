package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

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

  public void executeCommand(ProjectModel<Pixel> project) {
    this.checkNullProject(project);
    project.addImageToLayer(this.layerName, this.filePath, this.row, this.col);
  }
}
