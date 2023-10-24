package images.controller;

import images.model.ImageModel;
import images.view.ImageView;
import java.io.IOException;
import java.util.Scanner;

/**
 * The controller now implements the Features interface. This means each of those functions will
 * give control to the controller.
 */
public class FeatureController implements Features {

  private final ImageModel model;
  private ImageView view;

  /**
   * Constructor.
   *
   * @param m the model to use
   */
  public FeatureController(ImageModel m) {
    model = m;
  }

  @Override
  public void setView(ImageView imageView) {
    view = imageView;
    view.setFeatures(this);
    view.start();
  }

  @Override
  public void blur() {
    try {
      model.blur(view.getInput("How many times do you want to blur"));
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "blur");
    }
  }

  @Override
  public void sharpen() {
    try {
      model.sharpen(view.getInput("How many times do you want to sharpen"));
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "sharpen");
    }
  }

  @Override
  public void sepia() {
    try {
      model.sepia();
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "sepia");
    }
  }

  @Override
  public void greyScale() {
    try {
      model.greyScale();
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "greyScale");
    }
  }

  @Override
  public void reduceColourDensity() {
    try {
      model.reduceColourDensity(view.getInput("How many colors do you want for each RGB?"));
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "dithering");
    }
  }

  @Override
  public void mosaic() {
    try {
      model.mosaic(view.getInput("How many seeds do you want for the mosaic effect?"));
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "mosaic");
    }
  }

  @Override
  public void pixelation() {
    try {
      model.pixelation(view.getInput("How many pixels do you want across the image"));
      view.setImage(model.getBufferedImage());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "pixelation");
    }
  }

  @Override
  public void loadImage() {
    try {
      model.readImage(view.getImage());
      view.setImage(model.getBufferedImage());
    } catch (IOException | IllegalArgumentException e) {
      view.displayError(e.getMessage(), "load image");
    }
  }

  @Override
  public void saveImage() {
    try {
      model.writeImage(view.saveImage() + ".png");
      view.displayLog("Done saving Image");
    } catch (IOException | IllegalArgumentException e) {
      view.displayError(e.getMessage(), "save image");
    }
  }

  @Override
  public void batchProcessing() {
    view.startBatch();
    view.displayLog("Batch processing mode is active");
  }

  @Override
  public void runBatch(String batch) {
    Scanner script = new Scanner(batch);
    view.setBatchReport(BasicController.script(script, model));
  }

  @Override
  public void exitBatch() {
    view.endBatch();
  }

  @Override
  public void patternGeneration() {
    try {
      model.patternGeneration();
      view.setImage(model.getBufferedImage());
      view.showLegend(model.getDmc());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "pattern generation");
    }
  }

  @Override
  public void remove() {
    try {
      model.removePixel(view.imagePixelY(), view.imagePixelX());
      view.setImage(model.getBufferedImage());
      view.showLegend(model.getDmc());
      view.displayLog("removed color from legend");
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "remove");
    }
  }

  @Override
  public void swap() {
    try {
      model.swapPixel(view.imagePixelY(), view.imagePixelX(), view.legendColor());
      view.setImage(model.getBufferedImage());
      view.showLegend(model.getDmc());
      view.displayLog("swapped color");
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "swap");
    }
  }

  @Override
  public void palette() {
    try {
      model.restrictColor(view.selectedColors());
      view.setImage(model.getBufferedImage());
      view.showLegend(model.getDmc());
    } catch (IllegalArgumentException e) {
      view.displayError(e.getMessage(), "swap");
    }
  }

  @Override
  public void removeHelp() {
    view.removeHelp();
  }

  @Override
  public void swapHelp() {
    view.swapHelp();
  }

  @Override
  public void paletteHelp() {
    view.paletteHelp();
  }
}
