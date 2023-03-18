package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

/**
 * Represents a command to add a layer in the collage project.
 */
public class AddLayerCommand extends AbstractProjectCommand {
  private final String layerName;

  /**
   * Default constructor for the command takes in the name of the layer to create.
   * @param layerName the name of the new layer.
   */
  public AddLayerCommand(String layerName) {
    this.layerName = layerName;
  }

  public void executeCommand(ProjectModel<Pixel> project) {
    this.checkNullProject(project);
    project.addLayer(this.layerName);
  }
}
