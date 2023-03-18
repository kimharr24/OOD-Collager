package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.Controller;
import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;

public class CommandController implements Controller {
  private final Scanner scanner;
  private ProjectModel<Pixel> model;

  public CommandController(Readable input) {
    this.scanner = new Scanner(input);
  }

  @Override
  public void executeProgram() {
    ProjectCommand command = null;

    while (this.scanner.hasNext()) {
      String option = scanner.next();
      try {
        switch (option) {
          case "quit":
            return;
          case "new-project":
            this.model = new CollageProject(scanner.next(), scanner.nextInt(), scanner.nextInt());
            break;
          case "save-project":
            command = new SaveProjectCommand();
            break;
          case "add-layer":
            command = new AddLayerCommand(scanner.next());
            break;
          case "set-filter":
            command = new SetFilterCommand(scanner.next(), scanner.next());
            break;
          case "add-image-to-layer":
            command = new AddImageToLayerCommand(scanner.next(), scanner.next(),
                    scanner.nextInt(), scanner.nextInt());
            break;
          case "load-project":
            break;
          case "save-image":
            command = new SaveImageCommand(scanner.next());
            break;
          default:
            System.out.println("Unknown command");
            break;
        }
        if (command != null) {
          command.executeCommand(this.model);
        }
      } catch (InputMismatchException ignored) {
        System.out.println("Something went wrong");
      }
    }
  }
}
