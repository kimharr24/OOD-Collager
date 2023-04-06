package controller.fileio.fileoutputcommands;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

public abstract class AbstractImageFileOutputCommand implements FileOutputCommand<Pixel> {
  private final String formatName;

  public AbstractImageFileOutputCommand(String formatName) {
    this.formatName = formatName;
  }

  @Override
  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    BufferedImage bufferedImage = Util.createImageFromScratch(image);
    File imageFile = new File(filePath);
    try {
      System.out.println(this.formatName);
      boolean result = ImageIO.write(bufferedImage, this.formatName, imageFile);
      if (!result) { System.out.println("failed");}
    } catch (IOException ignored) {
    }
  }
}
