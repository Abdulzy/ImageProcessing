package images.controller;

import images.model.ImageModel;
import java.io.IOException;

/** The controller interface. */
public interface Controllers {

  /**
   * Starts the controller and runs the program.
   *
   * @param image the image model
   * @throws IOException If the file is not found.
   * @throws IllegalArgumentException If the image is null.
   */
  void start(ImageModel image) throws IOException;
}
