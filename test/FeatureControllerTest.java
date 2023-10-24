import images.controller.FeatureController;
import images.controller.Features;
import images.model.ImageModel;
import images.view.ImageView;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** The test for the feature controller. */
public class FeatureControllerTest {
  Features controller;
  ImageView view;
  ImageModel model;
  StringBuilder logModel;
  StringBuilder logView;

  /** Initialising the objects that would be used. */
  @Before
  public void setUp() {
    logModel = new StringBuilder();
    logView = new StringBuilder();
    model = new MockModel(logModel);
    controller = new FeatureController(model);
    view = new MockView(logView);
    controller.setView(view);
  }

  @Test
  public void setView() {
    // Since i set the view at the @Before the log has been updated
    assertEquals("Ran setFeatures\n" + "Ran start\n", logView.toString());
  }

  @Test
  public void blur() {
    controller.blur();
    assertEquals("Ran blur\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getInput\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void sharpen() {
    controller.sharpen();
    assertEquals("Ran sharpen\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getInput\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void sepia() {
    controller.sepia();
    assertEquals("Ran sepia\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran setImage\n", logView.toString());
  }

  @Test
  public void greyScale() {
    controller.greyScale();
    assertEquals("Ran greyScale\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran setImage\n", logView.toString());
  }

  @Test
  public void reduceColourDensity() {
    controller.reduceColourDensity();
    assertEquals("Ran reduceColourDensity\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getInput\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void mosaic() {
    controller.mosaic();
    assertEquals("Ran mosaic\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getInput\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void pixelation() {
    controller.pixelation();
    assertEquals("Ran pixelation\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getInput\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void loadImage() {
    controller.loadImage();
    assertEquals("Ran readImage\n" + "Ran getBufferedImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran getImage\n" + "Ran setImage\n",
        logView.toString());
  }

  @Test
  public void saveImage() {
    controller.saveImage();
    assertEquals("Ran writeImage\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran saveImage\n" + "Ran displayLog\n",
        logView.toString());
  }

  @Test
  public void batchProcessing() {
    controller.batchProcessing();
    assertEquals("", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran startBatch\n" + "Ran displayLog\n",
        logView.toString());
  }

  @Test
  public void runBatch() {
    controller.runBatch("hello");
    assertEquals("", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran setBatchReport\n", logView.toString());
  }

  @Test
  public void exitBatch() {
    controller.exitBatch();
    assertEquals("", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran endBatch\n", logView.toString());
  }

  @Test
  public void patternGeneration() {
    controller.patternGeneration();
    assertEquals(
        "Ran patternGeneration\n" + "Ran getBufferedImage\n" + "Ran getDmc\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n" + "Ran start\n" + "Ran setImage\n" + "Ran showLegend\n",
        logView.toString());
  }

  @Test
  public void remove() {
    controller.remove();
    assertEquals(
        "Ran removePixel\n" + "Ran getBufferedImage\n" + "Ran getDmc\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n"
            + "Ran start\n"
            + "Ran imagePixelY\n"
            + "Ran imagePixelX\n"
            + "Ran setImage\n"
            + "Ran showLegend\n"
            + "Ran displayLog\n",
        logView.toString());
  }

  @Test
  public void swap() {
    controller.swap();
    assertEquals(
        "Ran swapPixel\n" + "Ran getBufferedImage\n" + "Ran getDmc\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n"
            + "Ran start\n"
            + "Ran imagePixelY\n"
            + "Ran imagePixelX\n"
            + "Ran legendColor\n"
            + "Ran setImage\n"
            + "Ran showLegend\n"
            + "Ran displayLog\n",
        logView.toString());
  }

  @Test
  public void palette() {
    controller.palette();
    assertEquals(
        "Ran restrictColor\n" + "Ran getBufferedImage\n" + "Ran getDmc\n", logModel.toString());
    assertEquals(
        "Ran setFeatures\n"
            + "Ran start\n"
            + "Ran selectedColors\n"
            + "Ran setImage\n"
            + "Ran showLegend\n",
        logView.toString());
  }

  @Test
  public void removeHelp() {
    controller.removeHelp();
    assertEquals("", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran removeHelp\n", logView.toString());
  }

  @Test
  public void swapHelp() {
    controller.swapHelp();
    assertEquals("", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran swapHelp\n", logView.toString());
  }

  @Test
  public void paletteHelp() {
    controller.paletteHelp();
    assertEquals("", logModel.toString());
    assertEquals("Ran setFeatures\n" + "Ran start\n" + "Ran paletteHelp\n", logView.toString());
  }
}
