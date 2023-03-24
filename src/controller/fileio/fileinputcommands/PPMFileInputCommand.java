package controller.fileio.fileinputcommands;

import model.colors.RGBAColor;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import utils.Util;

/**
 * Represents a PPM input command, that is, a command responsible for extracting an image
 * from a PPM P3 file.
 */
public class PPMFileInputCommand extends AbstractFileInputCommand
        implements ImageFileInputCommand<Pixel> {
  /**
   * Validates the formatting of the PPM file. PPM files should begin with P3.
   * @throws IllegalStateException if the format of the PPM file is incorrect.
   */
  private void validatePPMToken() throws IllegalStateException {
    String token;
    token = scanner.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }
  }

  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    ImageModel<Pixel> extractedImage = new Image(this.getImageWidth(filepath),
            this.getImageHeight(filepath));
    this.initializeScanner(filepath);
    this.validatePPMToken();

    int width = scanner.nextInt();
    int height = scanner.nextInt();
    int maxValue = scanner.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double r = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        double g = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        double b = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        extractedImage.setImagePixelAtCoord(new ImagePixel(new Position2D(i, j),
                new RGBAColor(r, g, b, Util.MAX_PROJECT_VALUE)), i, j);
      }
    }
    return extractedImage;
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);
    this.validatePPMToken();

    return scanner.nextInt();
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);
    this.validatePPMToken();

    int width = scanner.nextInt();
    return scanner.nextInt();
  }
}
