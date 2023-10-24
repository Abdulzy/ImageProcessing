package images.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/** A scrollable pane implementation. */
public class PicturePane extends JPanel implements ItemListener {
  JScrollPane pictureScrollPane;
  ScrollablePicture picture;
  private int xPoint;
  private int yPoint;

  /** The constructor. */
  public PicturePane() {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    picture = null;
    pictureScrollPane = new JScrollPane(null);
    pictureScrollPane.setPreferredSize(new Dimension(500, 500));
    pictureScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.black));
    add(pictureScrollPane);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    xPoint = -1;
    yPoint = -1;
  }

  /**
   * Sets the image that would be displayed on the pane.
   *
   * @param i The image that would be displayed.
   */
  public void setPicture(ImageIcon i) {
    picture = new ScrollablePicture(i, 10);
    pictureScrollPane.setViewportView(picture);
  }

  /** Enables the picture to be clicked. */
  public void getPixel() {
    picture.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent event) {
            JOptionPane.showMessageDialog(
                null, "Mouse Clicked: (" + event.getY() + ", " + event.getX() + ")");
            xPoint = event.getX();
            yPoint = event.getY();
          }
        });
  }

  public int getxPoint() {
    return xPoint;
  }

  public int getyPoint() {
    return yPoint;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    picture.setMaxUnitIncrement(10);
  }
}
