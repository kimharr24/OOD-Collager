package controller;

import java.util.HashMap;
import java.util.Map;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.layers.LayerModel;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;
import view.CollageGUIView;

public class GUIController implements ControllerFeatures {
  private ProjectModel<Pixel> model;
  private CollageGUIView view;

  public GUIController(ProjectModel<Pixel> model, CollageGUIView view) {
    Util.anyNull(new IllegalArgumentException("Model cannot be null."), model);
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void applyFilter(String layerName, String filterName) {
    ProjectCommand command = new SetFilterCommand(layerName, filterName);
    try {
      command.executeCommand(this.model);
      this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage("Selected layer does not exist.");
    }
  }

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
    command.executeCommand(this.model);
    this.view.refresh(this.model.getCompositeImage(), this.createLayerToFilterMap());
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

  }
}
