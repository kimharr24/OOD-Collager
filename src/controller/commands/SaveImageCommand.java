package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

public class SaveImageCommand implements ProjectCommand {
  private final String filePath;
  public SaveImageCommand(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void executeCommand(ProjectModel<Pixel> project) {
    project.saveProjectImage(filePath);
  }
}
