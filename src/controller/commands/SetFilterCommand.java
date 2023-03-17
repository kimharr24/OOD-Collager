package controller.commands;

import model.CollageProject;
import model.filters.BrightenValueFilter;
import model.filters.Filter;
import model.filters.NormalFilter;
import model.filters.RedComponentFilter;

public class SetFilterCommand implements ProjectCommand {

  private final String layerName;
  private final String filterOption;

  public SetFilterCommand(String layerName, String filterOption) {
    this.layerName = layerName;
    this.filterOption = filterOption;
  }

  // finish the rest of the cases for choosing different filters (go to hw 3.3)
  public void executeCommand(CollageProject project) {
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
          filter = new RedComponentFilter();
          break;
        case "green-component":
          filter = new RedComponentFilter();
          break;
        case "brighten-value":
          filter = new BrightenValueFilter();
          break;
        case "darken-value":
          filter = new
      }
    }
    project.setLayerFilter(this.layerName, filter);
  }
}
