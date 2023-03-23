package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that brightens the layer by adding the average value of the three color
 * components to each individual color component.
 */
public class BrightenIntensityFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening intensity by value filter. Sets the name of the
   * filter to "Brighten Intensity Filter."
   */
  public BrightenIntensityFilter() {
    super("Brighten Intensity Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().brightenIntensityColor());
      }
    }

  }
}
