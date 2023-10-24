import images.controller.BasicController;
import images.controller.Controllers;
import images.model.ImageModel;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** The test for the controller. */
public class BasicControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void nullModel() throws IOException {
    Appendable out = new StringBuffer();
    Readable in =
        new StringReader(
            "load x\nsave x\nblur\nsharpen\ndither 1"
                + "\ndither\ngrey\nsepia\nmosaic 10\nmosaic\npixelate 5\npixelate\n"
                + "pattern 5\njohn\n");
    Controllers control = new BasicController(in, out);
    control.start(null);
  }

  @Test
  public void testController() throws IOException {
    Appendable out = new StringBuffer();
    Readable in =
        new StringReader(
            "load a.png\nsave a.png\nsave a.txt\nsave\nblur\nsharpen"
                + "\ndither 1\ndither\ngrey\nsepia\nmosaic 10\nmosaic\npixelate 5"
                + "\npixelate\npattern 5\njohn\n");
    StringBuilder log = new StringBuilder();
    ImageModel model = new MockModel(log);
    Controllers control = new BasicController(in, out);
    control.start(model);
    assertEquals(
        "Ran readImage\n"
            + "Ran writeImage\n"
            + "Ran writeImage\n"
            + "Ran blur\n"
            + "Ran sharpen\n"
            + "Ran reduceColourDensity\n"
            + "Ran greyScale\n"
            + "Ran sepia\n"
            + "Ran mosaic\n"
            + "Ran pixelation\n"
            + "Ran patternGeneration\n",
        log.toString());
    assertEquals(
        "load ran successfully\n"
            + "save ran successfully\n"
            + "save ran successfully\n"
            + "save ran successfully but didn't save\n"
            + "blur ran successfully\n"
            + "sharpen ran successfully\n"
            + "dither ran successfully\n"
            + "dither ran successfully but didn't dither\n"
            + "grey ran successfully\n"
            + "sepia ran successfully\n"
            + "mosaic ran successfully\n"
            + "mosaic ran successfully but didn't mosaic\n"
            + "pixelate ran successfully\n"
            + "pixelate ran successfully but didn't pixelate\n"
            + "pattern ran successfully\n"
            + "john is an unknown command\n"
            + "program is done running\n",
        out.toString());
  }

  @Test
  public void testControllerException() throws IOException {
    Appendable out = new StringBuffer();
    Readable in =
        new StringReader(
            "load a.png\nsave a.png\nsave a.txt\nblur\nsharpen"
                + "\ndither 1\ngrey\nsepia\nmosaic 10\npixelate 5"
                + "\npattern 5\njohn\n");
    StringBuilder log = new StringBuilder();
    ImageModel model = new MockModel(log);
    Controllers control = new BasicController(in, out);
    control.start(model);
    assertEquals(
        "Ran readImage\n"
            + "Ran writeImage\n"
            + "Ran writeImage\n"
            + "Ran blur\n"
            + "Ran sharpen\n"
            + "Ran reduceColourDensity\n"
            + "Ran greyScale\n"
            + "Ran sepia\n"
            + "Ran mosaic\n"
            + "Ran pixelation\n"
            + "Ran patternGeneration\n",
        log.toString());
    assertEquals(
        "load ran successfully\n"
            + "save ran successfully\n"
            + "save ran successfully\n"
            + "blur ran successfully\n"
            + "sharpen ran successfully\n"
            + "dither ran successfully\n"
            + "grey ran successfully\n"
            + "sepia ran successfully\n"
            + "mosaic ran successfully\n"
            + "pixelate ran successfully\n"
            + "pattern ran successfully\n"
            + "john is an unknown command\n"
            + "program is done running\n",
        out.toString());
  }
}
