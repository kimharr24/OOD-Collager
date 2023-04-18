package controller;

import model.pixels.Pixel;
import model.projects.CollageProject;
import model.projects.ProjectModel;
import view.GUIView;
import view.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Main class for executing the collage project. The executable has three options:
 * 1) -file path-to-file -> executes a script located at path-to-file
 * 2) -text -> allows the user to manually type in text-based commands
 * 3) -gui -> opens a GUI for the user to interact with
 */
public class Main {
  /**
   * Main method of entry into the collage project modes.
   * @param args the arguments provided by someone calling this executable.
   */
  public static void main(String[] args) {
    ProjectModel<Pixel> defaultModel = new CollageProject(300, 300);
    switch (args[0]) {
      case "-file":
        Readable reader = null;
        try {
          reader = new FileReader(args[1]);
          TextController controller = new CommandController(reader, new TextView(), defaultModel);
          controller.executeProgram();
        } catch (FileNotFoundException e) {
          System.out.printf("Filepath %s not found%n", args[1]);
        }
        break;
      case "-text": {
        TextController controller = new CommandController(new InputStreamReader(System.in),
                new TextView(), defaultModel);
        controller.executeProgram();
        break;
      }
      case "-gui": {
        ControllerFeatures controller = new GUIController(new CollageProject(500, 500),
                new GUIView());
        break;
      }
      default:
        System.out.println("Unknown command. Closing down the program.");
        break;
    }
  }
}
