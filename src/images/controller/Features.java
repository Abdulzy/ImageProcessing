package images.controller;

import images.view.ImageView;

/** A list of features the controller and view can support. */
public interface Features {

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
}
