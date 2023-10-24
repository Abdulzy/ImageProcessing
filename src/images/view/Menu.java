package images.view;

import images.controller.Features;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/** The menu bar of the view. */
public class Menu extends JMenuBar implements MenuInterface {

  private final JMenuItem load;
  private final JMenuItem save;
  private final JMenuItem applyBlur;
  private final JMenuItem applySharpen;
  private final JMenuItem applyGreyScale;
  private final JMenuItem applySepia;
  private final JMenuItem applyDither;
  private final JMenuItem applyPixelate;
  private final JMenuItem applyMosaic;
  private final JMenuItem applyCrossStitch;
  private final JMenuItem applyRemove;
  private final JMenuItem applySwap;
  private final JMenuItem applyPallete;
  private final JMenuItem applyBatch;

  /** Constructor of the class. */
  public Menu() {
    super();
    setBackground(Color.darkGray);
    JMenu file = new JMenu("File");
    file.setForeground(Color.cyan);
    this.add(file);
    load = new JMenuItem("Load Image");
    save = new JMenuItem("Save Image");
    file.add(load);
    file.add(save);

    JMenu blur = new JMenu("Blur");
    blur.setForeground(Color.cyan);
    this.add(blur);
    applyBlur = new JMenuItem("Apply blur");
    blur.add(applyBlur);

    JMenu sharpen = new JMenu("Sharpen");
    sharpen.setForeground(Color.cyan);
    this.add(sharpen);
    applySharpen = new JMenuItem("Apply sharpen");
    sharpen.add(applySharpen);

    JMenu greyScale = new JMenu("Greyscale");
    greyScale.setForeground(Color.cyan);
    this.add(greyScale);
    applyGreyScale = new JMenuItem("Apply greyScale");
    greyScale.add(applyGreyScale);

    JMenu sepia = new JMenu("Sepia");
    sepia.setForeground(Color.cyan);
    this.add(sepia);
    applySepia = new JMenuItem("Apply sepia");
    sepia.add(applySepia);

    JMenu dither = new JMenu("Dither");
    dither.setForeground(Color.cyan);
    this.add(dither);
    applyDither = new JMenuItem("Apply dither");
    dither.add(applyDither);

    JMenu mosaic = new JMenu("Mosaic");
    mosaic.setForeground(Color.cyan);
    this.add(mosaic);
    applyMosaic = new JMenuItem("Apply mosaic");
    mosaic.add(applyMosaic);

    JMenu pixelate = new JMenu("Pixelate");
    pixelate.setForeground(Color.cyan);
    this.add(pixelate);
    applyPixelate = new JMenuItem("Apply pixelate");
    pixelate.add(applyPixelate);

    JMenu crossStitch = new JMenu("CrossStitch");
    crossStitch.setForeground(Color.cyan);
    this.add(crossStitch);
    applyCrossStitch = new JMenuItem("Apply crossStitch");
    crossStitch.add(applyCrossStitch);
    applyRemove = new JMenuItem("Remove color");
    crossStitch.add(applyRemove);
    applySwap = new JMenuItem("Swap colors");
    crossStitch.add(applySwap);
    applyPallete = new JMenuItem("Restrict palette");
    crossStitch.add(applyPallete);

    JMenu batch = new JMenu("Batch processing");
    batch.setForeground(Color.cyan);
    this.add(batch);
    applyBatch = new JMenuItem("Run batch");
    batch.add(applyBatch);
  }

  @Override
  public void setFeatures(Features controller) {
    load.addActionListener(e -> controller.loadImage());
    applyBlur.addActionListener(e -> controller.blur());
    applySharpen.addActionListener(e -> controller.sharpen());
    applyGreyScale.addActionListener(e -> controller.greyScale());
    applySepia.addActionListener(e -> controller.sepia());
    applyDither.addActionListener(e -> controller.reduceColourDensity());
    applyMosaic.addActionListener(e -> controller.mosaic());
    applyPixelate.addActionListener(e -> controller.pixelation());
    applyCrossStitch.addActionListener(e -> controller.patternGeneration());
    applyRemove.addActionListener(e -> controller.remove());
    applySwap.addActionListener(e -> controller.swap());
    applyPallete.addActionListener(e -> controller.palette());
    save.addActionListener(e -> controller.saveImage());
    applyBatch.addActionListener(e -> controller.batchProcessing());
  }
}
