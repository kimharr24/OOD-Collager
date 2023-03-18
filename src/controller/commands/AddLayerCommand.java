package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

public class AddLayerCommand implements ProjectCommand {
  private final String layerName;

  public AddLayerCommand(String layerName) {
    this.layerName = layerName;
  }

  public void executeCommand(ProjectModel<Pixel> project) {
    project.addLayer(this.layerName);
  }
}
