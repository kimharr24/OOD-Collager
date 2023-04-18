package controller.fileio.fileinputcommands;

import model.projects.ProjectModel;
import model.pixels.Pixel;

/**
 * Represents a project file input command, that is, a command which can extract a collage project
 * in any format and give it to the requester.
 * @param <T> the project's pixel representation.
 */
public interface ProjectFileInputCommand<T> {
  /**
   * Given a filepath to a collage project, extracts the project from the file.
   * @param filepath the path to the collage project.
   * @param project the collage project.
   * @return the project populated with the saved data.
   * @throws IllegalStateException if the file does not exist or the project is null'
   */
  ProjectModel<T> extractProject(String filepath, ProjectModel<Pixel> project)
          throws IllegalArgumentException;
}
