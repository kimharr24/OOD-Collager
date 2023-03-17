package controller.commands;

import model.CollageProject;

public class AddLayerCommand implements ProjectCommand {

  private final String layerName;

  public AddLayerCommand(String layerName) {
    this.layerName = layerName;
  }

  public void executeCommand(CollageProject project) {
    project.addLayer(this.layerName);
  }
}
