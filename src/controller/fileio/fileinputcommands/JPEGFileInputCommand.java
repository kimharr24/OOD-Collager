package controller.fileio.fileinputcommands;

import model.images.ImageModel;
import model.pixels.Pixel;

public class JPEGFileInputCommand extends AbstractImageFileInputCommand {

  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    return super.extractImage(filepath);
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    return super.getImageWidth(filepath);
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    return super.getImageHeight(filepath);
  }
}
