package images.view;

import images.controller.Features;

/**
 * Menu bar interface.
 */
public interface MenuInterface {
  /**
   * Connects the menu to the feature object, which tends to be a controller.
   *
   * @param controller The controller that connects to the menu.
   */
  void setFeatures(Features controller);
}
