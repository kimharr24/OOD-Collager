package model.images;

import java.util.ArrayList;
import java.util.List;

import model.pixels.ImagePixel;
import utils.Position2D;
import model.colors.ColorModel;
import model.colors.RGBAColor;

public class Image implements ImageModel<ImagePixel> {
  private final int width;
  private final int height;
  private List<List<ImagePixel>> imageGrid;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.colorBackground(new RGBAColor(255, 255, 255, 0, 8));
  }

  public void colorBackground(ColorModel color) {
    List<List<ImagePixel>> image = new ArrayList<>();
    for (int row = 0; row < this.width; row++) {
      List<ImagePixel> currentRow = new ArrayList<>();
      for (int col = 0; col < this.height; col++) {
        currentRow.add(new ImagePixel(new Position2D(row, col), color));
      }
      image.add(currentRow);
    }
    this.imageGrid = image;
  }

  /**
   * Ensures that the given coordinate is within bounds of the image.
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
  public void overlayImage(String filePath, int row, int col)
          throws IllegalArgumentException {
    this.validateCoordinate(row, col);
  }

  @Override
  public void setImagePixelAtCoord(ImagePixel pixel, int row, int col)
          throws IllegalArgumentException {
    this.validateCoordinate(row, col);
  }

  @Override
  public ImagePixel getPixelAtCoord(int row, int col) throws IllegalArgumentException {
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
}
