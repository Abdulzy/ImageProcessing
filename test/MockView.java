import images.controller.Features;
import images.view.ImageView;
import java.awt.image.BufferedImage;
import java.util.List;

/** The mock view for testing the controller. */
public class MockView implements ImageView {

  private final StringBuilder log;

  public MockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void start() {
    log.append("Ran start\n");
  }

  @Override
  public String getImage() {
    log.append("Ran getImage\n");
    return null;
  }

  @Override
  public void setImage(BufferedImage image) {
    log.append("Ran setImage\n");
  }

  @Override
  public void setFeatures(Features f) {
    log.append("Ran setFeatures\n");
  }

  @Override
  public void displayError(String error, String errorLocation) {
    log.append("Ran displayError\n");
  }

  @Override
  public int getInput(String question) {
    log.append("Ran getInput\n");
    return 0;
  }

  @Override
  public String saveImage() {
    log.append("Ran saveImage\n");
    return null;
  }

  @Override
  public void setBatchReport(String report) {
    log.append("Ran setBatchReport\n");
  }

  @Override
  public void startBatch() {
    log.append("Ran startBatch\n");
  }

  @Override
  public void endBatch() {
    log.append("Ran endBatch\n");
  }

  @Override
  public void displayLog(String report) {
    log.append("Ran displayLog\n");
  }

  @Override
  public void showLegend(List<String[]> dmc) {
    log.append("Ran showLegend\n");
  }

  @Override
  public void paletteHelp() {
    log.append("Ran paletteHelp\n");
  }

  @Override
  public void removeHelp() {
    log.append("Ran removeHelp\n");
  }

  @Override
  public void swapHelp() {
    log.append("Ran swapHelp\n");
  }

  @Override
  public int imagePixelX() {
    log.append("Ran imagePixelX\n");
    return 0;
  }

  @Override
  public int imagePixelY() {
    log.append("Ran imagePixelY\n");
    return 0;
  }

  @Override
  public int[] legendColor() {
    log.append("Ran legendColor\n");
    return new int[0];
  }

  @Override
  public List<int[]> selectedColors() {
    log.append("Ran selectedColors\n");
    return null;
  }
}
