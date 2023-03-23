package model.filters;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Represents an inversion blending filter, that is, a filter which inverts
 * the colors of an image on the current layer based on the composite image below it.
 */
public class InversionBlendingFilter extends AbstractFilter {

  /**
   * Default constructor for inversion blending filter. Sets the name of the
   * filter to "Inversion Blending Filter."
   */
  public InversionBlendingFilter() {
    super("Inversion Blending Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        Pixel compositePixel = compositeImage.getPixelAtCoord(i, j);

        ColorModel currentPixelColor = currentPixel.getColor();
        ColorModel compositePixelColor = compositePixel.getColor();

        double red = Math.abs(currentPixelColor.getRedComponent() -
                compositePixelColor.getRedComponent());
        double green = Math.abs(currentPixelColor.getGreenComponent() -
                compositePixelColor.getGreenComponent());
        double blue = Math.abs(currentPixelColor.getBlueComponent() -
                compositePixelColor.getBlueComponent());

        currentPixel.setColor(new RGBAColor(red, green, blue,
                currentPixelColor.getAlphaComponent()));
      }
    }
  }
}
