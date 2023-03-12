package model.colors;

/**
 * Represents an RGBA (red, blue, green, alpha) color. Color formats that do not
 * have alpha channels can adhere to the RGBA color by using a default value of alpha or
 * inferring the value of alpha.
 */
public class RGBAColor implements ColorModel {
  private int red;
  private int green;
  private int blue;
  private int alpha;
  private int bits;

  /**
   * Validates the inputs to the RGBA constructor.
   *
   * @param red   the red component of the color.
   * @param green the green component of the color.
   * @param blue  the blue component of the color.
   * @param alpha the alpha component of the color.
   * @param bits  the number of bits used to represent this color.
   * @throws IllegalArgumentException if red, green, blue, or alpha are less than 0, the number
   *                                  of bits is less than or equal to 0, or RGBA uses an invalid
   *                                  integer given the number of bits.
   */
  private void validateConstructor(int red, int green, int blue, int alpha, int bits) {
    if (red < 0 || green < 0 || blue < 0 || alpha < 0) {
      throw new IllegalArgumentException("Negative values of RGBA not allowed.");
    }
    if (bits <= 0) {
      throw new IllegalArgumentException("Must use at least 1 bit to represent a color.");
    }
    int maxValue = (1 << bits) - 1;
    if (red > maxValue || green > maxValue || blue > maxValue || alpha > maxValue) {
      throw new IllegalArgumentException(String.format("RGBA cannot have a value greater than %d",
              maxValue));
    }
  }

  /**
   * Constructor for an RGBA color. The number of bits used to represent a color
   * can be specified by a client.
   *
   * @param red   the red component of the color.
   * @param green the green component of the color.
   * @param blue  the blue component of the color.
   * @param alpha the alpha component of the color.
   * @param bits  the number of bits used to represent this color.
   * @throws IllegalArgumentException if red, green, blue, or alpha are less than 0, the number
   *                                  of bits is less than or equal to 0, or RGBA uses an
   *                                  invalid integer given the number of bits.
   */
  public RGBAColor(int red, int green, int blue, int alpha, int bits)
          throws IllegalArgumentException {
    this.validateConstructor(red, green, blue, alpha, bits);
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.bits = bits;
  }

  @Override
  public int getRedComponent() {
    return this.red;
  }

  @Override
  public int getGreenComponent() {
    return this.green;
  }

  @Override
  public int getBlueComponent() {
    return this.blue;
  }

  @Override
  public int getAlphaComponent() {
    return this.alpha;
  }

  @Override
  public int getMaxValue() {
    return (1 << this.bits) - 1;
  }

  @Override
  public ColorModel createCopy() {
    return new RGBAColor(this.red, this.green, this.blue, this.alpha, this.bits);
  }

  @Override
  public ColorModel filterRedChannel() {
    return new RGBAColor(this.red, 0, 0, this.alpha, this.bits);
  }
}
