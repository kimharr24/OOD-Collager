package model.images;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import model.colors.ColorModel;
import model.colors.RGBAColor;
import utils.Util;

/**
 * Represents an image in the image collage project. This image class uses a 2-dimensional list
 * as its underlying data representation.
 */
public class Image implements ImageModel<Pixel> {
  private final int width;
  private final int height;
  private List<List<Pixel>> imageGrid;

  /**
   * Represents the constructor for an Image. When images are created, they are fully
   * white and transparent by default.
   *
   * @param width  the width value of the image
   * @param height the height value of the image
   * @throws IllegalArgumentException if the height or width are less than or equal to zero.
   */
  public Image(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width/height cannot be negative.");
    }
    this.width = width;
    this.height = height;
    this.colorBackground(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE, 0));
  }

  /**
   * Given an image to place above this image, collapses these two images into a single image
   * by computing the result of collapsing its pairwise pixel values.
   * @param aboveImage the image to place on top of this image.
   * @return the collapsed image.
   * @throws IllegalArgumentException if the given image is null or not the same size as this image.
   */
  public ImageModel<Pixel> collapseImage(ImageModel<Pixel> aboveImage)
          throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Image cannot be null"), aboveImage);
    if (aboveImage.getImageHeight() != this.height || aboveImage.getImageWidth() != this.width) {
      throw new IllegalArgumentException("Image dimensions do not match.");
    }
    ImageModel<Pixel> updatedImage = new Image(this.width, this.height);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        ColorModel updatedColor = aboveImage.getPixelAtCoord(i, j).getColor().getUpdatedColor(
                this.getPixelAtCoord(i, j).getColor());
        updatedImage.setImagePixelAtCoord(new ImagePixel(new Position2D(i, j), updatedColor), i, j);
      }
    }
    return updatedImage;
  }

  /**
   * Given a color, colors the entire image with that color.
   * @param color the color to color the entire image with.
   * @throws IllegalArgumentException if the provided color is null.
   */
  public void colorBackground(ColorModel color) throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Color is null."), color);
    List<List<Pixel>> image = new ArrayList<>();
    for (int row = 0; row < this.height; row++) {
      List<Pixel> currentRow = new ArrayList<>();
      for (int col = 0; col < this.width; col++) {
        currentRow.add(new ImagePixel(new Position2D(row, col), color));
      }
      image.add(currentRow);
    }
    this.imageGrid = image;
  }

  /**
   * Ensures that the given coordinate is within bounds of the image.
   *
   * @param row the row component of the coordinate.
   * @param col the column component of the coordinate.
   * @throws IllegalArgumentException if the coordinate is out-of-bounds.
   */
  private void validateCoordinate(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Coordinate cannot be negative.");
    }
    if (row >= this.height || col >= this.width) {
      throw new IllegalArgumentException("Coordinate is out-of-bounds of the image.");
    }
  }

  @Override
  public void overlayImage(ImageModel<Pixel> image, int row, int col)
          throws IllegalArgumentException {
    this.validateCoordinate(row, col);

    int extractedImageHeight = image.getImageHeight();
    int extractedImageWidth = image.getImageWidth();

    if (row + extractedImageHeight > this.height || col + extractedImageWidth > this.width) {
      throw new IllegalArgumentException("Placing the image at the provided coordinate causes it " +
              "to go out-of-bounds of the current layer.");
    }
    for (int i = 0; i < extractedImageHeight; i++) {
      for (int j = 0; j < extractedImageWidth; j++) {
        ColorModel updatedColor = image.getPixelAtCoord(i, j).getColor().getUpdatedColor(
                this.getPixelAtCoord(row + i, col + j).getColor());
        this.setImagePixelAtCoord(new ImagePixel(new Position2D(row + i, col + j),
                updatedColor), row + i, col + j);
      }
    }
  }

  @Override
  public void setImagePixelAtCoord(Pixel pixel, int row, int col)
          throws IllegalArgumentException {
    this.validateCoordinate(row, col);
    Util.anyNull(new IllegalArgumentException("Pixel is null."), pixel);
    List<Pixel> targetRow = this.imageGrid.get(row);
    targetRow.set(col, pixel);
    this.imageGrid.set(row, targetRow);
  }

  @Override
  public Pixel getPixelAtCoord(int row, int col) throws IllegalArgumentException {
    this.validateCoordinate(row, col);
    return this.imageGrid.get(row).get(col);
  }

  @Override
  public int getImageHeight() {
    return this.height;
  }

  @Override
  public int getImageWidth() {
    return this.width;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof ImageModel)) {
      return false;
    }
    ImageModel<Pixel> otherImage = (Image) other;
    if (otherImage.getImageWidth() != this.getImageWidth()
            || otherImage.getImageHeight() != this.getImageHeight()) {
      return false;
    }

    boolean result = true;
    for (int i = 0; i < this.getImageHeight(); i++) {
      for (int j = 0; j < this.getImageWidth(); j++) {
        result = result && this.getPixelAtCoord(i, j).getColor().equals(
                otherImage.getPixelAtCoord(i, j).getColor());
      }
    }
    return result;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width, this.height);
  }
}
