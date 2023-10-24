
# Image Application

Built a mvc with features that can be used to manipulate images to produce some interesting effects.

## About

Many applications use color images. A good number of these provide a way to change their appearance in different ways. 
For example, Instagram has "filters" that convert a picture into something more interesting. 
They do this by editing the colors of individual dots in the image (called pixels). Each pixel has a position in the image (row, column) and a color.
Colors on the computer are stored using three numbers: red, green, blue. Any color is a combination of these three base colors. 
Each of the three base colors -- red, green, and blue -- is called a channel in the image (i.e. a color image has 3 channels, ignoring transparent images). 
Each channel is usually stored using 8-bits, that is 256 distinct values. This is where the name 24-bit image comes from (three 8-bit channels).
Added functionality to the program, program is capable of applying the mosaic effect to an image, 
can also pixelate an image and apply a cross-Stitch pattern to an image.
There are 2 controllers being used both capable of communication with the model 
but "BasicController" which runs a batch file and implements the "Controller" interface.
"FeatureController" which interacts with a view and implements the "Features" interface.
The view implements the "ImageView" interface.
Also using a class that was implemented by Maria Jump, "ScrollablePicture", which is used in my code. 
A couple of other classes were also started in the features list.




## List Of Features

###The Features Of The Project Classes:

###Features Interface
```java
/**
   * Sets the view of the controller.
   *
   * @param imageView The image that is linked to the controller.
   */
  void setView(ImageView imageView);

  /** Blur the image. */
  void blur();

  /** Sharpen the image. */
  void sharpen();

  /** Sepia the image. */
  void sepia();

  /** GreyScale the image. */
  void greyScale();

  /** Reduce Color density of the image. */
  void reduceColourDensity();

  /** Mosaic the image. */
  void mosaic();

  /** Pixelate the image. */
  void pixelation();

  /** CrossStitch the image. */
  void patternGeneration();

  /** Load the image. */
  void loadImage();

  /** Save the image. */
  void saveImage();

  /** Start the batch processing. */
  void batchProcessing();

  /**
   * Runs a batch script.
   *
   * @param script The script that would be ran.
   */
  void runBatch(String script);

  /** Exits the batch processing. */
  void exitBatch();

  /** "Removes" a color. */
  void remove();

  /** Informs user how to remove color. */
  void removeHelp();

  /** Swaps two colors. */
  void swap();

  /** Informs user how to swap colors.*/
  void swapHelp();

  /** Restricts two colors. */
  void palette();

  /** Informs user how to use use a restrict the colors.*/
  void paletteHelp();
```
###View Interface
```java
  /** Makes the view visible. */
  void start();

  /**
   * Gets the name of the image.
   *
   * @return the name of the image.
   */
  String getImage();

  /**
   * Sets the image on the image panel.
   *
   * @param image The image that would be set.
   */
  void setImage(BufferedImage image);

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);

  /**
   * Displays an error as a pop up window.
   *
   * @param error The error that would be displayed.
   * @param errorLocation The location of said error.
   */
  void displayError(String error, String errorLocation);

  /**
   * Ask the user for input.
   *
   * @param question The question the user sees.
   * @return user's input.
   */
  int getInput(String question);

  /**
   * Ask the user for the name that they want to save their file as.
   *
   * @return user's input.
   */
  String saveImage();

  /**
   * Displays the report from a batch run.
   *
   * @param report The log of a batch run.
   */
  void setBatchReport(String report);

  /** Starts the batch mode. */
  void startBatch();

  /** Starts the batch mode. */
  void endBatch();

  /**
   * Displays the current log of what's happening in the controller.
   *
   * @param report The log from the controller.
   */
  void displayLog(String report);

  /** Shows the DMC colour Legend.
   *
   * @param dmc dmc color list.*/
  void showLegend(List<String[]> dmc);


  /** Start the color removal process. */
  void paletteHelp();

  /** Informs user how to remove color. */
  void removeHelp();

  /** Informs user how to remove color. */
  void swapHelp();

  /**
   * Gets the x coordinate of the image.
   *
   * @return an int.
   */
  int imagePixelX();

  /**
   * Gets the y coordinate of the image.
   *
   * @return an int.
   */
  int imagePixelY();

  /**
   * Gets the rgb values from the color legend.
   *
   * @return rgb values
   */
  int[] legendColor();

  /**
   * Gets all the selected rgb values from the color legend.
   *
   * @return selected colors
   */
  List<int[]> selectedColors();
```

####MenuBar Interface
```java
  /**
   * Connects the menu to the feature object, which tends to be a controller.
   *
   * @param controller The controller that connects to the menu.
   */
  void setFeatures(Features controller);
```

####Controller Interface
```java
  /**
   * Starts the controller and runs the program.
   * @param image the image model
   * @throws IOException If the file is not found.
   * @throws IllegalArgumentException If the image is null.
   */
  void start(ImageModel image) throws IOException;
```

####ImageModel Interface
```java
  /**
   * Grey scales an image that is passed as an array, returns a grey scaled version of the array.
   *
   * @throws IllegalArgumentException if the array is null
   */
  void greyScale();

  /**
   * Transforms an image into sepia tone which is passed as an array,
   * returns a sepia version of the array.
   *
   * @throws IllegalArgumentException if the array is null
   */
  void sepia();

  /**
   * Reduces the colour density of an image that is passed as
   * an array, returns a sepia version of the array.
   *
   * @param factor the amount of color pellet the image is meant to have
   * @throws IllegalArgumentException if the factor is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  void reduceColourDensity( int factor);

  /**
   * Blurs an image that is passed as an array, returns a blurred version of the array.
   *
   * @param intensity the amount of times you want the image blurred
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  void blur(int intensity);

  /**
   * Sharpens an image that is passed as an array, returns a sharpened version of the array.
   *
   * @param intensity the amount of times you want the image sharpened
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  void sharpen(int intensity);

  /**
   * Adds the mosaic effect to an image that is passed as an array,
   * returns a mosaic version of the array.
   *
   * @param seeds the amount of points on the image that decides the mosaic shapes
   * @return the mosaic form of the array
   * @throws IllegalArgumentException if the seeds is a non positive
   */
  void mosaic(int seeds);

  /**
   * Pixelates an image that is passed as an array, returns a pixelated version of the array.
   *
   * @param squares the amount of square pixels across the image
   * @throws IllegalArgumentException if the squares is out of range [0-length of the image]
   */
  void pixelation(int squares);

  /**
   * Creating an image pattern as a text file.
   */
  void patternGeneration();

  /**
   * Reading an image into an array.
   *
   * @param filename the name of the image
   * @throws IllegalArgumentException if filename doesnt exist or its empty or null
   */
  void readImage(String filename) throws IOException;

  /**
   * Writes an array into a image.
   *
   * @param filename the name of the image read
   * @throws IllegalArgumentException if filename doesnt exist or its empty or null
   * @throws IllegalArgumentException if arr is null
   */
  void writeImage(String filename) throws IOException;

  /**
   * Writes a string to a file.
   *
   * @param filename the name of the file.
   * @throws IOException if filename doesn't exist
   */
  void writeToFile(String filename) throws IOException;


  /**
   * Gets a buffered image using an array.
   *
   * @return a buffered image.
   */
  BufferedImage getBufferedImage();

  /**
   * Returns the array of dmc colors.
   *
   * @return an array that has the dmc colors.
   */
  List<String[]> getDmc();

  /**
   * Removes the color at the x and y pixel from the dmc color list.
   *
   * @param x the row index
   * @param y the height index
   */
  void removePixel(int x, int y);

  /**
   * Changes the any color that is similar to the color at the x and y
   * pixel to those set of rgb values.
   *
   * @param x the row index
   * @param y the height index
   * @param rgb rgb values that replace the color
   */
  void swapPixel(int x, int y, int[] rgb);

  /**
   * Changes every color to the closest color from the given list of colors.
   *
   * @param colors list of colors
   */
  void restrictColor(List<int[]> colors);
```

####Filters Interface
```java
  /**
   * Blurs an image that is passed as an array, returns a blurred version of the array.
   *
   * @param intensity the amount of times you want the image blurred
   * @param arr       the array that would be blurred
   * @return the blurred array
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] blur(int[][][] arr, int intensity);

  /**
   * Sharpens an image that is passed as an array, returns a sharpened version of the array.
   *
   * @param intensity the amount of times you want the image sharpened
   * @param arr       the array that would be sharpened
   * @return the sharpened array
   * @throws IllegalArgumentException if the intensity is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] sharpen(int[][][] arr, int intensity);
```

####Transforms Interface
```java
  /**
   * Grey scales an image that is passed as an array, returns a grey scaled version of the array.
   *
   * @param arr the array that would be grey scaled
   * @return the grey scaled array
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] greyScale(int[][][] arr);

  /**
   * Transforms an image into sepia tone which is passed as an array,
   * returns a sepia version of the array.
   *
   * @param arr the array that would be sepia
   * @return the sepia array
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] sepia(int[][][] arr);

  /**
   * Reduces the colour density of an image that is passed as
   * an array, returns a sepia version of the array.
   *
   * @param factor the amount of color pellet the image is meant to have
   * @param arr    the array that would be have it's colour density reduced
   * @return the reduced colour density array
   * @throws IllegalArgumentException if the factor is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] reduceColourDensity(int[][][] arr, int factor);
```

####Chunks Interface
```java
  /**
   * Adds the mosaic effect to an image that is passed as an array, returns a mosaic version of the
   * array.
   *
   * @param seeds the amount of points on the image that decides the mosaic shapes
   * @param arr the array that would have a mosaic effect
   * @return the mosaic form of the array
   * @throws IllegalArgumentException if the seeds is a non positive
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] mosaic(int[][][] arr, int seeds);

  /**
   * Pixelates an image that is passed as an array, returns a pixelated version of the array.
   *
   * @param squares the amount of square pixels across the image
   * @param arr the array that would be pixelated
   * @return the pixelated array
   * @throws IllegalArgumentException if the squares is out of range [0-length of the image]
   * @throws IllegalArgumentException if the array is null
   */
  int[][][] pixelation(int[][][] arr, int squares);

  /**
   * Creating a image pattern as a text file.
   *
   * @param arr the array that would have a mosaic effect
   * @return pattern
   * @throws IllegalArgumentException if the array is null
   */
  String patternGeneration(int[][][] arr);

  /**
   * Returns the array after a pattern as been generated.
   *
   * @return an array that has a pattern generated on it.
   */
  int[][][] getPatternGenerated();

  /**
   * Returns the array of dmc colors.
   *
   * @return an array that has the dmc colors.
   */
  List<String[]> getDmc();

  /**
   * Removes the color at the x and y pixel from the dmc color list.
   *
   * @param row the row location of that color
   * @param column the column location of that color
   */
  void remove(int row, int column);

  /**
   * Changes the color at the x and y pixel to those set of .
   *
   * @param row the row index
   * @param column the height index
   * @param rgb rgb values that replace the color
   */
  int[][][] swap(int row, int column, int[] rgb);

  /**
   * Changes every color to the closest color from the given list of colors.
   *
   * @param colors list of colors
   */
  int[][][] restrict(List<int[]> colors);
```

###SupportMethod Interface
```java
    /**
   * Clamps a value from going pass the specified range.
   *
   * @param lowerBound  the lower bound of the range
   * @param higherBound the higher bound of the range
   * @param value       the value that is being clamped
   * @return clamped value
   */
  int clamp(int lowerBound, int higherBound, int value);

  /**
   * Duplicates an array.
   *
   * @param arr the array that is meant to be duplicated
   * @return the duplicated array
   */
  int[][][] duplicate(int[][][] arr);

  /**
   * Reads a csv file into a 2d array.
   *
   * @param path the path to the csv file.
   * @return an array that has the contents of the file.
   */
  String[][] loadCsvFile(String path) throws IOException;
```

## How To Run
Click [here](https://www.jetbrains.com/help/idea/compiling-applications.html) to see how to run .jar file.
My .jar file has no arguments, using the link above you can run it to see the example of my run which was hard coded.

## How to Use the Program
Using the functionality of any specific class follow the format of "object name of specific class"."name of method()" e.g "image.blur()", image being a Image Model object.
Looking at the ExampleRun1.txt file you can see how to create objets for specific classes. 
It can be run with different images if you want a range of results.

###Version2
With the updated implementation, have added more example runs to show the full functionality of my implementation.
Using the "project4.jar" you can follow the ExampleRun2.txt from Version2 in "Description of Example Runs".

###Version3
For the final part of the project i have implemented 2 different controllers. The first controller "BasicController" runs the btachfiles.
Just follow Version2 above for how to do that. The second controller, "FeatureController", is more interactive which allows for communication between user and program.
To use the interactive controller you can use the ExampleRun3.txt from Version3 in "Description of Example Runs".

## Description of Example Runs.

saved as project3.jar

Run 1 -- Filename: ExampleRun1.txt:
1. Using the driver class.
2. Directory of file is gotten.
3. Create BasicFilter and BasicTransform objects.
4. Read the image as an array.
5. Create Blurs, sharpen, grey scale, sepia tone and reduced colour density of image.
6. Write and save all the different images.
8. Repeated all the previous steps on a different image
9. Print message of completion.

###Version2 of the project

saved as project4.jar

Run 1 -- Filename: ExampleRun2.txt:
1. Using the main file for controller .
2. Two batch files are declared "BatchFile1.txt" and "BatchFile2.txt"
3. Controller runs the "BatchFile1.txt" first then the "BatchFile2.txt".
4. Displays the log on the console as it's running.



###Version3 of the project

saved as project5.jar

Run1 -- Filename: ExampleRun3.txt
1. Using the main file for controller .
2. One batch files are declared "BatchFileProject5.txt" which was ran with the project5.jar
3. Controller runs it.
4. Displays the log on the console as it's running. As seen in ExampleRun3.txt.
5. Or you can run the project5.jar with a text "interactive" to run interactive controller.





## Design/Model Changes
Added an abstract class for  both the filters and transforms interface which is meant to be a basic implementation of how the features work.
Leaving space for modification if a user would like to make a different implementation of the features.
Added private method to both abstract classes, which are multiply and duplicate.
My ImageDriver(driver class) just shows how the code is run was previously named modification.

###version2
Added extra arguments to the "start" method of the controller.
Removed the link between the Filters, Transforms and Imagemodel interfaces.
added the method call of all the methods from the Filters, Transforms and Chunks interfaces into the ImageModel interface.
ImageModel interface has a single class implementation with the functionality stated above.
removed the chunkModel class and added the functionality to the basicModel class, permitted by the professor in the open mic.
Therefore added the javadoc to the imageModel interface.
Extracted some duplicated methods and made it into an interface called SupportMethods which has an implementation in the 
BasicMethod class which is extended by Filters, Transforms and Chunks classes.
Added a couple of methods to multiple classes.

###version3
Refactored my imageModel, made my model contain an image field.
Added more functionality to the model to encapsulate the full functionality of the requirements.


## Assumptions
The kenel size would be constant for future implementations of the classes.
.jar file would be run from a res folder.
The factor of the ReduceColourDensity() accounts for each particular colour, so factor of 2 means 2 colours for each and every colour.
When the patternGeneration() is called, the txt file is immediately created.
The user's file would be named appropriately for the controller class.
method names are accurate in the file used in the controller class, with the right arguments also.
Every image is saved has .PNG and files would be saved in .txt.

###Version3
To display symbol on image was to display it on the legend.




## Limitations
Changing the kennel size would cause a failure.
User has to use names that are listed in controller class to use method calls.
Bacth file for using the controller must always be in the project folder.
Name of .jar file might affect code.

###Version3
Legend Buttons would always be active but it doesn't affect computation.
Images doesnt cover the whole pane for some images and extends for other images which can be scrolled.
Wasn't able to complete the extra credit 3.




## Image Citation
I took this images by myself and I authorize the use of this images.


