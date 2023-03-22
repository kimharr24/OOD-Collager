package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.commands.AddImageToLayerCommand;
import controller.commands.AddLayerCommand;
import controller.commands.ProjectCommand;
import controller.commands.SaveImageCommand;
import controller.commands.SaveProjectCommand;
import controller.commands.SetFilterCommand;
import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import utils.Util;
import view.CollageView;

/**
 * Represents a controller for the collage project.
 */
public class CommandController implements Controller {
  private final Scanner scanner;
  private final CollageView view;

  /**
   * A controller takes in a way to read commands and the view to render to the user.
   * @param input the method of reading commands.
   * @param view the view to display information to the user.
   * @throws IllegalArgumentException if the input or view are null.
   */
  public CommandController(Readable input, CollageView view) {
    Util.anyNull(new IllegalArgumentException("Input or view cannot be null"), input, view);
    this.scanner = new Scanner(input);
    this.view = view;
  }

  @Override
  public void executeProgram() {
    this.view.renderCommandOptions();
    ProjectCommand command = null;
    ProjectModel<Pixel> model = null;

    while (this.scanner.hasNext()) {
      String option = scanner.next();
      try {
        switch (option) {
          case "quit":
            return;
          case "new-project":
            model = new CollageProject(scanner.next(), scanner.nextInt(), scanner.nextInt());
            break;
          case "save-project":
            if (model == null) {
              this.view.renderMessage("No project was found! Try creating a project first.");
            } else {
              command = new SaveProjectCommand();
            }
            break;
          case "add-layer":
            if (model == null) {
              this.view.renderMessage("No project was found! Try creating a project first.");
            } else {
              String layerName = scanner.next();
              if (model.containsLayer(layerName)) {
                this.view.renderMessage(
                        String.format("Layer with name %s already exists!", layerName));
              } else {
                command = new AddLayerCommand(layerName);
              }
            }
            break;
          case "set-filter":
            if (model == null) {
              this.view.renderMessage("No project was found! Try creating a project first.");
            } else {
              String layerName = scanner.next();
              if (!model.containsLayer(layerName)) {
                this.view.renderMessage(String.format("Layer %s does not exist", layerName));
              } else {
                command = new SetFilterCommand(layerName, scanner.next());
              }
            }
            break;
          case "add-image-to-layer":
            if (model == null) {
              this.view.renderMessage("No project was found! Try creating a project first.");
            } else {
              String layerName = scanner.next();
              if (!model.containsLayer(layerName)) {
                this.view.renderMessage(String.format("Layer %s does not exist", layerName));
              } else {
                command = new AddImageToLayerCommand(layerName, scanner.next(),
                        scanner.nextInt(), scanner.nextInt());
              }
            }
            break;
          case "load-project":
            break;
          case "save-image":
            if (model == null) {
              this.view.renderMessage("No project was found! Try creating a project first.");
            } else {
              command = new SaveImageCommand(scanner.next());
            }
            break;
          case "view-filter-options":
            this.view.renderFilterOptions();
            break;
          default:
            this.view.renderMessage("Unknown command!");
            break;
        }
        if (command != null) {
          command.executeCommand(model);
        }
      } catch (InputMismatchException ignored) {
        this.view.renderMessage("Incorrect command format encountered.");
      }
    }
  }
}
