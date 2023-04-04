package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that darkens the layer by subtracting the average value of the three color
 * components from each individual color component.
 */
public class DarkenIntensityFilter extends AbstractFilter {

  /**
   * Default constructor for a darkening intensity by value filter. Sets the name of the
   * filter to "Darken Intensity Filter."
   */
  public DarkenIntensityFilter() {
    super("darken-intensity");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().darkenIntensityColor());
      }
    }
  }
}
