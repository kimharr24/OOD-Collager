package controller;

import view.TextView;

import java.io.InputStreamReader;

/**
 * Main method of executing the collage project.
 */
public class Main {
  public static void main(String[] args) {
    Controller controller = new CommandController(new InputStreamReader(System.in), new TextView());
    controller.executeProgram();
  }
}
