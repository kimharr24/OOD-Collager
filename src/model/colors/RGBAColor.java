package model.colors;

public class RGBAColor implements ColorModel {
  private int red;
  private int green;
  private int blue;
  private int alpha;
  private int bits;

  public RGBAColor(int red, int green, int blue, int alpha, int bits) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.bits = bits;
  }

  @Override
  public int getRedComponent() {
    return 0;
  }

  @Override
  public int getGreenComponent() {
    return 0;
  }

  @Override
  public int getBlueComponent() {
    return 0;
  }

  @Override
  public int getAlphaComponent() {
    return 0;
  }

  @Override
  public int getMaxValue() {
    return 0;
  }

  @Override
  public int getNumberOfBits() {
    return this.bits;
  }
}
