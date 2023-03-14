package model;

import java.util.List;

import model.filters.Filter;

public class Layer {
  private String layerName;
  private Filter filter;
  private List<List<ImagePixel>> image;

  public Layer(String layerName, Filter filter, List<List<ImagePixel>> image) {
    this.layerName = layerName;
    this.filter = filter;
    this.image = image;
    this.applyFilter();
  }

  private void applyFilter() {
    for (List<ImagePixel> imagePixels : this.image) {
      for (int col = 0; col < this.image.get(0).size(); col++) {
        ImagePixel currentPixel = imagePixels.get(col);
        currentPixel.applyFilter(this.filter);
      }
    }
  }

  public String getLayerName() {
    return this.layerName;
  }

  public String getFilterName() {
    return this.filter.getName();
  }

  public void setLayerName(String name) {
    this.layerName = name;
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
    this.applyFilter();
  }

  public void setImage(int x, int y, List<List<ImagePixel>> image) {

  }
}
