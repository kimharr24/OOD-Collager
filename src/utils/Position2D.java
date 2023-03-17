package utils;

/**
 * Represents a 2-dimensional position on a grid
 * of size m x n where m represents the number of rows and n
 * represents the number of columns.
 */
public final class Position2D {
  private final int row;
  private final int col;

  /**
   * Default constructor for the position class.
   * @param row the value of the row.
   * @param col the value of the column
   * @throws IllegalArgumentException if the row or column is negative.
   */
  public Position2D(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Row or column position cannot be negative.");
    }
    this.row = row;
    this.col = col;
  }

  /**
   * Observer method for returning the row component of the position.
   * @return the row number.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Observer method for returning the column component of the position.
   * @return the column number.
   */
  public int getCol() {
    return this.col;
  }
}
