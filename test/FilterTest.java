//import org.junit.Test;
//
//import model.colors.ColorModel;
//import model.colors.RGBAColor;
//import model.filters.BlueComponentFilter;
//import model.filters.BrightenIntensityFilter;
//import model.filters.BrightenLumaFilter;
//import model.filters.BrightenValueFilter;
//import model.filters.DarkenIntensityFilter;
//import model.filters.DarkenLumaFilter;
//import model.filters.DarkenValueFilter;
//import model.filters.GreenComponentFilter;
//import model.filters.NormalFilter;
//import model.filters.RedComponentFilter;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Represents a class to test filter application.
// */
//public class FilterTest {
//
//  @Test
//  public void testNormalFilter() {
//    NormalFilter filter = new NormalFilter();
//
//    ColorModel color = new RGBAColor(255, 255, 255, 255);
//    ColorModel result = filter.apply(color);
//    assertEquals(color, result);
//  }
//
//  @Test
//  public void testApplyRedComponentFilter() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(100, 0, 0, 255);
//
//    RedComponentFilter filter = new RedComponentFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyBlueComponentFilter() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(0, 0, 30, 255);
//
//    BlueComponentFilter filter = new BlueComponentFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyGreenComponentFilter() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(0, 50, 0, 255);
//
//    GreenComponentFilter filter = new GreenComponentFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyBrightenIntensity() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(160, 110, 90, 255);
//
//    BrightenIntensityFilter filter = new BrightenIntensityFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyDarkenIntensity() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(40, 0, 0, 255);
//
//    DarkenIntensityFilter filter = new DarkenIntensityFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyBrightenLuma() {
//    ColorModel originalColor = new RGBAColor(100, 100, 100, 255);
//    ColorModel expectedColor = new RGBAColor(200, 200, 200, 255); // 60.63
//
//    BrightenLumaFilter filter = new BrightenLumaFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyDarkenLuma() {
//    ColorModel originalColor = new RGBAColor(100, 100, 100, 255);
//    ColorModel expectedColor = new RGBAColor(0, 0, 0, 255);
//
//    DarkenLumaFilter filter = new DarkenLumaFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyBrightenValue() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(200, 150, 130, 255);
//
//    BrightenValueFilter filter = new BrightenValueFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//  @Test
//  public void testApplyDarkenValue() {
//    ColorModel originalColor = new RGBAColor(100, 50, 30, 255);
//    ColorModel expectedColor = new RGBAColor(70, 20, 0, 255);
//
//    DarkenValueFilter filter = new DarkenValueFilter();
//    ColorModel filteredColor = filter.apply(originalColor);
//
//    assertEquals(expectedColor, filteredColor);
//  }
//
//}
