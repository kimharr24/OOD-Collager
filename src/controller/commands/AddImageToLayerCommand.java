package controller.commands;

import model.CollageProject;
import model.ImagePixel;
import model.images.ImageModel;

public class AddImageToLayerCommand implements ProjectCommand {

  private final String layerName;
  private final String filePath;
  private final int row;
  private final int col;

  public AddImageToLayerCommand(String layerName, String filePath, int row, int col) {
    this.layerName = layerName;
    this.filePath = filePath;
    this.row = row;
    this.col = col;
  }

  public void executeCommand(CollageProject project) {
    project.addImageToLayer(this.layerName, this.filePath, this.row, this.col);
  }
}
