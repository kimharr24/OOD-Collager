package controller;

import java.util.Scanner;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.LoadProjectCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.CollageProject;

public class CommandController {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    CollageProject projectModel = null;
    ProjectCommand command = null;
    while(s.hasNext()) {
      String option = s.next();
      try {
        switch (option) {
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
            command = new SetFilterCommand();
            break;
          case "add-image-to-layer":
            command = new AddImageToLayerCommand(s.next(), s.next(), s.nextInt(), s.nextInt());
            break;
          case "load-project":
            command = new LoadProjectCommand(s.next());

        }
        if (command != null) command.executeCommand(projectModel);
      }
    }
  }

}
