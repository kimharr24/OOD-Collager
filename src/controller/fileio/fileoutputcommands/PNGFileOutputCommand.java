package controller.fileio.fileoutputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;

public class PNGFileOutputCommand extends AbstractImageFileOutputCommand {
  public PNGFileOutputCommand() {
    super("png");
  }

  public void saveCollageImage(ImageModel<Pixel> image, String filePath) {
    super.saveCollageImage(image, filePath);
  }
}
