package controller;

import java.util.HashMap;
import java.util.Map;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import controller.fileio.fileinputcommands.CollageProjectFileInputCommand;
import controller.fileio.fileinputcommands.ProjectFileInputCommand;
import model.layers.LayerModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;
import view.CollageGUIView;

/**
 * Represents a controller that interacts with a GUI view. The controller can receive input
 * from the GUI by following the general callback design pattern.
 */
public class GUIController implements ControllerFeatures {
  private ProjectModel<Pixel> model;
  private final CollageGUIView<Pixel> view;

  /**
   * Constructor for a controller that interacts with a GUI controller expects the
   * view to be a GUI implementation.
   *
   * @param model a model representing a collage project.
   * @param view  the GUI view the controller interacts with.
   */
  public GUIController(ProjectModel<Pixel> model, CollageGUIView<Pixel> view) {
    Util.anyNull(new IllegalArgumentException("Model or view cannot be null."), model, view);
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
    this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
  }

  @Override
  public void applyFilter(String layerName, String filterName) {
    ProjectCommand command = new SetFilterCommand(layerName, filterName);
    try {
      command.executeCommand(this.model);
      this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage("Selected layer or filter does not exist.");
    }
  }

  /**
   * Creates a mapping between layers in the current collage project to their
   * corresponding filters.
   *
   * @return the map between the layer name and the filter name.
   */
  private Map<String, String> createLayerToFilterMap() {
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < this.model.getLayerCount(); i++) {
      LayerModel<Pixel> currentLayer = this.model.getLayerAtPosition(i);
      map.put(currentLayer.getLayerName(), currentLayer.getFilterName());
    }
    return map;
  }

  @Override
  public void makeNewProject(int canvasWidth, int canvasHeight) {
    try {
      this.model = new CollageProject(canvasHeight, canvasWidth);
      this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage("Please enter non-negative, non-zero integers.");
    }
  }

  @Override
  public void addImageToLayer(String filepath, String selectedLayerName, int row, int col) {
    try {
      ProjectCommand command = new AddImageToLayerCommand(selectedLayerName, filepath, row, col);
      try {
        command.executeCommand(this.model);
        this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
      } catch (IllegalArgumentException e) {
        this.view.renderErrorMessage("Unsupported file extension or unsupported file format or " +
                "image will be out-of-bounds of the screen.");
      }
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage("Please enter non-negative numbers for the displacement.");
    }
  }

  @Override
  public void addLayer(String layerName) {
    ProjectCommand command = new AddLayerCommand(layerName);
    try {
      command.executeCommand(this.model);
      this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage(String.format("Layer %s already exists!", layerName));
    }
  }

  @Override
  public void saveProject(String filepath) {
    ProjectCommand command = new SaveProjectCommand(filepath);
    command.executeCommand(this.model);
  }

  @Override
  public void saveImage(String filepath) {
    ProjectCommand command = new SaveImageCommand(filepath);
    command.executeCommand(this.model);
  }

  @Override
  public void loadProject(String filepath) {
    ProjectFileInputCommand<Pixel> command = new CollageProjectFileInputCommand();
    this.model = command.extractProject(filepath);
    this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
  }
}
