package controller.commands;

import java.io.FileWriter;
import java.io.IOException;

import model.projects.CollageProject;
import model.colors.ColorModel;
import model.images.ImageModel;
import model.layers.LayerModel;
import model.pixels.Pixel;

/**
 * Represents a command that can be executed to save a collage project according to
 * the formatting specifications of a .collage file.
 */
public class SaveProjectCommand implements ProjectCommand {
  @Override
  public void executeCommand(CollageProject project) {
    FileWriter writer = null;

    try {
      writer = new FileWriter(project.getName() + ".txt");
      writer.write("C1\n");
      writer.write(String.format("%d %d\n", project.getCanvasWidth(), project.getCanvasHeight()));
      writer.write(project.getMaxValue() + "\n");

      for (int layerIndex = 0; layerIndex < project.getLayerCount(); layerIndex++) {
        LayerModel<Pixel> currentLayer = project.getLayerAtPosition(layerIndex);
        writer.write(String.format("%s %s\n", currentLayer.getLayerName(),
                currentLayer.getFilterName()));
        ImageModel<Pixel> image = currentLayer.getImage();
        for (int i = 0; i < image.getImageHeight(); i++) {
          for (int j = 0; j < image.getImageWidth(); j++) {
            Pixel currentPixel = image.getPixelAtCoord(i, j);
            ColorModel currentColor = currentPixel.getColor();
            writer.write(String.format("%d %d %d %d\n", currentColor.getRedComponent(),
                    currentColor.getGreenComponent(), currentColor.getBlueComponent(),
                    currentColor.getAlphaComponent()));
          }
        }
      }
      writer.close();
    } catch (IOException ignored) {
    }
  }
}
