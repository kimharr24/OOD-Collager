package controller;

import model.projects.CollageProject;
import view.GUIView;
import view.TextView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Main class for executing the collage project. The executable has three options:
 * 1) -file path-to-file -> executes a script located at path-to-file
 * 2) -text -> allows the user to manually type in text-based commands
 * 3) [no option] -> opens a GUI for the user to interact with
 */
public class Main {
  /**
   * Main method of entry into the collage project modes.
   * @param args the arguments provided by someone calling this executable.
   */
  public static void main(String[] args) {
    switch (args[0]) {
      case "-file":
        Readable reader = null;
        try {
          reader = new FileReader(args[1]);
          TextController controller = new CommandController(reader, new TextView());
          controller.executeProgram();
        } catch (FileNotFoundException e) {
          System.out.printf("Filepath %s not found%n", args[1]);
        }
        break;
      case "-text": {
        TextController controller = new CommandController(new InputStreamReader(System.in),
                new TextView());
        controller.executeProgram();
        break;
      }
      case "": {
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
