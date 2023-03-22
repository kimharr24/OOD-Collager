package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;

/**
 * Represents the interface for calling a project command.
 */
public interface ProjectCommand {
  /**
   * Executes the given command when called by the given project command class.
   * @throws IllegalArgumentException if the project is null.
   */
  void executeCommand(ProjectModel<Pixel> project);
}
