package controller.commands;

import java.io.FileWriter;
import java.io.IOException;

import model.colors.ColorModel;
import model.filters.NormalFilter;
import model.images.ImageModel;
import model.layers.LayerModel;
import model.pixels.Pixel;
import model.projects.ProjectModel;

/**
 * Represents a command that can be executed to save a collage project according to
 * the formatting specifications of a .collage file.
 */
public class SaveProjectCommand extends AbstractProjectCommand {
  @Override
  public void executeCommand(ProjectModel<Pixel> project) {
    this.checkNullProject(project);
    FileWriter writer = null;

    try {
      writer = new FileWriter(project.getName() + ".collage");
      writer.write("C1\n");
      writer.write(String.format("%d %d\n", project.getCanvasWidth(), project.getCanvasHeight()));
      writer.write(project.getMaxValue() + "\n");

      for (int layerIndex = 0; layerIndex < project.getLayerCount(); layerIndex++) {
        LayerModel<Pixel> currentLayer = project.getLayerAtPosition(layerIndex);
        writer.write(String.format("%s %s\n", currentLayer.getLayerName(),
                currentLayer.getFilterName()));
        ImageModel<Pixel> image = currentLayer.getImage();
        image.applyFilter(new NormalFilter());
        for (int i = 0; i < image.getImageHeight(); i++) {
          for (int j = 0; j < image.getImageWidth(); j++) {
            Pixel currentPixel = image.getPixelAtCoord(i, j);
            ColorModel currentColor = currentPixel.getColor();
            writer.write(String.format("%d %d %d %d\n", (int) currentColor.getRedComponent(),
                    (int) currentColor.getGreenComponent(), (int) currentColor.getBlueComponent(),
                    (int) currentColor.getAlphaComponent()));
          }
        }
        image.applyFilter(currentLayer.getFilter());
      }
      writer.close();
    } catch (IOException ignored) {
      System.out.println("Unable to save project.");
    }
  }
}
