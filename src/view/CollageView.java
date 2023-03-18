package view;

/**
 * Represents the user interface through which a user of the collage project can
 * interact with the program.
 */
public interface CollageView {
  /**
   * Transmits the given message to a user of the collage project.
   * @param message the message to be transmitted.
   */
  void renderMessage(String message);

  /**
   * Shows the user of the collage project the available commands as
   * well as their corresponding syntax.
   */
  void renderCommandOptions();

  /**
   * Shows the user the available filter options in the collage project.
   */
  void renderFilterOptions();
}
