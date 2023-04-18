This is a photo editing software that allows you to create layers in a project, add images to those layers, and save your edited images and projects. You can also set filters to specified layers, which will change the appearance of the image on that layer. These layers stack on top of each other, and you are able to see the composite image created by all of these layers.

Features:
- creating a new project
- loading in a project
- saving a project
- saving a composite image (supported formats: PPM, PNG, JPEG)
- adding layers to a project
- adding images to the layers (supported formats: PPM, PNG, JPEG)
- adding filters to the layers

How To Use
To run this program, there are 3 options that a user can specify from their run configurations: 

1) -text (Runs the text-based command view of the collager project)
2) -file path-to-script (Runs a script of commands at path-to-script)
3) -gui (Runs the GUI version of the collager project)

** Make sure that res is on the same directory level as src/ and test/

This program requires the user to have Java 11 or higher and JUnit4.

The USEME file can be found in the root directory of this project, labeled USEME.txt.

Design of our code:

MODEL:
The ColorModel and ColorState interfaces are segregated between methods that modify a color and
methods that can observe the state of a color. Our concrete implementation, RGBAColor is the data representation
that will be used in our collage project. Whenever an image is read into the program, regardless of its format, 
it can be converted into an RGBAColor. Similar logic for HSLAColor.

The Filter interface was designed so that future filters can easily be added to the project, simply by creating
another concrete filter implementation. Common methods and fields were abstracted into an AbstractFilter class.

The ImageModel and ImageState interfaces were created to allow the project to handle arbitrary data representations
of images in the future. The Image class is a concrete implementation that uses a 2-dimensional representation
of an image. We specifically designed the Image class to fully hide away its internal representation of the
image from anyone outside of the class so that changes to the image's representation will not affect
other classes. To this end, we created methods such as getPixelAtCoordinate() and setPixelAtCoordinate() inside
of the Image class to prevent a client from ever receiving a List<List<Pixel>>. 

The LayerModel and LayerState interfaces were designed to represent generic layers that can be present in a
collage project. The Layer class is a specific version of the layer used in the collage project.

Similarly, the Pixel interface represents a generic pixel on a computer screen. The ImagePixel class is a 
concrete implementation in our project. The same logic follows for ProjectModel, ProjectState, and CollageProject.

For CollageProject, we added some new methods called createDefaultImage(), createPixel(), and 
getFilterFromName(). These methods were created to hide away the concrete implementation of ImageModel and
Pixel from the controller. Similarly, we hid away the concrete implementations of filters from the controller
with the getFilterFromName() method.

We made the design decision to not allow the user to change the maximum value in a project. The only way to do so
is to change the public static integer variable inside of the Util class, which holds common utility methods.
We decided to implement this in this fashion because having static access to the maximum value across all
classes was the cleanest solution. Position2D is a similar utility class for storing positions.

CONTROLLER:
The ProjectCommand interface was designed to follow the command design pattern. 
We also created a FileInputCommand interface to add support for reading in general files. The ImageFileInputCommand
inteface is more specifically for reading in images.
This interface was designed so that any future images (even if they are not .ppm files) can be read into 
our program and converted to our ImageModel representation. Common code is extracted into AbstractFileInputCommand.
Furthermore, the ProjectFileInputCommand is responsible for reading in general project files. 
The same logic applies for file output. We supported the GUI controller and the text controller with 
GUIController and CommandController respectively.

VIEW:
The view has a text view and a GUI view interface with their corresponding implementations.

Image Citations
- emoji.ppm: https://www.clipartmax.com/middle/m2i8m2b1i8Z5b1b1_picture-300-x-300-pixel/
- cat.ppm: https://www.photos-public-domain.com/2018/05/01/siamese-tabby-cat/
