package images.view;

import images.controller.Features;
import java.awt.image.BufferedImage;
import java.util.List;

/** The interface for our view class. */
public interface ImageView {

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
   * Gets the x coordinate of the image that was clicked.
   *
   * @return an int.
   */
  int imagePixelX();

  /**
   * Gets the y coordinate of the image that was clicked.
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

}
