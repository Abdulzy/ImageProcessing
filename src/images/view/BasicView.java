package images.view;

import images.controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Implementation of the view. */
public class BasicView extends JFrame implements ImageView {
  private final PicturePane imagePanel;
  private final JPanel legend;
  private final JScrollPane legendPane;
  private final Menu menu;
  private final JButton runBatch;
  private final JButton exitBatch;
  private final JTextArea batchArea;
  private final JTextArea batchReport;
  private final JPanel superBatch;
  private final JPanel superInteractive;
  private final JTextArea top;
  private final JButton swapColor;
  private final JButton removeColor;
  private final JButton useColor;
  private final JPanel color;
  private int[] colorLegend;
  private final List<int[]> colorOptions;

  /** Constructor for the class. */
  public BasicView() {
    super("Image Modifier");
    this.setSize(1000, 650);
    setLocation(50, 50);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    imagePanel = new PicturePane();
    imagePanel.setBackground(Color.lightGray);

    top =
        new JTextArea(
            "Welcome to an interactive GUI, "
                + "you can either load an image and process it with the menu bar"
                + " or you can switch to batch file mode by clicking on run "
                + "batch under batch processing");
    top.setEditable(false);
    this.add(top, BorderLayout.NORTH);

    JPanel batchPanel = new JPanel();
    batchPanel.setLayout(new BorderLayout());
    JLabel batchInstruction = new JLabel("Write your batch script:");
    batchPanel.add(batchInstruction, BorderLayout.NORTH);
    batchArea = new JTextArea(2, 10);
    batchArea.setEditable(true);
    JScrollPane scrollPane = new JScrollPane(batchArea);
    batchPanel.add(scrollPane, BorderLayout.CENTER);
    runBatch = new JButton("run");
    runBatch.setActionCommand("run batch");
    exitBatch = new JButton("exit");
    exitBatch.setActionCommand("exit batch");
    JPanel command = new JPanel(new FlowLayout());
    command.add(runBatch);
    command.add(exitBatch);
    batchPanel.add(command, BorderLayout.SOUTH);
    batchPanel.setPreferredSize(new Dimension(300, 400));

    batchReport = new JTextArea("batch report would be displayed here.");
    batchReport.setEditable(false);
    JScrollPane reportPane = new JScrollPane(batchReport);
    reportPane.setPreferredSize(new Dimension(250, 400));

    superBatch = new JPanel();
    superBatch.setBackground(Color.darkGray);
    superBatch.add(batchPanel);
    superBatch.add(reportPane);
    superBatch.setVisible(false);

    legend = new JPanel();
    legend.setLayout(new BoxLayout(legend, BoxLayout.Y_AXIS));
    legend.setBackground(Color.lightGray);
    legendPane = new JScrollPane(legend);
    legendPane.setPreferredSize(new Dimension(110, 400));
    legendPane.setVisible(false);

    swapColor = new JButton("swap colors");
    swapColor.setActionCommand("swap colors");
    removeColor = new JButton("remove color");
    removeColor.setActionCommand("remove color");
    useColor = new JButton("restrict palette");
    useColor.setActionCommand("restrict palette");
    color = new JPanel(new FlowLayout());
    color.setBackground(Color.darkGray);
    color.add(removeColor);
    color.add(swapColor);
    color.add(useColor);
    color.setVisible(false);
    this.add(color, BorderLayout.SOUTH);

    superInteractive = new JPanel();
    superInteractive.add(imagePanel);
    superInteractive.add(legendPane);
    superInteractive.setBackground(Color.darkGray);

    JPanel display = new JPanel(new FlowLayout());
    display.add(superBatch);
    display.add(superInteractive);
    display.setBackground(Color.darkGray);
    this.add(display, BorderLayout.CENTER);

    menu = new Menu();
    this.setJMenuBar(menu);

    colorLegend = new int[] {-1, -1, -1};
    colorOptions = new ArrayList<>();
  }

  @Override
  public void start() {
    this.setVisible(true);
  }

  @Override
  public String getImage() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return "";
    }
  }

  @Override
  public void setImage(BufferedImage image) {
    colorOptions.clear();
    colorLegend = new int[]{0, 0, 0};
    top.setVisible(false);
    legendPane.setVisible(false);
    color.setVisible(false);
    imagePanel.setPicture(new ImageIcon(image));
    repaint();
    revalidate();
  }

  @Override
  public void setFeatures(Features controller) {
    runBatch.addActionListener(l -> controller.runBatch(batchArea.getText()));
    exitBatch.addActionListener(l -> controller.exitBatch());
    swapColor.addActionListener(l -> controller.swapHelp());
    removeColor.addActionListener(l -> controller.removeHelp());
    useColor.addActionListener(l -> controller.paletteHelp());
    menu.setFeatures(controller);
  }

  @Override
  public void displayError(String error, String errorLocation) {
    errorLocation = "Error from " + errorLocation;
    JOptionPane.showMessageDialog(this, error, errorLocation, JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public int getInput(String question) {
    String response;
    response = JOptionPane.showInputDialog(question);
    if (response.trim().isEmpty()) {
      return -1;
    }
    return Integer.parseInt(response);
  }

  @Override
  public String saveImage() {
    JFileChooser fc = new JFileChooser();
    int result = fc.showSaveDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      return file.getAbsolutePath();
    } else {
      return "";
    }
  }

  @Override
  public void showLegend(List<String[]> dmc) {
    legend.removeAll();
    for (String[] strings : dmc) {
      JButton buttonColor = new JButton(strings[1] + ": DMC- " + strings[0]);
      int red = Integer.parseInt(strings[2]);
      int green = Integer.parseInt(strings[3]);
      int blue = Integer.parseInt(strings[4]);
      buttonColor.setBackground(new Color(red, green, blue));
      buttonColor.addMouseListener(
          new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
              JOptionPane.showMessageDialog(
                  null, "Mouse Clicked: (" + red + ", " + green + ", " + blue + ")");
              colorLegend[0] = red;
              colorLegend[1] = green;
              colorLegend[2] = blue;
              colorOptions.add(new int[]{red, blue, green});
            }
          });
      legend.add(buttonColor);
    }
    legend.repaint();
    legend.revalidate();
    legendPane.setVisible(true);
    color.setVisible(true);
    repaint();
    revalidate();
  }

  @Override
  public void setBatchReport(String s) {
    batchReport.setText(s);
  }

  @Override
  public void displayLog(String s) {
    JOptionPane.showMessageDialog(this, s);
  }

  @Override
  public void startBatch() {
    legendPane.setVisible(false);
    menu.setVisible(false);
    top.setVisible(false);
    color.setVisible(false);
    superInteractive.setVisible(false);
    superBatch.setVisible(true);
  }

  @Override
  public void endBatch() {
    menu.setVisible(true);
    top.setVisible(true);
    superInteractive.setVisible(true);
    superBatch.setVisible(false);
  }

  @Override
  public void removeHelp() {
    imagePanel.getPixel();
    JOptionPane.showMessageDialog(
        this,
        "Click the color on the picture you want removed"
            + "\n After that go to the menu bar and click run \"Remove color\"");
  }

  @Override
  public void swapHelp() {
    imagePanel.getPixel();
    JOptionPane.showMessageDialog(
        this,
        "Click the color on the picture you want swapped"
            + "\n Click the color on the legend you want swapped."
            + "\n After that go to the menu bar and click run \"Swap color\"");
  }

  @Override
  public void paletteHelp() {
    JOptionPane.showMessageDialog(
        this,
        " Click the colors on the legend you want "
            + "included in your new image."
            + "\n After that go to the menu bar and click run \"Restrict Palette\"");
  }

  @Override
  public int imagePixelX() {
    return imagePanel.getxPoint();
  }

  @Override
  public int imagePixelY() {
    return imagePanel.getyPoint();
  }

  @Override
  public int[] legendColor() {
    return colorLegend;
  }

  @Override
  public List<int[]> selectedColors() {
    return colorOptions;
  }
}
