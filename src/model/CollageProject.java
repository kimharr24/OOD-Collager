package model;

import java.util.ArrayList;
import java.util.List;

import model.filters.Filter;
import model.images.ImageModel;
import model.layers.Layer;

public class CollageProject {
  private final int canvasWidth;
  private final int canvasHeight;
  private String projectName;
  private List<Layer> layers;
  private final int numberOfBits;

  public CollageProject(String projectName, int canvasWidth, int canvasHeight, int numberOfBits) {
    this.projectName = projectName;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    this.numberOfBits = numberOfBits;
//    this.layers = new ArrayList<>(List.of(new Layer.LayerUtils().getDefaultWhiteOpaqueLayer(
//            canvasWidth, canvasHeight, numberOfBits)));
  }

  public void setLayerFilter(String layerName, Filter filter) {
    for (Layer layer : this.layers) {
      if (layer.getLayerName().equals(layerName)) {
        layer.setFilter(filter);
      }
    }
  }

  public void saveProject() {
    // TODO
  }

  public void addImageToLayer(String layerName, ImageModel<ImagePixel> image, int row, int col) {
    for (Layer layer : this.layers) {
      if(layer.getLayerName().equals(layerName)) {
        ImageModel<ImagePixel> existingImage = layer.getImage();
        existingImage.overlayImage(image, row, col);
      }
    }
  }

  public void saveCompiledImage() {
    // TODO
  }
}