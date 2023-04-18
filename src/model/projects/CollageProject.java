package model.projects;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import model.colors.ColorModel;
import model.colors.RGBAColor;
import model.filters.BlueComponentFilter;
import model.filters.BrightenIntensityFilter;
import model.filters.BrightenLumaFilter;
import model.filters.BrightenValueFilter;
import model.filters.BrighteningBlendingFilter;
import model.filters.DarkenIntensityFilter;
import model.filters.DarkenLumaFilter;
import model.filters.DarkenValueFilter;
import model.filters.DarkeningBlendingFilter;
import model.filters.Filter;
import model.filters.GreenComponentFilter;
import model.filters.InversionBlendingFilter;
import model.filters.NormalFilter;
import model.filters.RedComponentFilter;
import model.images.Image;
import model.images.ImageModel;
import model.layers.Layer;
import model.layers.LayerModel;
import model.pixels.ImagePixel;
import model.pixels.Pixel;
import utils.Position2D;
import utils.Util;

/**
 * Represents a collage project for the image processing application.
 * It is assumed that multiple filters cannot be applied to the same layer. Furthermore, when a
 * project is created, by default, it creates a layer called "default-layer" with a fully opaque
 * white background. When new layers are added to the project, the layers have a fully
 * transparent white background by default.
 * Class Invariant (1): All layers in the project contain images with the same dimensions.
 * Class Invariant (2): All layers store original, unfiltered images at all times.
 */
public class CollageProject implements ProjectModel<Pixel> {
  private final int canvasWidth;
  private final int canvasHeight;
  private final List<LayerModel<Pixel>> layers;

  /**
   * Represents the constructor for the collage project.
   *
   * @param canvasHeight the height of the project's canvas
   * @param canvasWidth  the width of the project's canvas
   * @throws IllegalArgumentException if the canvas width or height are less than or equal to 0.
   */
  public CollageProject(int canvasHeight, int canvasWidth)
          throws IllegalArgumentException {
    if (canvasHeight <= 0 || canvasWidth <= 0) {
      throw new IllegalArgumentException("Canvas width and height must be at least 1 pixel");
    }
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
   *
   * @param layerName the name of the layer.
   * @param filter    the filter to give to the layer.
   * @throws IllegalArgumentException if the filter is null or the layer does not exist.
   */
  @Override
  public void setLayerFilter(String layerName, Filter<Pixel> filter)
          throws IllegalArgumentException {
    Util.anyNull(new IllegalArgumentException("Filter is null"), filter);
    if (!this.containsLayer(layerName)) {
      throw new IllegalArgumentException(String.format("Layer %s does not exist!", layerName));
    }
    for (LayerModel<Pixel> layer : this.layers) {
      if (layer.getLayerName().equals(layerName)) {
        layer.setFilter(filter);
      }
    }
  }

  @Override
  public void addImageToLayer(String layerName, ImageModel<Pixel> image, int row, int col) {
    Util.anyNull(new IllegalArgumentException("Image is null."), image);
    if (!this.containsLayer(layerName)) {
      throw new IllegalArgumentException(String.format("Layer %s does not exist!", layerName));
    }
    for (LayerModel<Pixel> layer : this.layers) {
      if (layer.getLayerName().equals(layerName)) {
        ImageModel<Pixel> existingImage = layer.getImage();
        existingImage.overlayImage(image, row, col);
      }
    }
  }

  /**
   * Adds a new layer to the top of the collage project. By default, this layer has a fully
   * transparent white image with the normal filter.
   *
   * @param layerName the name of the new layer.
   * @throws IllegalArgumentException if the project already contains the given layer name.
   */
  @Override
  public void addLayer(String layerName) throws IllegalArgumentException {
    if (this.containsLayer(layerName)) {
      throw new IllegalArgumentException(String.format("Layer %s already exists!", layerName));
    }
    this.layers.add(new Layer(layerName, new NormalFilter(), this.canvasWidth, this.canvasHeight));
  }

  @Override
  public void deleteLayer(String layerName) throws IllegalArgumentException {
    if (!this.containsLayer(layerName)) {
      throw new IllegalArgumentException("Layer does not exist");
    }
    this.layers.removeIf(layer -> layer.getLayerName().equals(layerName));
  }

  @Override
  public ProjectModel<Pixel> createProject(int width, int height) throws IllegalArgumentException {
    return new CollageProject(height, width);
  }

  @Override
  public ImageModel<Pixel> createDefaultImage(int width, int height) throws IllegalArgumentException {
    return new Image(width, height);
  }

  @Override
  public Pixel createPixel(Position2D position, ColorModel color) throws IllegalArgumentException {
    return new ImagePixel(position, color);
  }

  @Override
  public Filter<Pixel> getFilterFromName(String name) throws IllegalArgumentException {
    Filter<Pixel> filter = null;

    try {
      switch (name) {
        case "normal":
          filter = new NormalFilter();
          break;
        case "red-component":
          filter = new RedComponentFilter();
          break;
        case "blue-component":
          filter = new BlueComponentFilter();
          break;
        case "green-component":
          filter = new GreenComponentFilter();
          break;
        case "brighten-value":
          filter = new BrightenValueFilter();
          break;
        case "darken-value":
          filter = new DarkenValueFilter();
          break;
        case "brighten-intensity":
          filter = new BrightenIntensityFilter();
          break;
        case "darken-intensity":
          filter = new DarkenIntensityFilter();
          break;
        case "brighten-luma":
          filter = new BrightenLumaFilter();
          break;
        case "darken-luma":
          filter = new DarkenLumaFilter();
          break;
        case "inversion-blending":
          filter = new InversionBlendingFilter();
          break;
        case "darkening-blending":
          filter = new DarkeningBlendingFilter();
          break;
        case "brightening-blending":
          filter = new BrighteningBlendingFilter();
          break;
      }
    } catch (InputMismatchException ignored) {
      throw new IllegalArgumentException("Unknown filter");
    }
    return filter;
  }

  @Override
  public int getMaxValue() {
    return Util.MAX_PROJECT_VALUE;
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

  @Override
  public ImageModel<Pixel> getCompositeImage() {
    LayerModel<Pixel> bottomLayer = this.layers.get(0);
    ImageModel<Pixel> dummyCompositeImage = bottomLayer.getImage();

    // Apply the filter on the bottom layer to the bottom layer
    bottomLayer.applyFilter(dummyCompositeImage);

    ImageModel<Pixel> resultingImage = new Image(this.canvasWidth, this.canvasHeight);
    resultingImage = resultingImage.collapseImage(bottomLayer.getImage());

    for (LayerModel<Pixel> layer : this.layers.subList(1, this.layers.size())) {
      // Apply the filter to the current layer
      layer.applyFilter(resultingImage);
      // Update the collapsed image by passing in the filtered layer image
      resultingImage = resultingImage.collapseImage(layer.getImage());
      // Temporarily keep track of the original layer filter
      Filter<Pixel> originalFilter = layer.getFilter();
      // Update the layer to use the normal filter
      layer.setFilter(new NormalFilter());
      // Apply the normal filter (passing in the composite image does not do anything useful)
      layer.applyFilter(resultingImage);
      // Reset the layer back to the original filter
      layer.setFilter(originalFilter);
    }

    // Reset the bottom layer image to its original image
    Filter<Pixel> originalBottomLayerFilter = bottomLayer.getFilter();
    bottomLayer.setFilter(new NormalFilter());
    bottomLayer.applyFilter(dummyCompositeImage);
    bottomLayer.setFilter(originalBottomLayerFilter);

    return resultingImage;
  }
}