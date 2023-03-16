package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class SaveProjectCommand implements ProjectCommand {

  private final Project project;

  private final String fileName;

  public SaveProjectCommand(Project project, String fileName) {
    this.project = project;
    this.fileName = fileName;
  }

  /**
   * executes the given command when called by the given project command class.
   */
  public void executeCommand() {
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(project);
      out.close();
      fileOut.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,
              "Error saving project: " + e.getMessage(),
              "Error", JOptionPane.ERROR_MESSAGE);
    }
    this.project.saveProject();
  }
}
