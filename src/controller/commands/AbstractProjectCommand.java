package controller.commands;

import model.pixels.Pixel;
import model.projects.ProjectModel;
import utils.Util;

/**
 * Represents a project command with all of the common functionality extracted into this class.
 */
public abstract class AbstractProjectCommand implements ProjectCommand {
  /**
   * Checks whether the given project is null.
   * @throws IllegalArgumentException if the project is null.
   */
  protected void checkNullProject(ProjectModel<Pixel> project) {
    Util.anyNull(new IllegalArgumentException("Project cannot be null"), project);
  }
}
