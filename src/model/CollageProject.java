package model;

import java.util.ArrayList;
import java.util.List;

import model.filters.Filter;
import model.filters.NormalFilter;
import model.images.ImageModel;
import model.layers.Layer;
import utils.Util;

public class CollageProject {
  private final int canvasWidth;
  private final int canvasHeight;
  private String projectName;
  private List<Layer> layers;

  public CollageProject(String projectName, int canvasHeight, int canvasWidth) {
    this.projectName = projectName;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
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

  public void addImageToLayer(String layerName, String filePath, int row, int col) {
    for (Layer layer : this.layers) {
      if(layer.getLayerName().equals(layerName)) {
        ImageModel<ImagePixel> existingImage = layer.getImage();
        existingImage.overlayImage(filePath, row, col);
      }
    }
  }

  public void saveCompiledImage() {
    // TODO
  }

  public void addLayer(String layerName) {
    this.layers.add(new Layer(layerName, new NormalFilter()));

  }

  public int getMaxValue() {
    return Util.MAX_PROJECT_VALUE;
  }
}