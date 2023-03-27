package view;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import controller.ControllerFeatures;
import model.colors.ColorModel;
import model.images.ImageModel;
import model.pixels.Pixel;

public class GUIView extends JFrame implements CollageGUIView<Pixel> {
  private JScrollPane compositeImage;
  private JPanel mainPanel, imagePanel;
  private JButton newProjectButton;
  private JTextField widthField, heightField;
  private JLabel errorMessage;

  public GUIView() {
    this.setSize(new Dimension(1000, 1000));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

    this.renderNewProjectComponent();

    this.imagePanel = new JPanel();
    this.compositeImage = new JScrollPane();
    this.imagePanel.add(this.compositeImage);
    this.mainPanel.add(this.imagePanel);

    this.errorMessage = new JLabel("");
    this.errorMessage.setForeground(Color.RED);
    this.mainPanel.add(this.errorMessage);

    this.add(this.mainPanel);

    this.pack();
    this.setVisible(true);
  }

  private void renderNewProjectComponent() {
    JPanel newProjectPanel = new JPanel();
    newProjectPanel.setLayout(new BoxLayout(newProjectPanel, BoxLayout.PAGE_AXIS));

    JLabel createProjectLabel = new JLabel("Create New Project?");

    JPanel fieldPanel = new JPanel();
    fieldPanel.setLayout(new FlowLayout());

    JLabel width = new JLabel("Width:");
    JLabel height = new JLabel("Height:");
    this.widthField = new JTextField(5);
    this.heightField = new JTextField(5);

    fieldPanel.add(width);
    fieldPanel.add(widthField);
    fieldPanel.add(height);
    fieldPanel.add(heightField);

    this.newProjectButton = new JButton("Create Project");

    newProjectPanel.add(createProjectLabel);
    newProjectPanel.add(fieldPanel);
    newProjectPanel.add(newProjectButton);

    this.mainPanel.add(newProjectPanel);
  }

  @Override
  public void renderImage(ImageModel<Pixel> image) {
    Image compositeImage = this.createImageFromScratch(image);

    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(compositeImage));

    this.imagePanel.remove(this.compositeImage);
    this.compositeImage = new JScrollPane(imageLabel);
    this.compositeImage.setPreferredSize(new Dimension(500, 500));
    this.imagePanel.add(this.compositeImage);
  }

  private Image createImageFromScratch(ImageModel<Pixel> compositeImage) {
    BufferedImage bufferedImage = new BufferedImage(compositeImage.getImageWidth(),
            compositeImage.getImageHeight(),
            BufferedImage.TYPE_INT_ARGB);

    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++) {
        ColorModel currentPixelColor = compositeImage.getPixelAtCoord(y, x).getColor();
        int r = (int) currentPixelColor.getRedComponent();
        int g = (int) currentPixelColor.getGreenComponent();
        int b = (int) currentPixelColor.getBlueComponent();
        int a = (int) currentPixelColor.getAlphaComponent();

        int argb = a << 24;
        argb |= r << 16;
        argb |= g << 8;
        argb |= b;
        bufferedImage.setRGB(x, y, argb);
      }
    }
    return bufferedImage;
  }

  @Override
  public void renderErrorMessage(String message) {
    this.errorMessage.setText(message);
  }

  @Override
  public void addFeatures(ControllerFeatures features) {
    this.newProjectButton.addActionListener(evt -> {
      try {
        int canvasWidth = Integer.parseInt(this.widthField.getText(), 10);
        int canvasHeight = Integer.parseInt(this.heightField.getText(), 10);
        features.makeNewProject(canvasWidth, canvasHeight);
      } catch (NumberFormatException e) {
        this.renderErrorMessage("Please enter a number greater than zero.");
      }
    });
  }

  @Override
  public void refresh(ImageModel<Pixel> compositeImage) {
    this.renderErrorMessage("");
    this.renderImage(compositeImage);
    this.setVisible(true);
//    this.repaint();
    // Update the list of layers being displayed
    // Update the image being displayed
    // Get rid of error messages (when they perform any operation successfully, remove the message)
  }
}
