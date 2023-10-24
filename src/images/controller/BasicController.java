package images.controller;

import images.model.ImageModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The controller that communicates to the image Model.
 */
public class BasicController implements Controllers {
  private final Readable in;
  private final Appendable out;

  /**
   * Default Constructor.
   *
   * @param in  the data or commands being passed in.
   * @param out the response or output from the controller.
   */
  public BasicController(Readable in, Appendable out) {
    Objects.requireNonNull(in, "in cannot be null");
    Objects.requireNonNull(out, "out cannot be null");
    this.in = in;
    this.out = out;
  }

  @Override
  public void start(ImageModel model) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("model cant be null");
    }
    Scanner effect = new Scanner(this.in);
    out.append(script(effect, model));
  }

  /**
   * Runs a script that utilizes an image model.
   *
   * @param script A scanner input that contains text to be read.
   * @param model an image model
   * @return a log of all that happened.
   */
  public static String script(Scanner script, ImageModel model) {
    StringBuilder log = new StringBuilder();
    while (script.hasNextLine()) {
      List<String> pixels = new ArrayList<>(Arrays.asList(script.nextLine().split(" ")));
      switch (pixels.get(0).trim().toLowerCase()) {
        case "load":
          try {
            if (pixels.size() > 1) {
              if (pixels.get(1).trim().toLowerCase().contains("png")) {
                model.readImage(pixels.get(1).trim());
                log.append(pixels.get(0)).append(" ran successfully\n");
              }
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't load\n");
            }
          } catch (IOException | IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "save":
          try {
            if (pixels.size() > 1) {
              if (pixels.get(1).trim().toLowerCase().contains("png")) {
                model.writeImage(pixels.get(1).trim());
                log.append(pixels.get(0)).append(" ran successfully\n");
              } else if (pixels.get(1).trim().toLowerCase().contains("txt")) {
                model.writeToFile(pixels.get(1).trim());
                log.append(pixels.get(0)).append(" ran successfully\n");
              }
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't save\n");
            }
          } catch (IOException | IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "blur":
          try {
            if (pixels.size() > 1) {
              model.blur(1);
              log.append(pixels.get(0)).append(" ran successfully\n");
            } else {
              model.blur(Integer.parseInt(pixels.get(1)));
              log.append(pixels.get(0)).append(" ran successfully but didn't blur\n");
            }
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "sharpen":
          try {
            if (pixels.size() > 1) {
              model.sharpen(Integer.parseInt(pixels.get(1)));
              log.append(pixels.get(0)).append(" ran successfully\n");
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't sharpen\n");
            }
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "grey":
          try {
            model.greyScale();
            log.append(pixels.get(0)).append(" ran successfully\n");
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "sepia":
          try {
            model.sepia();
            log.append(pixels.get(0)).append(" ran successfully\n");
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "dither":
          try {
            if (pixels.size() > 1) {
              model.reduceColourDensity(Integer.parseInt(pixels.get(1)));
              log.append(pixels.get(0)).append(" ran successfully\n");
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't dither\n");
            }
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "mosaic":
          try {
            if (pixels.size() > 1) {
              model.mosaic(Integer.parseInt(pixels.get(1)));
              log.append(pixels.get(0)).append(" ran successfully\n");
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't mosaic\n");
            }
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "pixelate":
          try {
            if (pixels.size() > 1) {
              model.pixelation(Integer.parseInt(pixels.get(1)));
              log.append(pixels.get(0)).append(" ran successfully\n");
            } else {
              log.append(pixels.get(0)).append(" ran successfully but didn't pixelate\n");
            }
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        case "pattern":
          try {
            model.patternGeneration();
            log.append(pixels.get(0)).append(" ran successfully\n");
          } catch (IllegalArgumentException e) {
            log.append(e).append("\n").append(pixels.get(0)).append(" did not not run\n");
          }
          break;
        default:
          log.append(pixels.get(0)).append(" is an unknown command\n");
          break;
      }
    }
    log.append("program is done running\n");
    return log.toString();
  }
}
