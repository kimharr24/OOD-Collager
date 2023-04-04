package controller;

/**
 * Represents a list of controller features that will be given to a view. The view will give
 * the controller the inputs that the user selected by passing in arguments to these
 * interface methods.
 */
public interface ControllerFeatures {
  /**
   * Given the name of a filter and the name of the layer to apply the filter to, the controller
   * updates the model to apply the filter to the specified layer.
   *
   * @param layerName  the name of the layer whose filter should be updated.
   * @param filterName the name of the filter to apply to the layer.
   */
  void applyFilter(String layerName, String filterName);

  /**
   * Given a canvas width and height, the controller will create a new collage project
   * model with the specified canvas dimensions.
   *
   * @param canvasWidth  the width of the new project canvas.
   * @param canvasHeight the height of the new project canvas.
   */
  void makeNewProject(int canvasWidth, int canvasHeight);

  /**
   * Given a filepath to an image, the name of a layer, and the row and column displacement
   * to place the image's upper-left corner, the controller extracts the image from the user's
   * filesystem and places it at the specified layer and coordinates.
   *
   * @param filepath          the path to the image that the user wants to upload.
   * @param selectedLayerName the name of the layer to upload the image onto.
   * @param row               the row displacement of the image's upper-left corner.
   * @param col               the column displacement of the images upper-left corner.
   */
  void addImageToLayer(String filepath, String selectedLayerName, int row, int col);

  /**
   * Given the name of a new layer, the controller adds the layer to the collage project.
   *
   * @param layerName the name of the new layer.
   */
  void addLayer(String layerName);

  /**
   * Given a filepath to save the project to, the controller saves the current state
   * of the collage project model.
   *
   * @param filepath the path where the controller should save the project file.
   */
  void saveProject(String filepath);

  /**
   * Given a filepath to save the composite image in the collage project, the controller
   * saves the current composite image that is shown in the view.
   *
   * @param filepath the path where the controller should save the image file.
   */
  void saveImage(String filepath);

  /**
   * Given a filepath to load a project into the existing model, the controller will
   * update the existing model to be set to the saved project file.
   *
   * @param filepath the path to the file where a previous collage project is saved.
   */
  void loadProject(String filepath);
}
