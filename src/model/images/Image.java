package model.images;

import java.util.ArrayList;
import java.util.List;

import model.filters.Filter;
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
   * Represents the constructor for an Image. When images are created, they are fully
   * white and transparent by default.
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

  /**
   * Private constructor for an image to hide away the data representation of an image from
   * clients. This constructor is used solely for modifying the existing image by providing
   * a new image to it.
   * @param width the width of the new image
   * @param height the height of the new image
   * @param imageGrid the updated image.
   * @throws IllegalArgumentException if the width/height are negative, if the image is null,
   * or if the image's dimensions do not match the supplied width/height.
   */
  private Image(int width, int height, List<List<Pixel>> imageGrid) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width/height cannot be negative.");
    }
    Util.anyNull(new IllegalArgumentException("Image cannot be null"), imageGrid);
    if (imageGrid.size() != height || imageGrid.get(0).size() != width) {
      throw new IllegalArgumentException("Provided image does not match height and " +
              "width dimensions.");
    }
    this.width = width;
    this.height = height;
    this.imageGrid = this.createImageGridCopy(imageGrid);
  }

  /**
   * Create a deep copy of the image's grid.
   * @param originalImage the original image to be copied
   * @return the deep copy of the image grid.
   * @throws IllegalArgumentException if the original image is null or empty.
   */
  private List<List<Pixel>> createImageGridCopy(List<List<Pixel>> originalImage) {
    List<List<Pixel>> copiedImageGrid = new ArrayList<>();
    for (int i = 0; i < originalImage.size(); i++) {
      List<Pixel> currentRow = new ArrayList<>();
      for (int j = 0; j < originalImage.get(0).size(); j++) {
        currentRow.add(originalImage.get(i).get(j).createCopy());
      }
      copiedImageGrid.add(currentRow);
    }
    return copiedImageGrid;
  }

  public ImageModel<Pixel> createCopy() {
    return new Image(this.width, this.height, this.imageGrid);
  }

  public void applyFilter(Filter filter) throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Filter is null!"), filter);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.getPixelAtCoord(i, j).applyFilter(filter);
      }
    }
  }

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
