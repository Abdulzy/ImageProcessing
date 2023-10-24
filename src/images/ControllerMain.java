package images;

import images.controller.BasicController;
import images.controller.Controllers;
import images.model.BasicModel;
import images.model.ImageModel;

import java.io.FileReader;
import java.io.IOException;

/**
 * Driver class of my project. To show how my code works.
 */
public class ControllerMain {

  /**
   * Main method to help run the code.
   *
   * @param args args
   * @throws IOException if the file isn't found
   */
  public static void main(String[] args) throws IOException {

    Readable reader = new FileReader("thoughts.txt");
    ImageModel model = new BasicModel();
    Controllers control = new BasicController(reader, System.out);
    control.start(model);
  }
}
