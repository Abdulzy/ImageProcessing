package images.model;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/** A Image model of the project that contains all the required functionality. */
public class BasicModel implements ImageModel {
  private final Filters filter;
  private final Transforms transform;
  private final Chunks chunk;
  private int[][][] image;
  private int[][][] originalImage;
  private String pattern;

  /** The constructor that initializes all the type of image modification options. */
  public BasicModel() {
    filter = new BasicFilter();
    transform = new BasicTransform();
    chunk = new BasicChunk();
    image = new int[0][][];
    originalImage = new int[0][][];
    pattern = "";
  }

  @Override
  public void greyScale() {
    image = transform.greyScale(originalImage);
  }

  @Override
  public void sepia() {
    image = transform.sepia(originalImage);
  }

  @Override
  public void reduceColourDensity(int factor) {
    image = transform.reduceColourDensity(originalImage, factor);
  }

  @Override
  public void blur(int intensity) {
    image = filter.blur(originalImage, intensity);
  }

  @Override
  public void sharpen(int intensity) {
    image = filter.sharpen(originalImage, intensity);
  }

  @Override
  public void mosaic(int seeds) {
    image = chunk.mosaic(originalImage, seeds);
  }

  @Override
  public void pixelation(int squares) {
    image = chunk.pixelation(originalImage, squares);
  }

  @Override
  public void patternGeneration() {
    pattern = chunk.patternGeneration(originalImage);
    image = chunk.getPatternGenerated();
  }

  @Override
  public void readImage(String filename) throws IOException {
    if (filename == null | filename.isEmpty()) {
      throw new IllegalArgumentException("filename is illegal ");
    }
    image = ImageUtilities.readImage(filename.trim());
    originalImage = ImageUtilities.readImage(filename.trim());
  }

  @Override
  public void writeImage(String filename) throws IOException {
    if (image == null | image.length <= 0) {
      throw new IllegalArgumentException("Illegal array length ");
    }
    if (filename == null | filename.isEmpty()) {
      throw new IllegalArgumentException("filename is illegal ");
    }
    ImageUtilities.writeImage(image, image[0].length, image.length, filename.trim());
    originalImage = ImageUtilities.readImage(filename.trim());
  }

  @Override
  public void writeToFile(String filename) throws IOException {
    if (filename == null | filename.isEmpty() | pattern.isEmpty()) {
      throw new IllegalArgumentException("filename or content is illegal ");
    }
    FileWriter myWriter = new FileWriter(filename);
    myWriter.write(pattern);
    myWriter.close();
  }

  @Override
  public BufferedImage getBufferedImage() {
    if (image == null | image.length <= 0) {
      throw new IllegalArgumentException("Illegal array length ");
    }
    return ImageUtilities.getBufferedImage(image, image[0].length, image.length);
  }

  @Override
  public List<String[]> getDmc() {
    return chunk.getDmc();
  }

  @Override
  public void removePixel(int x, int y) {
    chunk.remove(x, y);
  }

  @Override
  public void swapPixel(int x, int y, int[] rgb) {
    image = chunk.swap(x, y, rgb);
  }

  @Override
  public void restrictColor(List<int[]> colors) {
    image = chunk.restrict(colors);
  }
}
