package model.images;

import java.util.ArrayList;
import java.util.List;

import model.ImagePixel;
import model.Position2D;
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

  @Override
  public void overlayImage(ImageModel<ImagePixel> image, int row, int col)
          throws IllegalArgumentException {

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
  public ImagePixel getPixelAtCoord(int row, int col) throws IllegalArgumentException {
    return this.imageGrid.get(row).get(col);
  }
}
