package controller.fileio.fileinputcommands;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.images.Image;
import model.images.ImageModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;

public abstract class AbstractImageFileInputCommand implements ImageFileInputCommand<Pixel> {
  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    File file = new File(filepath);
    ImageModel<Pixel> extractedImage = new Image(this.getImageWidth(filepath),
            this.getImageHeight(filepath));
    try {
      BufferedImage image = ImageIO.read(file);
      for (int x = 0; x < image.getWidth(); x++) {
        for (int y = 0; y < image.getHeight(); y++) {
          int pixelColor = image.getRGB(x, y);
          Color color = new Color(pixelColor, true);

          double red = color.getRed();
          double green = color.getGreen();
          double blue = color.getBlue();
          double alpha = color.getAlpha();

          ColorModel rgbaColor = new RGBAColor(red, green, blue, alpha);
          extractedImage.setImagePixelAtCoord(new ImagePixel(new Position2D(y, x),
                  rgbaColor), y, x);
        }
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot read file or create buffered image");
    }
    return extractedImage;
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    File file = new File(filepath);
    try {
      BufferedImage image = ImageIO.read(file);
      return image.getWidth();
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot read file or create buffered image");
    }
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    File file = new File(filepath);
    try {
      BufferedImage image = ImageIO.read(file);
      return image.getHeight();
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot read file or create buffered image");
    }
  }
}
