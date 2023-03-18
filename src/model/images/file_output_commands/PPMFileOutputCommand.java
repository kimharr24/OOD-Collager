package model.images.file_output_commands;

import java.io.FileWriter;
import java.io.IOException;

import model.colors.ColorModel;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

public class PPMFileOutputCommand implements FileOutputCommand<Pixel> {
  @Override
  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    Util.anyNull(new IllegalArgumentException("Image to be saved cannot be null."), image);
    if (!Util.getFileExtension(filePath).equals("ppm")) {
      throw new IllegalArgumentException("Filepath must lead to a .ppm file.");
    }

    FileWriter writer = null;
    try {
      writer = new FileWriter(filePath);
      writer.write("P3\n");
      writer.write(String.format("%d %d\n", image.getImageWidth(), image.getImageHeight()));
      writer.write(String.format("%d\n", Util.MAX_PROJECT_VALUE));

      for (int i = 0; i < image.getImageHeight(); i++) {
        for (int j = 0; j < image.getImageWidth(); j++) {
          ColorModel currentPixelColor = image.getPixelAtCoord(i, j).getColor();
          int red = (int) (currentPixelColor.getRedComponent()
                  * (currentPixelColor.getAlphaComponent() / Util.MAX_PROJECT_VALUE));
          int green = (int) (currentPixelColor.getGreenComponent()
                  * (currentPixelColor.getAlphaComponent() / Util.MAX_PROJECT_VALUE));
          int blue = (int) (currentPixelColor.getBlueComponent()
                  * (currentPixelColor.getAlphaComponent() / Util.MAX_PROJECT_VALUE));

          if (j == image.getImageWidth() - 1) {
            writer.write(String.format("%d %d %d\n", red, green, blue));
          } else {
            writer.write(String.format("%d %d %d\t", red, green, blue));
          }
        }
      }
      writer.close();
    } catch (IOException exception) {
      throw new IllegalStateException("Unable to save image to a PPM file");
    }
  }
}
