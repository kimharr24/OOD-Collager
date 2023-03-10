package model.colors;

public interface Color {
  /**
   * Given an arbitrary color representation (rgba, hexadecimal, etc.), updates the current color
   * based on the background color
   * @param color
   */
  void mergeColors(Color color);
}
