package model.filters;

import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Abstract implementation of a concrete filter object. All filter objects
 * should instantiate their names using this abstract filter's constructor.
 */
public abstract class AbstractFilter implements Filter<Pixel> {
  private final String name;

  /**
   * Constructor for an abstract filter.
   * @param name the name of the filter.
   */
  public AbstractFilter(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the filter.
   * @return the filter name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Checks whether the given images are valid for a filter object to operate on.
   * @param image1 the first image.
   * @param image2 the second image.
   * @throws IllegalArgumentException if the images are invalid.
   */
  protected void checkImageValidity(ImageModel<Pixel> image1, ImageModel<Pixel> image2)
          throws IllegalArgumentException {
    this.checkNullImages(image1, image2);
    this.checkImageSizeEquality(image1, image2);
  }

  /**
   * Checks to ensure that both images are not null.
   * @param image1 the first image.
   * @param image2 the second image.
   * @throws IllegalArgumentException if either image is null.
   */
  private void checkNullImages(ImageModel<Pixel> image1, ImageModel<Pixel> image2)
          throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Current layer image and composite image " +
            "cannot be null"), image1, image2);
  }

  /**
   * Checks whether the two provided images have the same dimensions.
   * @param image1 the first image.
   * @param image2 the second image.
   * @throws IllegalArgumentException if the images have different dimensions.
   */
  private void checkImageSizeEquality(ImageModel<Pixel> image1, ImageModel<Pixel> image2)
          throws IllegalArgumentException {
    if (image1.getImageWidth() != image2.getImageWidth() ||
            image1.getImageHeight() != image2.getImageHeight()) {
      throw new IllegalArgumentException("Current layer image and composite image must be " +
              "of the same size.");
    }
  }
}
