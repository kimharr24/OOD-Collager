package controller.fileio.fileinputcommands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents an abstract file input command. Contains common functionality used by all
 * file input commands.
 */
public abstract class AbstractFileInputCommand implements FileInputCommand {
  protected Scanner scanner;

  @Override
  public void initializeScanner(String filepath) throws IllegalArgumentException {
    try {
      this.scanner = new Scanner(new FileInputStream(filepath));
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException(String.format("File %s not found", filepath));
    }

    StringBuilder builder = new StringBuilder();
    while (this.scanner.hasNextLine()) {
      String currentLine = scanner.nextLine();
      builder.append(currentLine + System.lineSeparator());
    }
    this.scanner = new Scanner(builder.toString());
  }
}
