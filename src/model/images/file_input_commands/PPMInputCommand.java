package model.images.file_input_commands;

import model.images.ImageModel;
import model.pixels.Pixel;

public class PPMInputCommand extends AbstractFileInputCommand {
  @Override
  public ImageModel<Pixel> extractImage(String filepath) throws IllegalArgumentException {
    this.validateFilePath(filepath);
    return null;
  }

  @Override
  public int getImageWidth(String filepath) throws IllegalArgumentException {
    return 0;
  }

  @Override
  public int getImageHeight(String filepath) throws IllegalArgumentException {
    return 0;
  }
}
