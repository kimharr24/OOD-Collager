package controller.commands;

import model.CollageProject;

/**
 * Represents the interface for calling a project command.
 */
public interface ProjectCommand {

  /**
   * executes the given command when called by the given project command class.
   */
  void executeCommand(CollageProject project);
}
