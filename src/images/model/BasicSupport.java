package images.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class of helper methods that are used.
 */
public class BasicSupport implements SupportMethod {
  @Override
  public int clamp(int lowerBound, int higherBound, int value) {
    if (lowerBound >= higherBound) {
      throw new IllegalArgumentException("lowerBound has to be less than higherBound");
    }
    if (value > higherBound) {
      return higherBound;
    } else if (value < lowerBound) {
      return lowerBound;
    }
    return value;
  }

  @Override
  public int[][][] duplicate(int[][][] arr) {
    int aRow = arr.length;
    int aCol = arr[0].length;
    int aVal = arr[0][0].length;
    int[][][] transformArray = new int[aRow][aCol][aVal];
    for (int row = 0; row < aRow; row++) {
      for (int col = 0; col < aCol; col++) {
        for (int val = 0; val < aVal; val++) {
          transformArray[row][col][val] = arr[row][col][val];
        }
      }
    }
    return transformArray;
  }

  @Override
  public String[][] loadCsvFile(String path) throws IOException {
    String line;
    List<String[]> values = new ArrayList<>();
    BufferedReader br = new BufferedReader(new FileReader(path));
    while ((line = br.readLine()) != null) {
      values.add(line.split(","));
    }
    values = values.subList(1, values.size());
    String[][] array = new String[values.size()][0];

    values.toArray(array);
    return array;
  }

}
