package controller.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;

import model.CollageProject;

public class LoadProjectCommand implements ProjectCommand {
  private final String fileName;

  public LoadProjectCommand(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void executeCommand(CollageProject project) {
    try {
      FileInputStream fileIn = new FileInputStream(fileName);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      CollageProject project = (CollageProject) in.readObject();
      in.close();
      fileIn.close();
      CollageProject.setCurrentProject(project);
    } catch (IOException | ClassNotFoundException e) {
      JOptionPane.showMessageDialog(null,
              "Error loading project: " + e.getMessage(), "Error",
              JOptionPane.ERROR_MESSAGE);
    }
    project.loadProject(fileName);
  }
}

