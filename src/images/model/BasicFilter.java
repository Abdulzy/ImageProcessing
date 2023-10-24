package images.model;

/**
 * A basic implementation and representation of filters.
 */
public class BasicFilter extends BasicSupport implements Filters {

  private final double[][] blurKennel;
  private final double[][] sharpenKennel;

  /**
   * The constructor that initializes two kennels.
   */
  public BasicFilter() {
    blurKennel = new double[][]{{(1 / 16.0), (1 / 8.0), (1 / 16.0)},
      {(1 / 8.0), (1 / 4.0), (1 / 8.0)},
      {(1 / 16.0), (1 / 8.0), (1 / 16.0)}};
    sharpenKennel = new double[][]{{(-1 / 8.0), (-1 / 8.0), (-1 / 8.0), (-1 / 8.0), (-1 / 8.0)},
      {(-1 / 8.0), (1 / 4.0), (1 / 4.0), (1 / 4.0), (-1 / 8.0)},
      {(-1 / 8.0), (1 / 4.0), 1, (1 / 4.0), (-1 / 8.0)},
      {(-1 / 8.0), (1 / 4.0), (1 / 4.0), (1 / 4.0), (-1 / 8.0)},
      {(-1 / 8.0), (-1 / 8.0), (-1 / 8.0), (-1 / 8.0), (-1 / 8.0)}};
  }

  @Override
  public int[][][] blur(int[][][] arr, int intensity) {
    if (intensity <= 0) {
      throw new IllegalArgumentException("intensity must be positive");
    }
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] filterArray = duplicate(arr);
    for (int i = 0; i < intensity; i++) {
      filterArray = multiply(blurKennel, filterArray);
    }
    return filterArray;
  }

  @Override
  public int[][][] sharpen(int[][][] arr, int intensity) {
    if (intensity <= 0) {
      throw new IllegalArgumentException("intensity must be positive");
    }
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] filterArray = duplicate(arr);
    for (int i = 0; i < intensity; i++) {
      filterArray = multiply(sharpenKennel, filterArray);
    }
    return filterArray;
  }


  /**
   * Multiplies the kennel and the image array.
   *
   * @param kennel        the array that represents the image transformation
   * @param multiplyArray the array of the image
   * @return the multiplied array
   */
  private int[][][] multiply(double[][] kennel, int[][][] multiplyArray) {
    int arrRow = multiplyArray.length;
    int arrCol = multiplyArray[0].length;
    int kennelRow = kennel.length;
    int kennelCol = kennel[0].length;
    int offset = kennelRow / 2;
    for (int row = 0; row < arrRow; row++) {
      for (int col = 0; col < arrCol; col++) {
        double r = 0;
        double g = 0;
        double b = 0;
        for (int i = 0; i < kennelRow; i++) {
          for (int j = 0; j < kennelCol; j++) {
            int x = row + i - offset;
            int y = col + j - offset;
            if ((x >= 0 && x < arrRow) && (y >= 0) && (y < arrCol)) {
              r = r + (multiplyArray[x][y][0] * kennel[i][j]);
              g = g + (multiplyArray[x][y][1] * kennel[i][j]);
              b = b + (multiplyArray[x][y][2] * kennel[i][j]);
            }
          }
        }

        multiplyArray[row][col][0] = clamp(0, 255, (int) r);
        multiplyArray[row][col][1] = clamp(0, 255, (int) g);
        multiplyArray[row][col][2] = clamp(0, 255, (int) b);
      }
    }
    return multiplyArray;
  }

}
