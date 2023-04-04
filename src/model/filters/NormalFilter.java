package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a normal filter, that is, a filter which does not do
 * anything to the layer it is applied to.
 */
public class NormalFilter extends AbstractFilter {
  /**
   * Default constructor for a normal filter. The default constructor
   * names this filter "Normal Filter."
   */
  public NormalFilter() {
    super("normal");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor());
      }
    }
  }
}
