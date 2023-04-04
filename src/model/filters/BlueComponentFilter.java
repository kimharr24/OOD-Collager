package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a blue component filter, that is, a filter which keeps only the blue channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (0, 0, 30, 200).
 */
public class BlueComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a blue component filter names the blue component filter
   * "Blue Component Filter."
   */
  public BlueComponentFilter() {
    super("blue-component");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().filterBlueChannel());
      }
    }
  }
}
