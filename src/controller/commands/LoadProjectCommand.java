package controller.commands;

import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

public class LoadProjectCommand implements ProjectCommand {
  private final String fileName;

  public LoadProjectCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void executeCommand(ProjectModel<Pixel> project) {
  }
}

