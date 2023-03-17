package model.projects;

import java.util.ArrayList;
import java.util.List;

import model.colors.RGBAColor;
import model.filters.Filter;
import model.filters.NormalFilter;
import model.images.ImageModel;
import model.layers.Layer;
import model.layers.LayerModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Represents a collage project.
 */
public class CollageProject implements ProjectModel<Pixel> {
  private final int canvasWidth;
  private final int canvasHeight;
  private final String projectName;
  private List<LayerModel<Pixel>> layers;

  /**
   * Represents the constructor for the collage project.
   * @param projectName the name of the project
   * @param canvasHeight the height of the project's canvas
   * @param canvasWidth the width of the project's canvas
   */
  public CollageProject(String projectName, int canvasHeight, int canvasWidth) {
    this.projectName = projectName;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
    LayerModel<Pixel> defaultLayer = new Layer("default-layer", new NormalFilter(),
            canvasWidth, canvasHeight);
    ImageModel<Pixel> image = defaultLayer.getImage();
    image.colorBackground(new RGBAColor(Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE,
            Util.MAX_PROJECT_VALUE, Util.MAX_PROJECT_VALUE));
    this.layers = new ArrayList<>(List.of(defaultLayer));
  }

  /**
   * Sets a filter on a given layer of the project.
   * @param layerName the name of the layer.
   * @param filter    the filter to give to the layer.
   * @throws IllegalArgumentException
   */
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

  /**
   *
   * @param layerName the name of the layer.
   * @param filePath  the path to the image.
   * @param row       the row coordinate of the starting position.
   * @param col       the column coordinate of the starting position.
   */
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
    this.layers.add(new Layer(layerName, new NormalFilter(), this.canvasWidth, this.canvasHeight));
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