How To Use
To run this program, there are 3 options that a user can specify from their run configurations: 

1) -text (Runs the text-based command view of the collager project)
2) -file path-to-script (Runs a script of commands at path-to-script)
3) -gui (Runs the GUI version of the collager project)

Make sure to create a run configuration for the Main method inside of the controller package.
** The JAR must be run from inside of the res/ directory (i.e., the working directory should be res/)
** Make sure that res is on the same directory level as src/ and test/

Design Changes
- Refactored the Filter interface so that it operates on an entire image rather than a single pixel. A filter should take in the image that
it will apply the filter to, as well as the composite image of every image below the current image. This was done so that filters are more flexible
and can use the information of an entire image rather than a single pixel.
- Moved file IO commands from the model to the controller to maintain proper separation of concerns. We restructured the hierarchy of file IO design. At the top
of the design is a FileInputCommand which holds common methods for all file input commands such as image reading, project reading, etc. Then, we have more specific
interfaces ImageFileInputCommand and ProjectFileInputCommand. Classes that implement these specific interfaces will also extend the abstract class that implements 
FileInputCommand to get access to common file input methods.
- Kept the old view and controller interfaces intact for backwards compatibility and created new view and controller interfaces specifically for the GUI version
of the program.
- Added a command for loading a project into the collager, which is something that we did not have for Assignment #4.

Program Completion Status
- All required features in Assignment #5 have been implemented.

Image Citations
- emoji.ppm: https://www.clipartmax.com/middle/m2i8m2b1i8Z5b1b1_picture-300-x-300-pixel/
- cat.ppm: https://www.photos-public-domain.com/2018/05/01/siamese-tabby-cat/
