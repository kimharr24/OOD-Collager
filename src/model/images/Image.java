package model.images;

import java.util.ArrayList;
import java.util.List;

import model.images.file_input_commands.FileInputCommand;
import model.images.file_input_commands.PPMInputCommand;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import model.colors.ColorModel;
import model.colors.RGBAColor;
import utils.Util;

/**
 * Represents an image in the image collage project.
 */
public class Image implements ImageModel<Pixel> {
  private final int width;
  private final int height;
  private List<List<Pixel>> imageGrid;

  /**
   * Represents the constructor for an Image.
   * @param width the width value of the image
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
  public void overlayImage(String filePath, int row, int col) throws IllegalArgumentException {
    this.validateCoordinate(row, col);

    FileInputCommand<Pixel> command = null;
    switch (Util.getFileExtension(filePath)) {
      case "ppm":
        command = new PPMInputCommand(filePath);
        break;
    }

    if (command == null) {
      throw new IllegalArgumentException(String.format("%s is an unsupported file extension",
              Util.getFileExtension(filePath)));
    }
    ImageModel<Pixel> extractedImage = command.extractImage(filePath);
    int extractedImageHeight = command.getImageHeight(filePath);
    int extractedImageWidth = command.getImageWidth(filePath);

    if (row + extractedImageHeight > this.height || col + extractedImageWidth > this.width) {
      throw new IllegalArgumentException("Placing the image at the provided coordinate causes it " +
              "to go out-of-bounds of the current layer.");
    }
    for (int i = 0; i < extractedImageHeight; i++) {
      for (int j = 0; j < extractedImageWidth; j++) {
        ColorModel updatedColor = extractedImage.getPixelAtCoord(i, j).getColor().getUpdatedColor(
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
}
