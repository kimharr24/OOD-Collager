package controller.commands;

import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

/**
 * Represents the interface for calling a project command.
 */
public interface ProjectCommand {

  /**
   * executes the given command when called by the given project command class.
   */
  void executeCommand(ProjectModel<Pixel> project);
}
