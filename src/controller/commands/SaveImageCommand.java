package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

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
    project.saveProjectImage(filePath);
  }
}
