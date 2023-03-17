package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.LoadProjectCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.projects.CollageProject;

public class CommandController {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    CollageProject projectModel = null;
    ProjectCommand command = null;
    while(s.hasNext()) {
      String option = s.next();
      try {
        switch (option) {
          case "quit":
            return;
          case "new-project":
            projectModel = new CollageProject(s.next(), s.nextInt(), s.nextInt());
            break;
          case "save-project":
            command = new SaveProjectCommand();
            break;
          case "add-layer":
            command = new AddLayerCommand(s.next());
            break;
          case "set-filter":
            command = new SetFilterCommand(s.next(), s.next());
            break;
          case "add-image-to-layer":
            command = new AddImageToLayerCommand(s.next(), s.next(), s.nextInt(), s.nextInt());
            break;
          case "load-project":
            command = new LoadProjectCommand(s.next());
            break;
          default:
            System.out.println("Unknown command");
            break;
        }
        if (command != null) {
          command.executeCommand(projectModel);
        }
      } catch (InputMismatchException ignored) {
        System.out.println("Something went wrong");
      }
    }
  }
}
