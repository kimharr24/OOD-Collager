package model.images.file_input_commands;

import java.util.Scanner;

import model.colors.RGBAColor;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import utils.Util;

/**
 * Represents the class for a PPM input list of commands.
 */
public class PPMInputCommand extends AbstractFileInputCommand {
  public PPMInputCommand(String filePath) {
    super(filePath);
  }

  private void initializeScanner(String filepath) {
    this.validateFilePath(filepath);

    StringBuilder builder = new StringBuilder();
    while (this.scanner.hasNextLine()) {
      String currentLine = scanner.nextLine();
      builder.append(currentLine + System.lineSeparator());
    }

    this.scanner = new Scanner(builder.toString());

    String token;
    token = scanner.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
  }

  /**
   * Extracts the given image from wherever it is being imported from, and stores it in the filepath
   * @param filepath the path to the image to load.
   * @return null
   * @throws IllegalArgumentException
   */
  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);

    int width = scanner.nextInt();
    int height = scanner.nextInt();
    int maxValue = scanner.nextInt();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double r = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        double g = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        double b = Util.convertColorScale(Util.MAX_PROJECT_VALUE, maxValue, scanner.nextInt());
        this.extractedImage.setImagePixelAtCoord(new ImagePixel(new Position2D(i, j),
                new RGBAColor(r, g, b, Util.MAX_PROJECT_VALUE)), i, j);
      }
    }
    return this.extractedImage;
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);
    return scanner.nextInt();
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    this.initializeScanner(filepath);
    int width = scanner.nextInt();
    return scanner.nextInt();
  }
}
