import images.model.ImageModel;
import java.awt.image.BufferedImage;
import java.util.List;

/** The mock model for testing the controller. */
public class MockModel implements ImageModel {

  private final StringBuilder log;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void greyScale() {
    log.append("Ran greyScale\n");
  }

  @Override
  public void sepia() {
    log.append("Ran sepia\n");
  }

  @Override
  public void reduceColourDensity(int factor) {
    log.append("Ran reduceColourDensity\n");
  }

  @Override
  public void blur(int intensity) {
    log.append("Ran blur\n");
  }

  @Override
  public void sharpen(int intensity) {
    log.append("Ran sharpen\n");
  }

  @Override
  public void mosaic(int seeds) {
    log.append("Ran mosaic\n");
  }

  @Override
  public void pixelation(int squares) {
    log.append("Ran pixelation\n");
  }

  @Override
  public void patternGeneration() {
    log.append("Ran patternGeneration\n");
  }

  @Override
  public void readImage(String filename) {
    log.append("Ran readImage\n");
  }

  @Override
  public void writeImage(String filename) {
    log.append("Ran writeImage\n");
  }

  @Override
  public void writeToFile(String filename) {
    log.append("Ran writeImage\n");
  }

  @Override
  public BufferedImage getBufferedImage() {
    log.append("Ran getBufferedImage\n");
    return null;
  }

  @Override
  public List<String[]> getDmc() {
    log.append("Ran getDmc\n");
    return null;
  }

  @Override
  public void removePixel(int x, int y) {
    log.append("Ran removePixel\n");
  }

  @Override
  public void swapPixel(int x, int y, int[] rgb) {
    log.append("Ran swapPixel\n");
  }

  @Override
  public void restrictColor(List<int[]> colors) {
    log.append("Ran restrictColor\n");
  }
}
