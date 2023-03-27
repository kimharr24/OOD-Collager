package controller;

public interface ControllerFeatures {
  void applyFilter(String layerName, String filterName);

  void makeNewProject(int canvasWidth, int canvasHeight);
}
