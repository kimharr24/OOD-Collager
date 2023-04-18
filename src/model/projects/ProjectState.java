package model.projects;

import model.colors.ColorModel;
import model.images.ImageModel;
import model.layers.LayerModel;
import model.pixels.Pixel;
import utils.Position2D;

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
   * Returns the layer at the given position in the stack. For this project, it is assumed that
   * index 0 of the layer stack represents the bottom of the stack.
   *
   * @param position the position of the layer to retrieve from the layer stack.
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
   * Builds the composite image generated from applying all filters in the current project
   * and collapsing them into a single image. This method ensures that all stored images
   * in the project maintain their original, unfiltered form before and after the
   * method is called by a client.
   *
   * @return the composite image of the project.
   */
  ImageModel<T> getCompositeImage();
}
