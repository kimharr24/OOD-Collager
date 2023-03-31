package view;

import java.util.Map;

import controller.ControllerFeatures;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents all the operations that should be supported by a GUI in the collage project.
 */
public interface CollageGUIView<T> {
  /**
   * Renders the composite image that the user is currently working on in the project.
   * @param image the composite image after all filters are applied.
   * @throws IllegalArgumentException if the image is null or the image has a size of 0.
   */
  void renderImage(ImageModel<Pixel> image);

//  void renderLayerOptions();

  /**
   * Renders an error message to the user in case something goes wrong.
   * @param message the message to render.
   */
  void renderErrorMessage(String message);

  void addFeatures(ControllerFeatures features);

  void refresh(ImageModel<T> compositeImage, Map<String, String> layerNameToFilterName);
}
