package model.filters;

import model.colors.ColorModel;
import model.colors.HSLAColor;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Represents a filter that darkens the image based on the lightness of the composite image
 * below the current image.
 */
public class DarkeningBlendingFilter extends AbstractFilter {
  /**
   * Default constructor for a darkening blending filter. Sets the name of the filter
   * to "Darkening Blending Filter" by default.
   */
  public DarkeningBlendingFilter() {
    super("Darkening Blending Filter");
  }

  @Override
  public void apply(ImageModel<Pixel> image, ImageModel<Pixel> compositeImage) {
    for (int i = 0; i < image.getImageHeight(); i++) {
      for (int j = 0; j < image.getImageWidth(); j++) {
        Pixel currentPixel = image.getPixelAtCoord(i, j);
        Pixel compositePixel = compositeImage.getPixelAtCoord(i, j);

        ColorModel currentPixelColor = currentPixel.getColor();
        ColorModel compositePixelColor = compositePixel.getColor();

        HSLAColor currentPixelHSLA = Util.convertRGBAtoHSLA(
                currentPixelColor.getRedComponent() / Util.MAX_PROJECT_VALUE,
                currentPixelColor.getGreenComponent() / Util.MAX_PROJECT_VALUE,
                currentPixelColor.getBlueComponent() / Util.MAX_PROJECT_VALUE,
                currentPixelColor.getAlphaComponent() / Util.MAX_PROJECT_VALUE);

        HSLAColor compositePixelHSLA = Util.convertRGBAtoHSLA(
                compositePixelColor.getRedComponent() / Util.MAX_PROJECT_VALUE,
                compositePixelColor.getGreenComponent() / Util.MAX_PROJECT_VALUE,
                compositePixelColor.getBlueComponent() / Util.MAX_PROJECT_VALUE,
                compositePixelColor.getAlphaComponent() / Util.MAX_PROJECT_VALUE);

        HSLAColor updatedCurrentPixelHSLA = new HSLAColor(currentPixelHSLA.getHue(),
                currentPixelHSLA.getSaturation(), currentPixelHSLA.getLightness() *
                compositePixelHSLA.getLightness(), currentPixelHSLA.getAlpha());

        ColorModel updatedCurrentPixelRGBA = Util.convertHSLAtoRGBA(
                updatedCurrentPixelHSLA.getHue(),
                updatedCurrentPixelHSLA.getSaturation(),
                updatedCurrentPixelHSLA.getLightness(),
                updatedCurrentPixelHSLA.getAlpha());

        currentPixel.setColor(updatedCurrentPixelRGBA);
      }
    }
  }
}
