import org.junit.Before;
import org.junit.Test;

import model.Position2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Testing class for ensuring that all methods in Position2D
 * behave as expected.
 */
public class Position2DTest {
  Position2D position1;
  Position2D position2;

  @Before
  public void init() {
    this.position1 = new Position2D(3, 5);
    this.position2 = new Position2D(12, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeRow() {
    Position2D position = new Position2D(-4, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeColumn() {
    Position2D position = new Position2D(4, -5);
  }

  @Test
  public void testValidConstructor() {
    try {
      Position2D position = new Position2D(1, 2);
    } catch(IllegalArgumentException e) {
      fail("Expected constructor to be valid.");
    }
  }

  @Test
  public void testGetRow() {
    assertEquals(3, this.position1.getRow());
    assertEquals(12, this.position2.getRow());
  }

  @Test
  public void testGetCol() {
    assertEquals(5, this.position1.getCol());
    assertEquals(3, this.position2.getCol());
  }
}
