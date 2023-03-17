package model.images.file_input_commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.images.Image;
import model.images.ImageModel;
import model.pixels.Pixel;

/**
 * Abstract class responsible for moving common code among subclasses into this class. It also
 * performs argument checks, such as whether the filepath is valid.
 */
public abstract class AbstractFileInputCommand implements FileInputCommand<Pixel> {
  protected Scanner scanner;
  protected ImageModel<Pixel> extractedImage;

  public AbstractFileInputCommand(String filePath) {
    this.extractedImage = new Image(this.getImageWidth(filePath), this.getImageHeight(filePath));
  }

  /**
   * Checks whether the filepath is valid. If it is, instantiates the scanner to read
   * from the filepath. This method should always be called at the start of
   * FileInputCommand.extractImage().
   *
   * @throws IllegalArgumentException if the filepath is invalid.
   */
  protected void validateFilePath(String filepath) throws IllegalArgumentException {
    try {
      this.scanner = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException(String.format("File %s not found", filepath));
    }
  }
}
