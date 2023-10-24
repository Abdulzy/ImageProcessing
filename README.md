# ImageProcessing

Built an MVC with features that can be used to manipulate images to produce some interesting effects.

# About

Many applications use color images. A good number of these provide a way to change their appearance in different ways. 
For example, Instagram has "filters" that convert a picture into something more interesting. 
They do this by editing the colors of individual dots in the image (called pixels). Each pixel has a position in the image (row, column) and a color.
Colors on the computer are stored using three numbers: red, green, and blue. Any color is a combination of these three base colors. 
Each of the three base colors -- red, green, and blue -- is called a channel in the image (i.e. a color image has 3 channels, ignoring transparent images). 
Each channel is usually stored using 8 bits, that is 256 distinct values. This is where the name 24-bit image comes from (three 8-bit channels).
Added functionality to the program, the program is capable of applying the mosaic effect to an image, 
can also pixelate an image and apply a cross-stitch pattern to an image.
There are 2 controllers being used both capable of communication with the model 
but "BasicController" which runs a batch file and implements the "Controller" interface.
"FeatureController" which interacts with a view and implements the "Features" interface.
The view implements the "ImageView" interface.
Also I used a class that was implemented by Maria Jump, "ScrollablePicture", which is used in my code. 
A couple of other classes were also started in the features list.




# High level diagram of project

![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/UML.png?raw=true)


# How To Run
Click [here](https://www.jetbrains.com/help/idea/compiling-applications.html) to see how to run .jar file.
My .jar file has no arguments, using the link above you can run it to see the example of my run which was hard coded.

## How to Use the Program
Using the functionality of any specific class follow the format of "object name of specific class"."name of method()" e.g "image.blur()", image being a Image Model object.
Looking at the ExampleRun1.txt file you can see how to create objets for specific classes. 
It can be run with different images if you want a range of results.

## Version2
With the updated implementation, have added more example runs to show the full functionality of my implementation.
Using the "project4.jar" you can follow the ExampleRun2.txt from Version2 in "Description of Example Runs".

## Version3
For the final part of the project i have implemented 2 different controllers. The first controller "BasicController" runs the btachfiles.
Just follow Version2 above for how to do that. The second controller, "FeatureController", is more interactive which allows for communication between user and program.
To use the interactive controller you can use the ExampleRun3.txt from Version3 in "Description of Example Runs".

# Description of Example Runs.
Have files in the root folder if running through IntelliJ.

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

## Version2 of the project

saved as project4.jar

Run 1 -- Filename: ExampleRun2.txt:
1. Using the main file for the controller.
2. Two batch files are declared "BatchFile1.txt" and "BatchFile2.txt"
3. Controller runs the "BatchFile1.txt" first then the "BatchFile2.txt".
4. Displays the log on the console as it's running.



## Version3 of the project

saved as project5.jar

Run1 -- Filename: ExampleRun3.txt
1. Using the main file for the controller.
2. One batch file is declared "BatchFileProject5.txt" which was run with the project5.jar
3. Controller runs it.
4. Displays the log on the console as it's running. As seen in ExampleRun3.txt.
5. Or you can run the project5.jar with the text "interactive" to run the interactive controller.





# Design/Model Changes
Added an abstract class for  both the filters and transforms interface which is meant to be a basic implementation of how the features work.
Leaving space for modification if a user would like to make a different implementation of the features.
Added private method to both abstract classes, which are multiply and duplicate.
My ImageDriver(driver class) just shows how the code is run was previously named modification.

## version2
Added extra arguments to the "start" method of the controller.
Removed the link between the Filters, Transforms, and Imagemodel interfaces.
added the method call of all the methods from the Filters, Transforms, and Chunks interfaces into the ImageModel interface.
ImageModel interface has a single-class implementation with the functionality stated above.
removed the chunkModel class and added the functionality to the basicModel class, permitted by the professor in the open mic.
Therefore added the Javadoc to the imageModel interface.
Extracted some duplicated methods and made them into an interface called SupportMethods which has an implementation in the 
BasicMethod class which is extended by Filters, Transforms, and Chunks classes.
Added a couple of methods to multiple classes.

## version3
Refactored my imageModel, and made my model contain an image field.
Added more functionality to the model to encapsulate the full functionality of the requirements.


# Assumptions
The kennel size would be constant for future implementations of the classes.
.jar file would be run from a res folder.
The factor of the ReduceColourDensity() accounts for each particular color, so a factor of 2 means 2 colors for each and every color.
When the patternGeneration() is called, the txt file is immediately created.
The user's file would be named appropriately for the controller class.
method names are accurate in the file used in the controller class, with the right arguments also.
Every image is saved as .PNG and files are saved in .txt.

## Version3
To display the symbol on the image was to display it on the legend.




# Limitations
Changing the kennel size would cause a failure.
The user has to use names that are listed in the controller class to use method calls.
The batch file for using the controller must always be in the project folder.
The name of the .jar file might affect the code.

## Version3
Legend Buttons would always be active but it doesn't affect computation.
Images don't cover the whole pane for some images and extend for other images that can be scrolled.
Wasn't able to complete the extra credit 3.




# Image Citation
I took these images by myself and I authorize the use of these images.

# Preview

## Initial
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/base%20image.png?raw=true)

## Blurred Image
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/blur%20image.png?raw=true)


## Greyed Image
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/grey%20image.png?raw=true)


## Mosaiced Image
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/mosaic%20image.png?raw=true)


## Pixelated Image
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/pixel%20image.png?raw=true)


## Before Batch Run
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/batch%20run.png?raw=true)


## After Batch Run
![alt text](https://github.com/Abdulzy/ImageProcessing/blob/master/res/batch%20run%20complete.png?raw=true)



