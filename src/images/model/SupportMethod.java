package images.model;

import java.io.IOException;

/**
 * Support method used by the image model.
 */
public interface SupportMethod {
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

}
