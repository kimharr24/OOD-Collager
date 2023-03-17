package controller.commands;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import model.CollageProject;

public class SaveProjectCommand implements ProjectCommand {

  public SaveProjectCommand() {

  }

  /**
   * executes the given command when called by the given project command class.
   */
  public void executeCommand(CollageProject project) {
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
