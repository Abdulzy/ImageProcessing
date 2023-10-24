package images.model;


/**
 * A basic implementation and representation of transform.
 */
public class BasicTransform extends BasicSupport implements Transforms {

  private final double[][] greyKennel;
  private final double[][] sepiaKennel;

  /**
   * The constructor that initializes two kennels.
   */
  public BasicTransform() {
    greyKennel = new double[][]{{0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722}};
    sepiaKennel = new double[][]{{0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168},
      {0.272, 0.534, 0.131}};
  }

  @Override
  public int[][][] greyScale(int[][][] arr) {
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] transformArray = duplicate(arr);
    return multiply(greyKennel, transformArray);
  }

  @Override
  public int[][][] sepia(int[][][] arr) {
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] transformArray = duplicate(arr);
    return multiply(sepiaKennel, transformArray);
  }

  @Override
  public int[][][] reduceColourDensity(int[][][] arr, int factor) {
    if (factor <= 0) {
      throw new IllegalArgumentException("Factor must be positive");
    }
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] reducedArray = duplicate(arr);
    for (int i = 0; i < reducedArray.length - 1; i++) {
      for (int j = 1; j < reducedArray[i].length - 1; j++) {
        int oldR = reducedArray[i][j][0];
        int oldG = reducedArray[i][j][1];
        int oldB = reducedArray[i][j][2];
        int newR = (factor * oldR / 255 * 255 / factor);
        int newG = (factor * oldG / 255 * 255 / factor);
        int newB = (factor * oldB / 255 * 255 / factor);
        reducedArray[i][j][0] = newR;
        reducedArray[i][j][1] = newG;
        reducedArray[i][j][2] = newB;

        int errR = oldR - newR;
        int errG = oldG - newG;
        int errB = oldB - newB;


        //adding the 7/16
        int r = reducedArray[i][j + 1][0];
        int g = reducedArray[i][j + 1][1];
        int b = reducedArray[i][j + 1][2];

        r = r + errR * 7 / 16;
        g = g + errG * 7 / 16;
        b = b + errB * 7 / 16;
        reducedArray[i][j + 1][0] = r;
        reducedArray[i][j + 1][1] = g;
        reducedArray[i][j + 1][2] = b;

        //adding the 3/16
        r = reducedArray[i + 1][j - 1][0];
        g = reducedArray[i + 1][j - 1][1];
        b = reducedArray[i + 1][j - 1][2];

        r = r + errR * 3 / 16;
        g = g + errG * 3 / 16;
        b = b + errB * 3 / 16;
        reducedArray[i + 1][j - 1][0] = r;
        reducedArray[i + 1][j - 1][1] = g;
        reducedArray[i + 1][j - 1][2] = b;

        //adding the 5/16
        r = reducedArray[i + 1][j][0];
        g = reducedArray[i + 1][j][1];
        b = reducedArray[i + 1][j][2];

        r = r + errR * 5 / 16;
        g = g + errG * 5 / 16;
        b = b + errB * 5 / 16;
        reducedArray[i + 1][j][0] = r;
        reducedArray[i + 1][j][1] = g;
        reducedArray[i + 1][j][2] = b;

        //adding the 1/16
        r = reducedArray[i + 1][j + 1][0];
        g = reducedArray[i + 1][j + 1][1];
        b = reducedArray[i + 1][j + 1][2];

        r = r + errR * 1 / 16;
        g = g + errG * 1 / 16;
        b = b + errB * 1 / 16;
        reducedArray[i + 1][j + 1][0] = r;
        reducedArray[i + 1][j + 1][1] = g;
        reducedArray[i + 1][j + 1][2] = b;

      }
    }
    return reducedArray;
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
    for (int row = 0; row < arrRow; row++) {
      for (int col = 0; col < arrCol; col++) {
        double r = 0;
        double g = 0;
        double b = 0;
        r = r + (multiplyArray[row][col][0] * kennel[0][0]);
        g = g + (multiplyArray[row][col][0] * kennel[1][0]);
        b = b + (multiplyArray[row][col][0] * kennel[2][0]);

        r = r + (multiplyArray[row][col][1] * kennel[0][1]);
        g = g + (multiplyArray[row][col][1] * kennel[1][1]);
        b = b + (multiplyArray[row][col][1] * kennel[2][1]);

        r = r + (multiplyArray[row][col][2] * kennel[0][1]);
        g = g + (multiplyArray[row][col][2] * kennel[1][1]);
        b = b + (multiplyArray[row][col][2] * kennel[2][1]);

        multiplyArray[row][col][0] = clamp(0, 255, (int) r);
        multiplyArray[row][col][1] = clamp(0, 255, (int) g);
        multiplyArray[row][col][2] = clamp(0, 255, (int) b);
      }
    }
    return multiplyArray;
  }
}
