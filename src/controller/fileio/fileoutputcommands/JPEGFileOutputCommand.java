package controller.fileio.fileoutputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;

public class JPEGFileOutputCommand extends AbstractImageFileOutputCommand {
  public JPEGFileOutputCommand() {
    super("jpg");
  }

  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    super.saveCollageImage(image, filePath);
  }
}
