package images.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * An interface called imagModel.
 */
public interface ImageModel {
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
  void reduceColourDensity(int factor);

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
}
