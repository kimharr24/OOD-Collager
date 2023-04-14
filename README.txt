How To Use
To run this program, there are 3 options that a user can specify from their run configurations: 

1) -text (Runs the text-based command view of the collager project)
2) -file path-to-script (Runs a script of commands at path-to-script)
3) -gui (Runs the GUI version of the collager project)

Make sure to create a run configuration for the Main method inside of the controller package.
** The JAR must be run from inside of the res/ directory (i.e., the working directory should be res/)
** Make sure that res is on the same directory level as src/ and test/

View Decoupling Files Required
Interfaces
- ControllerFeatures: needed for giving the view controller methods as part of the callback design pattern.
- ColorModel: needed by Util (which is needed by the view) for converting an image from our representation to a buffered image.
- ColorState: needed because ColorModel extends ColorState.
- ImageModel: needed by the view because the view has to render the image to display to the user.
- ImageState: needed because ImageModel extends ImageState.
- Pixel: needed because many interfaces required by the view are parameterized by a Pixel interface.
- CollageGUIView: needed because it is the GUI view.
- CollageView: needed because it is the text view.

Classes
- Position2D: needed because the Pixel interface has a method that returns a Position2D
- Util: needed to store the max value of the project as well as some constants that the view needs.
- GUIView: needed because it is the implementation of CollageGUIView
- TextView: needed because it is the implementation of CollageView

Design Changes
- No design changes since Assignment #5.

Program Completion Status
- All required features in Assignment #6 have been implemented.

Image Citations
- emoji.ppm: https://www.clipartmax.com/middle/m2i8m2b1i8Z5b1b1_picture-300-x-300-pixel/
- cat.ppm: https://www.photos-public-domain.com/2018/05/01/siamese-tabby-cat/
