package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a red component filter, that is, a filter which keeps only the red channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (10, 0, 0, 200).
 */
public class RedComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a red component filter names the red component filter
   * "Red Component Filter."
   */
  public RedComponentFilter() {
    super("red-component");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().filterRedChannel());
      }
    }
  }
}
