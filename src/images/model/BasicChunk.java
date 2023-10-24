package images.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** A basic implementation of a class that represents ways to chunk images. */
public class BasicChunk extends BasicSupport implements Chunks {
  private final List<String[]> usedDmc;
  private int[][][] patternGenerated;

  public BasicChunk() {
    patternGenerated = new int[0][][];
    usedDmc = new ArrayList<>();
  }

  @Override
  public int[][][] mosaic(int[][][] arr, int seeds) {
    if (seeds <= 0) {
      throw new IllegalArgumentException("seeds must be positive");
    }
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    int[][][] chunkArray = duplicate(arr);
    int arrRow = arr.length;
    int arrCol = arr[0].length;
    int[][] points = generateSeed(seeds, arrRow, arrCol);
    for (int row = 0; row < arrRow; row++) {
      for (int col = 0; col < arrCol; col++) {
        double distance = Double.MAX_VALUE;
        int closestRow = 0;
        int closestCol = 0;
        for (int i = 0; i < seeds; i++) {
          double length =
              Math.sqrt(Math.pow(points[i][0] - row, 2) + Math.pow(points[i][1] - col, 2));
          if (length < distance) {
            distance = length;
            closestRow = points[i][0];
            closestCol = points[i][1];
          }
        }
        chunkArray[row][col] = chunkArray[closestRow][closestCol];
      }
    }

    return chunkArray;
  }

  private int[][] generateSeed(int seeds, int row, int col) {
    int[][] generatedSeeds = new int[seeds][2];
    Random rand = new Random();
    for (int i = 0; i < seeds; i++) {
      generatedSeeds[i][0] = rand.nextInt(row);
      generatedSeeds[i][1] = rand.nextInt(col);
    }
    return generatedSeeds;
  }

  @Override
  public int[][][] pixelation(int[][][] arr, int squares) {
    if (squares <= 0) {
      throw new IllegalArgumentException("seeds must be positive");
    }
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    } else if (squares > arr[0].length) {
      throw new IllegalArgumentException(
          "seeds can't be greater than length of image" + " which is " + arr[0].length);
    }
    int[][][] chunkArray = duplicate(arr);
    float squareLength = (float) chunkArray[0].length / squares;
    int superPixelRow = Math.round((float) chunkArray.length / squareLength);
    float squareHeight = (float) chunkArray.length / superPixelRow;
    for (int row = 0; row < superPixelRow; row++) {
      for (int col = 0; col < squares; col++) {
        superPixel(chunkArray, row, col, squareLength, squareHeight);
      }
    }

    return chunkArray;
  }

  private int[][][] superPixel(
      int[][][] chunkArray, int row, int col, float squareLength, float squareHeight) {
    int pixelIndex;
    int red;
    int green;
    int blue;
    List<int[]> pixels = new ArrayList<>();
    for (int pixelRow = Math.round(row * squareHeight);
        pixelRow < (row + 1) * squareHeight;
        pixelRow++) {
      for (int pixelCol = Math.round(col * squareLength);
          pixelCol < (col + 1) * squareLength;
          pixelCol++) {
        pixels.add(chunkArray[pixelRow][pixelCol]);
      }
    }

    if (pixels.size() % 2 == 1) {
      pixelIndex = (pixels.size() / 2 + pixels.size() % 2) - 1;
      red = pixels.get(pixelIndex)[0];
      green = pixels.get(pixelIndex)[1];
      blue = pixels.get(pixelIndex)[2];
    } else {
      pixelIndex = pixels.size() / 2;
      red = (pixels.get(pixelIndex - 1)[0] + pixels.get(pixelIndex)[0]) / 2;
      green = (pixels.get(pixelIndex - 1)[1] + pixels.get(pixelIndex)[1]) / 2;
      blue = (pixels.get(pixelIndex - 1)[2] + pixels.get(pixelIndex)[2]) / 2;
    }

    for (int pixelRow = Math.round(row * squareHeight);
        pixelRow < (row + 1) * squareHeight;
        pixelRow++) {
      for (int pixelCol = Math.round(col * squareLength);
          pixelCol < (col + 1) * squareLength;
          pixelCol++) {
        chunkArray[pixelRow][pixelCol][0] = red;
        chunkArray[pixelRow][pixelCol][1] = green;
        chunkArray[pixelRow][pixelCol][2] = blue;
      }
    }
    return chunkArray;
  }

  @Override
  public String patternGeneration(int[][][] arr) {
    if (arr == null | arr.length <= 0) {
      throw new IllegalArgumentException("Image doesn't exist ");
    }
    patternGenerated = duplicate(arr);
    File excel = new File("");
    String path = excel.getAbsolutePath() + "\\DMC.csv";
    String[][] dmc = new String[0][0];
    try {
      dmc = loadCsvFile(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
    List<String> symbols = getSymbols();
    replaceDmc(dmc, symbols);
    int squares = 100;
    System.out.println(squares);
    patternGenerated = pixelation(patternGenerated, squares);
    float squareLength = (float) patternGenerated[0].length / squares;
    int superPixelRow = Math.round((float) patternGenerated.length / squareLength);
    float squareHeight = (float) patternGenerated.length / superPixelRow;
    Map<String, String> symbolMap = new HashMap<>();
    StringBuilder pattern = new StringBuilder();
    floss(dmc, squareLength, squareHeight, squares, superPixelRow, symbolMap, pattern);
    pattern.append("\n").append("Symbols and their equivalent Floss Colours:").append("\n");
    createSymbolMap(symbolMap, pattern);
    return pattern.toString();
  }

  /**
   * Builds a list of symbols.
   *
   * @return a list of symbols.
   */
  private List<String> getSymbols() {
    List<String> symbols = new ArrayList<>();

    for (int i = 97; i <= 586; i++) {
      symbols.add("" + (char) i);
    }
    return symbols;
  }

  /**
   * Replaces a column with a list of symbols.
   *
   * @param dmc the array whose column is to be replaced.
   * @param symbol the list that contains the new values.
   */
  private void replaceDmc(String[][] dmc, List<String> symbol) {
    for (int row = 0; row < dmc.length; row++) {
      dmc[row][1] = symbol.get(row);
    }
  }

  /**
   * Maps a symbol to a floss color and stores output in a 'pattern' string.
   *
   * @param dmc the dmc values table
   * @param squareLength the length of the super pixel
   * @param superPixelRow the number of super pixel rows
   * @param squareHeight the height of the super pixel
   * @param symbolMap the symbol map
   * @param pattern the string that holds the pattern
   */
  private void floss(
      String[][] dmc,
      float squareLength,
      float squareHeight,
      int squares,
      int superPixelRow,
      Map<String, String> symbolMap,
      StringBuilder pattern) {
    List<String> dmcNames = new ArrayList<>();
    usedDmc.clear();
    int[] pixelChannels = new int[3];
    int[] dmcChannels = new int[3];
    for (int superRow = 0; superRow < superPixelRow; superRow++) {
      for (int superCol = 0; superCol < squares; superCol++) {
        int row = Math.round(superRow * squareHeight);
        int col = Math.round(superCol * squareLength);
        getSuperColour(pixelChannels, row, col);
        int dmcIndex =
            dmcIndex(
                dmc,
                pixelChannels,
                dmcChannels);
        pattern.append(dmc[dmcIndex][1]).append(" ");
        symbolMap.put(dmc[dmcIndex][1] + " ", "DMC-" + dmc[dmcIndex][0]);
        if (!dmcNames.contains(dmc[dmcIndex][0])) {
          usedDmc.add(dmc[dmcIndex]);
          dmcNames.add(dmc[dmcIndex][0]);
        }

        for (int pixelRow = row; pixelRow < (superRow + 1) * squareHeight; pixelRow++) {
          for (int pixelCol = col; pixelCol < (superCol + 1) * squareLength; pixelCol++) {
            patternGenerated[pixelRow][pixelCol][0] = Integer.parseInt(dmc[dmcIndex][2]);
            patternGenerated[pixelRow][pixelCol][1] = Integer.parseInt(dmc[dmcIndex][3]);
            patternGenerated[pixelRow][pixelCol][2] = Integer.parseInt(dmc[dmcIndex][4]);
          }
        }
      }
      pattern.append("\n");
    }
  }

  /**
   * Gets the index of the dmc value that's the closest to the super pixel.
   *
   * @param dmc the dmc value array.
   * @param superChannels the channel of the super pixel values.
   * @param dmcChannels the channel of dmc values.
   * @return the closest dmc value
   */
  private int dmcIndex(
      String[][] dmc,
      int[] superChannels,
      int[] dmcChannels) {
    int dmcIndex = 0;
    double distance = Double.MAX_VALUE;
    for (int rows = 0; rows < dmc.length; rows++) {
      dmcChannels[0] = Integer.parseInt(dmc[rows][2]);
      dmcChannels[1] = Integer.parseInt(dmc[rows][3]);
      dmcChannels[2] = Integer.parseInt(dmc[rows][4]);
      if (distance(superChannels, dmcChannels) < distance) {
        distance = distance(superChannels, dmcChannels);
        dmcIndex = rows;
      }
    }

    return dmcIndex;
  }

  /**
   * Gets a pixel Channels colour.
   *
   * @param pixelChannels the channel of the super pixel.
   * @param row the row of the superPixel.
   * @param col the col of the superPixel.
   */
  private void getSuperColour(int[] pixelChannels, int row, int col) {
    pixelChannels[0] = patternGenerated[row][col][0];
    pixelChannels[1] = patternGenerated[row][col][1];
    pixelChannels[2] = patternGenerated[row][col][2];
  }

  /**
   * Calculates the distance between two colors.
   *
   * @param superChannel the first color.
   * @param dmcChannel the second color.
   * @return the distance as a double.
   */
  private double distance(int[] superChannel, int[] dmcChannel) {
    long rMean = (superChannel[0] + dmcChannel[0]) / 2;
    long rDif = superChannel[0] - dmcChannel[0];
    long gDif = superChannel[1] - dmcChannel[1];
    long bDif = superChannel[2] - dmcChannel[2];
    return Math.sqrt(
        ((2 + (rMean / 256.0)) * rDif * rDif)
            + (4 * gDif * gDif)
            + (((2 + ((255 - rMean) / 256.0)) * bDif * bDif)));
  }

  /**
   * Maps the symbols to their respective DMC values.
   *
   * @param symbolMap the hashMap that contains the values.
   * @param pattern the string that holds the pattern
   */
  private void createSymbolMap(Map<String, String> symbolMap, StringBuilder pattern) {
    for (String symbols : symbolMap.keySet()) {
      pattern.append(symbols).append(symbolMap.get(symbols)).append("\n");
    }
  }

  @Override
  public int[][][] getPatternGenerated() {
    return duplicate(patternGenerated);
  }

  @Override
  public List<String[]> getDmc() {
    return usedDmc;
  }

  @Override
  public void remove(int row, int col) {
    if (row < 0 | col < 0) {
      throw new IllegalArgumentException("You didn't click on the picture");
    }
    for (int i = 0; i < usedDmc.size(); i++) {
      if (patternGenerated[row][col][0] == Integer.parseInt(usedDmc.get(i)[2])
          && patternGenerated[row][col][1] == Integer.parseInt(usedDmc.get(i)[3])
          && patternGenerated[row][col][2] == Integer.parseInt(usedDmc.get(i)[4])) {
        usedDmc.remove(i);
        return;
      }
    }
  }

  @Override
  public int[][][] swap(int row, int col, int[] rgb) {
    if (rgb == null | rgb[0] < 0) {
      throw new IllegalArgumentException("You didn't click on a color in the legend");
    }
    if (row < 0 | col < 0) {
      throw new IllegalArgumentException("You didn't click on the picture");
    }
    int[][][] tempSwap = duplicate(patternGenerated);
    for (int i = 0; i < patternGenerated.length; i++) {
      for (int j = 0; j < patternGenerated[0].length; j++) {
        if (Arrays.equals(tempSwap[i][j], patternGenerated[row][col])) {
          tempSwap[i][j][0] = rgb[0];
          tempSwap[i][j][1] = rgb[1];
          tempSwap[i][j][2] = rgb[2];
        }
      }
    }
    patternGenerated = duplicate(tempSwap);
    return getPatternGenerated();
  }

  @Override
  public int[][][] restrict(List<int[]> colors) {
    if (colors == null | colors.isEmpty()) {
      throw new IllegalArgumentException("You didn't click on any color in the legend");
    }
    int[][][] tempSwap = duplicate(patternGenerated);
    int index = 0;
    System.out.println(colors.size());
    for (int rows = 0; rows < colors.size(); rows++) {
      System.out.println(
          colors.get(rows)[0] + " x " + colors.get(rows)[1] + " x " + colors.get(rows)[2]);
    }
    for (int i = 0; i < tempSwap.length; i++) {
      for (int j = 0; j < tempSwap[0].length; j++) {
        double distance = Double.MAX_VALUE;
        for (int rows = 0; rows < colors.size(); rows++) {
          if (distance(tempSwap[i][j], colors.get(rows)) < distance) {
            distance = distance(tempSwap[i][j], colors.get(rows));
            index = rows;
          }
        }
        tempSwap[i][j][0] = colors.get(index)[0];
        tempSwap[i][j][1] = colors.get(index)[1];
        tempSwap[i][j][2] = colors.get(index)[2];
      }
    }
    patternGenerated = duplicate(tempSwap);
    return getPatternGenerated();
  }
}
