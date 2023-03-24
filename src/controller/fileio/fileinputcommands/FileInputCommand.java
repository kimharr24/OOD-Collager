package controller.fileio.fileinputcommands;

/**
 * Represents a generic file input command. Supports operations that should be performed
 * by any type of file command.
 */
public interface FileInputCommand {
  /**
   * Given a filepath to a file, initializes the scanner by populating it with the content
   * contained in the file.
   * @param filepath the path to the file to read.
   * @throws IllegalArgumentException if the file does not exist.
   */
  void initializeScanner(String filepath) throws IllegalArgumentException;
}
