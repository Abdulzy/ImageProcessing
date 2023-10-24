package images.model;

import java.util.List;

/** The chunks interface, shows the description of each public method. */
public interface Chunks {
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

}
