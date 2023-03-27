package controller;

import model.projects.CollageProject;
import view.GUIView;
import view.TextView;

import java.io.InputStreamReader;

/**
 * Main method of executing the collage project.
 */
public class Main {
  public static void main(String[] args) {
//    Controller controller = new CommandController(new InputStreamReader(System.in), new TextView());
//    controller.executeProgram();
    ControllerFeatures controller = new GUIController(new CollageProject(500, 500),
            new GUIView());
  }
}
