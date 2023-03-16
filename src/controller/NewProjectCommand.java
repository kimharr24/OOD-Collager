package controller;

public class NewProjectCommand implements ProjectCommand {
  private final Project project;
  private final String name;
  private final int width;
  private final int height;

  public NewProjectCommand(Project project, String name, int width, int height) {
    this.project = project;
    this.name = name;
    this.width = width;
    this.height = height;
  }

  /**
   * executes the given command when called by the given project command class.
   */
  @Override
  public void executeCommand() {
    project.newProject(name, width, height);
  }
}
