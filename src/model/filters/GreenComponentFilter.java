package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents a green component filter, that is, a filter which keeps only the green channel
 * of a color. For example, the RGBA color (10, 20, 30, 200) becomes (0, 20, 0, 200).
 */
public class GreenComponentFilter extends AbstractFilter {
  /**
   * Default constructor for a green component filter names the green component filter
   * "Green Component Filter."
   */
  public GreenComponentFilter() {
    super("green-component");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    this.checkImageValidity(image, compositeImage);
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        currentPixel.setColor(currentPixel.getOriginalColor().filterGreenChannel());
      }
    }
  }
}
