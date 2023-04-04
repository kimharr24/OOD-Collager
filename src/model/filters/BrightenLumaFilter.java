package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that brightens the layer by adding the luma value to each of the
 * individual color components.
 */
public class BrightenLumaFilter extends AbstractFilter {

  /**
   * Default constructor for a brightening luma by value filter. Sets the name of the
   * filter to "Brighten Luma Filter."
   */
  public BrightenLumaFilter() {
    super("brighten-luma");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().brightenLumaColor());
      }
    }
  }
}
