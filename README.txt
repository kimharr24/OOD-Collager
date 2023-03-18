**Did not get to implementing load-project command.

Running Example Script
- Run the main method in controller.
- Once running, type each line of the example script in Commands.txt to get a sample result.

Running the Program in General
- To create a new project, type new-project project-name canvas-height canvas-width
- To add a layer, type add-layer layer-name
- To add an image to a layer, type add-image-to-layer layer-name image-path x-pos y-pos

NB: Make sure to choose the initial position of the top left corner of the image such that it does not 
spill off of the canvas. We made the decision decision to not allow "image spillage." For the cat image,
the canvas should be larger than 300 x 300 to have an offset that is not (0, 0).

- To apply a filter, type set-filter layer-name filter-option
- To save the iamge type save-image path-to-save
- For all supported commands see the Commands.txt file

MODEL
The ColorModel and ColorState interfaces are segregated between methods that modify a color and
methods that can observe the state of a color. Our concrete implementation, RGBAColor is the data representation
that will be used in our collage project. Whenever an image is read into the program, regardless of its format, 
it can be converted into an RGBAColor. 

The Filter interface was designed so that future filters can easily be added to the project, simply by creating
another concrete filter implementation. Each filter delegates the task of applying the filter to the ColorModel.
Rather than computing the updated color inside of the filter, we decided to use this delegation in order
to maintain separation of concerns. In other words, the only job of the filter is to determine which
method of the ColorModel interface to call. This has the downside of additional methods being needed to be 
added into the ColorModel interface if more filters are needed. Common methods and fields were abstracted
into an AbstractFilter class.

The ImageModel and ImageState interfaces were created to allow the project to handle arbitrary data representations
of images in the future. The Image class is a concrete implementation that uses a 2-dimensional representation
of an image. We specifically designed the Image class to fully hide away its internal representation of the
image from anyone outside of the class so that changes to the image's representation will not affect
other classes. To this end, we created methods such as getPixelAtCoordinate() and setPixelAtCoordinate() inside
of the Image class to prevent a client from ever receiving a List<List<Pixel>>. 

We also created a FileInputCommand interface to add support for reading in image files. This interface was 
designed so that any future images (even if they are not .ppm files) can be read into our program and 
converted to our ImageModel representation. The PPMInputCommand is specifically responsible for reading in .ppm
files. Common code is extracted into AbstractFileInputCommand.

Similarly, the FileOutputCommand interface was designed to add support for saving images in any format. The
PPMFileOutputCommand class is responsible for saving files to .ppm. If we need to save images to .png, for instance,
we can simply add another concrete FileOutputCommand.

The LayerModel and LayerState interfaces were designed to represent generic layers that can be present in a
collage project. The Layer class is a specific version of the layer used in the collage project.

Similarly, the Pixel interface represents a generic pixel on a computer screen. The ImagePixel class is a 
concrete implementation in our project. The same logic follows for ProjectModel, ProjectState, and CollageProject.

We made the design decision to not allow the user to change the maximum value in a project. The only way to do so
is to change the public static integer variable inside of the Util class, which holds common utility methods.
We decided to implement this in this fashion because having static access to the maximum value across all
classes was the cleanest solution. Position2D is a similar utility class for storing positions.

CONTROLLER
The ProjectCommand interface was created to follow the command design pattern to allow for easy addition of future
commands that the controller can support. Each of the classes inside of the commands package supports a specific
command that the controller can then execute.

The Controller interface represents a generic controller which should be able to execute the collage project. 
Our CommandController class is a concrete implementation of the controller.

VIEW
The CollageView interface represents a generic set of methods that a view should support for our collage project.
The TextView class is a simple text UI view that implements these methods. In the future, we envision that
we can create another class that implements the interface with a GUI.

Image Citations
- emoji.ppm: https://www.clipartmax.com/middle/m2i8m2b1i8Z5b1b1_picture-300-x-300-pixel/
- cat.ppm: https://www.photos-public-domain.com/2018/05/01/siamese-tabby-cat/
- extracted-cat.ppm: generated by a test
- rainbow-cat.ppm: generated by a test
- test-save-command.ppm: generated by a test