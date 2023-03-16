package controller;

/**
 * Represents the interface for calling a project command.
 */
public interface ProjectCommand {

  /**
   * executes the given command when called by the given project command class.
   */
  public void executeCommand();
}
