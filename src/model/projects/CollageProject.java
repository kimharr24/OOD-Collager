package model.projects;

import java.util.List;

import model.filters.Filter;
import model.filters.NormalFilter;
import model.images.ImageModel;
import model.layers.Layer;
import model.layers.LayerModel;
import model.pixels.Pixel;
import utils.Util;

public class CollageProject implements ProjectModel<Pixel> {
  private final int canvasWidth;
  private final int canvasHeight;
  private final String projectName;
  private List<LayerModel<Pixel>> layers;

  public CollageProject(String projectName, int canvasHeight, int canvasWidth) {
    this.projectName = projectName;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
  }

  @Override
  public void setLayerFilter(String layerName, Filter filter) throws IllegalArgumentException {
    for (LayerModel<Pixel> layer : this.layers) {
      if (layer.getLayerName().equals(layerName)) {
        layer.setFilter(filter);
      }
    }
  }

  public void saveProject() {
    // TODO
  }

  @Override
  public void addImageToLayer(String layerName, String filePath, int row, int col) {
    for (LayerModel<Pixel> layer : this.layers) {
      if(layer.getLayerName().equals(layerName)) {
        ImageModel<Pixel> existingImage = layer.getImage();
        existingImage.overlayImage(filePath, row, col);
      }
    }
  }

  public void saveCompiledImage() {
    // TODO
  }

  @Override
  public void addLayer(String layerName) {
    this.layers.add(new Layer(layerName, new NormalFilter()));
  }

  @Override
  public int getMaxValue() {
    return Util.MAX_PROJECT_VALUE;
  }

  @Override
  public String getName() {
    return this.projectName;
  }

  @Override
  public int getCanvasWidth() {
    return this.canvasWidth;
  }

  @Override
  public int getCanvasHeight() {
    return this.canvasHeight;
  }

  @Override
  public int getLayerCount() {
    return this.layers.size();
  }

  @Override
  public LayerModel<Pixel> getLayerAtPosition(int position) throws IllegalArgumentException {
    if (position < 0) {
      throw new IllegalArgumentException("Layer positions do not support negative indices.");
    }
    if (position >= this.getLayerCount()) {
      throw new IllegalArgumentException(String.format("Layer position %d is out of bounds for " +
              "this project's layer stack of size %d", position, this.getLayerCount()));
    }
    return this.layers.get(position);
  }

  @Override
  public boolean containsLayer(String layerName) {
    for (LayerModel<Pixel> layer : this.layers) {
      if (layer.getLayerName().equals(layerName)) {
        return true;
      }
    }
    return false;
  }
}