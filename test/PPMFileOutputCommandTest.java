//import org.junit.Test;
//
//import model.images.Image;
//import model.images.ImageModel;
//import model.images.fileinputcommands.ImageFileInputCommand;
//import model.images.fileinputcommands.PPMInputCommand;
//import model.images.fileoutputcommands.FileOutputCommand;
//import model.images.fileoutputcommands.PPMFileOutputCommand;
//import model.pixels.Pixel;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Testing class for saving images to PPM files with the command object.
// */
//public class PPMFileOutputCommandTest {
//  @Test(expected = IllegalArgumentException.class)
//  public void testSaveCollageImageNullImage() {
//    FileOutputCommand<Pixel> outputCommand = new PPMFileOutputCommand();
//    outputCommand.saveCollageImage(null, "./test/images/extracted-cat.ppm");
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSaveCollageImageNotPPMFile() {
//    FileOutputCommand<Pixel> outputCommand = new PPMFileOutputCommand();
//    outputCommand.saveCollageImage(new Image(100, 100), "./test/images/extracted-cat.png");
//  }
//
//  @Test
//  public void testSaveCollageImage() {
//    ImageFileInputCommand<Pixel> inputCommand = new PPMInputCommand("./test/images/cat.ppm");
//    ImageModel<Pixel> extractedImage = inputCommand.extractImage("./test/images/cat.ppm");
//    FileOutputCommand<Pixel> outputCommand = new PPMFileOutputCommand();
//    outputCommand.saveCollageImage(extractedImage, "./test/images/extracted-cat.ppm");
//    inputCommand = new PPMInputCommand("./test/images/extracted-cat.ppm");
//    ImageModel<Pixel> extractedTestImage = inputCommand.extractImage("./test/images/cat.ppm");
//
//    for (int i = 0; i < extractedImage.getImageHeight(); i++) {
//      for (int j = 0; j < extractedImage.getImageWidth(); j++) {
//        assertEquals(extractedImage.getPixelAtCoord(i, j).getColor(),
//                extractedTestImage.getPixelAtCoord(i, j).getColor());
//      }
//    }
//  }
//}
