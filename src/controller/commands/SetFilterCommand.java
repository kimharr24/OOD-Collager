package controller.commands;

import model.pixels.Pixel;
import model.filters.Filter;
import model.projects.ProjectModel;

/**
 * Represents a command that can be used to set the filter option of a particular
 * layer in the collage project.
 */
public class SetFilterCommand extends AbstractProjectCommand {
  private final String layerName;
  private final String filterOption;

  /**
   * Sets the name of the layer whose filter to modify as well as the new filter.
   * @param layerName the name of the layer whose filter will change.
   * @param filterOption the filter to change it to.
   */
  public SetFilterCommand(String layerName, String filterOption) {
    this.layerName = layerName;
    this.filterOption = filterOption;
  }

  @Override
  public void executeCommand(ProjectModel<Pixel> project) {
    this.checkNullProject(project);
    Filter<Pixel> filter = project.getFilterFromName(this.filterOption);

    project.setLayerFilter(this.layerName, filter);
  }
}
