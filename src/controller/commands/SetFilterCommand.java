package controller.commands;

import java.util.InputMismatchException;

import model.filters.BrightenIntensityFilter;
import model.filters.BrightenLumaFilter;
import model.filters.DarkenIntensityFilter;
import model.filters.DarkenLumaFilter;
import model.filters.DarkenValueFilter;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.filters.BlueComponentFilter;
import model.filters.BrightenValueFilter;
import model.filters.Filter;
import model.filters.GreenComponentFilter;
import model.filters.NormalFilter;
import model.filters.RedComponentFilter;
import model.projects.ProjectModel;

/**
 * Represents a command that can be used to set the filter option of a particular
 * layer in the collage project.
 */
public class SetFilterCommand implements ProjectCommand {
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
    Filter filter = null;
    try {
      switch (this.filterOption) {
        case "normal":
          filter = new NormalFilter();
          break;
        case "red-component":
          filter = new RedComponentFilter();
          break;
        case "blue-component":
          filter = new BlueComponentFilter();
          break;
        case "green-component":
          filter = new GreenComponentFilter();
          break;
        case "brighten-value":
          filter = new BrightenValueFilter();
          break;
        case "darken-value":
          filter = new DarkenValueFilter();
          break;
        case "brighten-intensity":
          filter = new BrightenIntensityFilter();
          break;
        case "darken-intensity":
          filter = new DarkenIntensityFilter();
          break;
        case "brighten-luma":
          filter = new BrightenLumaFilter();
          break;
        case "darken-luma":
          filter = new DarkenLumaFilter();
          break;
        default:
          System.out.println("Unknown command");
          break;
      }
    } catch (InputMismatchException ignored) {

    }
    project.setLayerFilter(this.layerName, filter);
  }
}
