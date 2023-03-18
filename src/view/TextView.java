package view;

/**
 * Simple text-based view for a collage project.
 */
public class TextView implements CollageView {
  @Override
  public void renderMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void renderCommandOptions() {
    System.out.println("Welcome to the collage project! Available commands are listed:");
    System.out.println("new-project project-name canvas-height canvas-width");
    System.out.println("load-project path-to-project-file");
    System.out.println("save-project");
    System.out.println("add-layer layer-name");
    System.out.println("add-image-to-layer layer-name image-name x-pos y-pos");
    System.out.println("set-filter layer-name filter-option");
    System.out.println("save-image file-name");
    System.out.println("view-filter-options");
    System.out.println("quit");
  }

  @Override
  public void renderFilterOptions() {
    System.out.println("normal");
    System.out.println("darken-intensity");
    System.out.println("red-component");
    System.out.println("green-component");
    System.out.println("blue-component");
    System.out.println("brighten-value");
    System.out.println("darken-value");
    System.out.println("brighten-intensity");
    System.out.println("darken-intensity");
    System.out.println("brighten-luma");
    System.out.println("darken-luma");
  }
}
