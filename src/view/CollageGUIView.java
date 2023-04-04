package view;

import java.util.Map;

import controller.ControllerFeatures;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents all the operations that should be supported by a GUI in the collage project.
 *
 * @param <T> the image's representation of a pixel.
 */
public interface CollageGUIView<T> {
  /**
   * Renders the composite image that the user is currently working on in the project.
   *
   * @param image the composite image after all filters are applied.
   * @throws IllegalArgumentException if the image is null or the image has a size of 0.
   */
  void renderImage(ImageModel<Pixel> image);

  /**
   * Renders an error message to the user in case something goes wrong.
   *
   * @param message the message to render.
   */
  void renderErrorMessage(String message);

  /**
   * Given a list of controller features, the view should save these features to callback
   * when an event is triggered.
   *
   * @param features the supported controller features.
   * @throws IllegalArgumentException if features is null.
   */
  void addFeatures(ControllerFeatures features);

  /**
   * Given an updated composite image to display, as well as an updated mapping between layers
   * and filters in the collage project, the view will refresh the necessary components.
   *
   * @param compositeImage        the updated composite image.
   * @param layerNameToFilterName the updated layer to filter name mapping.
   * @throws IllegalArgumentException if the compositeImage or mapping is null.
   */
  void refresh(ImageModel<T> compositeImage, Map<String, String> layerNameToFilterName);
}
