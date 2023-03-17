package controller.commands;

import model.projects.CollageProject;

public class LoadProjectCommand implements ProjectCommand {
  private final String fileName;

  public LoadProjectCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void executeCommand(CollageProject project) {
  }
}

