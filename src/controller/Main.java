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
    ControllerFeatures controller = new GUIController(new CollageProject(500,500),
            new GUIView());
  }
}
