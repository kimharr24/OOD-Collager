package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents the filter that darkens the layer by subtracting the luma value from each of the
 * individual color components.
 */
public class DarkenLumaFilter extends AbstractFilter {

  /**
   * Default constructor for a darkening using the luma value filter. Sets the name of the
   * filter to "Darken Luma Filter."
   */
  public DarkenLumaFilter() {
    super("Darken Luma Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().darkenLumaColor());
      }
    }
  }
}