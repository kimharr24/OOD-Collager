package controller.commands;

import javax.swing.*;

public class QuitProjectCommand implements ProjectCommand {
  private final Project project;

  public QuitProjectCommand(Project project) {
    this.project = project;
  }

  @Override
  public void executeCommand(CollageProject project) {
    int answer = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to quit? All unsaved work will be lost.",
            "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    if (answer == JOptionPane.YES_OPTION) {
      project.quitProject();
    }
  }
}

