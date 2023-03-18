package model.projects;

import model.layers.LayerModel;

/**
 * Represents an observable collage project, that is, a project which cannot be modified.
 *
 * @param <T> the collage project's representation of an image's pixel.
 */
public interface ProjectState<T> {
  /**
   * Returns the maximum value a pixel's color in a project is allowed to hold.
   *
   * @return the maximum value of a pixel's color.
   */
  int getMaxValue();

  /**
   * Returns the name of this project.
   *
   * @return the name of the project.
   */
  String getName();

  /**
   * Returns the height of the canvas in the collage project.
   *
   * @return the height of the canvas.
   */
  int getCanvasHeight();

  /**
   * Returns the width of the canvas in the collage project.
   *
   * @return the width of the canvas.
   */
  int getCanvasWidth();

  /**
   * Returns the number of layers contained in the collage project.
   *
   * @return the number of layers in the project.
   */
  int getLayerCount();

  /**
   * Returns the layer at the given position in the stack. For example, a position of 0 would get
   * the layer at the bottom of the stack.
   *
   * @param position the position counting from the bottom of the stack.
   * @return the layer at the given position.
   * @throws IllegalArgumentException if the given position is negative or if the given position
   *                                  is out-of-bounds based on the number of layers in the project.
   */
  LayerModel<T> getLayerAtPosition(int position) throws IllegalArgumentException;

  /**
   * Returns whether a given layer exists in a project.
   *
   * @param layerName the name of the layer to check.
   * @return true if the layer exists, false otherwise.
   */
  boolean containsLayer(String layerName);

  /**
   * Saves the image contained in the project.
   * @param filePath the file path to save the image.
   */
  void saveProjectImage(String filePath);
}
