package view;

/**
 * Represents the user interface through which a user of the collage project can
 * interact with the program.
 */
public interface CollageView {
  /**
   * Renders the given message to a user of the collage project.
   * @param message the message to be transmitted.
   */
  void renderMessage(String message);

  /**
   * Displays all supported commands of the collage project to the user, as well as their
   * corresponding syntax.
   */
  void renderCommandOptions();

  /**
   * Displays all supported filter options in the collage project to the user.
   */
  void renderFilterOptions();
}
