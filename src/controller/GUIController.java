package controller;

import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import view.CollageGUIView;

public class GUIController implements ControllerFeatures {
  private ProjectModel<Pixel> model;
  private CollageGUIView view;

  public GUIController(ProjectModel<Pixel> model, CollageGUIView view) {
    this.model = model;
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void applyFilter(String layerName, String filterName) {

  }

  @Override
  public void makeNewProject(int canvasWidth, int canvasHeight) {
    try {
      this.model = new CollageProject(canvasHeight, canvasWidth);
      this.view.refresh(this.model.getCompositeImage());
    } catch (IllegalArgumentException e) {
      this.view.renderErrorMessage("Please enter non-negative, non-zero integers.");
    }
  }
}
