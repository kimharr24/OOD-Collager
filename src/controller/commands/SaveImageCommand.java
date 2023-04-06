package controller.commands;

import controller.fileio.fileoutputcommands.FileOutputCommand;
import controller.fileio.fileoutputcommands.JPEGFileOutputCommand;
import controller.fileio.fileoutputcommands.PNGFileOutputCommand;
import controller.fileio.fileoutputcommands.PPMFileOutputCommand;
import model.images.ImageModel;
import model.pixels.Pixel;
import model.projects.ProjectModel;
import utils.Util;

/**
 * Command for saving an image in a collage project.
 */
public class SaveImageCommand extends AbstractProjectCommand {
  private final String filePath;

  /**
   * Default constructor for saving an image takes in the filepath representing where
   * the image will be saved.
   * @param filePath the file path to save the image to.
   */
  public SaveImageCommand(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void executeCommand(ProjectModel<Pixel> project) {
    this.checkNullProject(project);
    FileOutputCommand<Pixel> command = null;

    switch (Util.getFileExtension(this.filePath)) {
      case "ppm":
        command = new PPMFileOutputCommand();
        break;
      case "png":
        command = new PNGFileOutputCommand();
        break;
      case "jpeg":
      case "jpg":
        command = new JPEGFileOutputCommand();
        break;
      default:
        throw new IllegalArgumentException("Unknown file extension");
    }
    ImageModel<Pixel> compositeImage = project.getCompositeImage();
    command.saveCollageImage(compositeImage, this.filePath);
  }
}
