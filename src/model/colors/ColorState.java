package model.colors;

public interface ColorState {
  int getRedComponent();
  int getGreenComponent();
  int getBlueComponent();
  int getAlphaComponent();
  int getMaxValue();

  int getNumberOfBits();
}
