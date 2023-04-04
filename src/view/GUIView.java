package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ControllerFeatures;
import model.colors.ColorModel;
import model.images.ImageModel;
import model.pixels.Pixel;
import utils.Util;

/**
 * Represents a view that implements a GUI. The view is used for displaying the commands
 * that are supported by a collage project controller.
 */
public class GUIView extends JFrame implements CollageGUIView<Pixel> {
  private JScrollPane compositeImage;
  private final JPanel mainPanel;
  private JPanel layerDropdownContainer, layerDropdownPanel, imagePanel;
  private JButton newProjectButton, showLayerFilterButton, openFileButton,
          confirmImageUploadButton, confirmSetLayerFilterButton, addLayerButton,
          uploadProjectButton, saveProjectButton, saveImageButton;
  private JTextField widthField, heightField, imageRowDisplacementField, imageColDisplacementField,
          layerNameField;
  private JLabel errorMessage, layerDropdownMessage, layerFilterMessage, selectedFileMessage;
  private JComboBox<String> layerDropdown, filterDropdown;
  private Map<String, String> layerNameToFilterName;
  private String selectedLayer, selectedFilter, imageFilePath;

  /**
   * Constructor for a GUI view shows the composite image that the user is working on, as
   * well as all the commands that a user can interact with.
   */
  public GUIView() {
    this.setSize(new Dimension(1000, 1000));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.mainPanel = new JPanel();
    this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));

    this.selectedLayer = "default-layer";
    this.selectedFilter = "normal";
    this.imageFilePath = "";

    this.renderNewProjectComponent();
    this.renderDefaultImageDisplayComponent();

    this.renderErrorMessageDisplayComponent();
    this.renderAddLayerComponent();

    this.renderDefaultFilterLayerContainerComponent();
    this.renderAddImageComponent();
    this.renderProjectCommandComponent();

    this.add(this.mainPanel);
    this.pack();
    this.setVisible(true);
  }

  /**
   * Creates a horizontally flowing component that holds components for
   * allowing a user to specify the width and height of a new collage project to create.
   */
  private void renderNewProjectComponent() {
    JPanel newProjectPanel = new JPanel();
    newProjectPanel.setLayout(new FlowLayout());

    JLabel createProjectLabel = new JLabel("Create New Project?");
    JLabel width = new JLabel("Width:");
    JLabel height = new JLabel("Height:");
    this.widthField = new JTextField(5);
    this.heightField = new JTextField(5);

    newProjectPanel.add(createProjectLabel);
    newProjectPanel.add(width);
    newProjectPanel.add(this.widthField);
    newProjectPanel.add(height);
    newProjectPanel.add(this.heightField);
    this.newProjectButton = new JButton("Create Project");
    newProjectPanel.add(this.newProjectButton);

    this.mainPanel.add(newProjectPanel);
  }

  /**
   * Renders a component that contains the composite image to display to the user. If the
   * composite image is too big, scrollbars are added to the pane.
   */
  private void renderDefaultImageDisplayComponent() {
    this.imagePanel = new JPanel();
    this.compositeImage = new JScrollPane();
    this.imagePanel.add(this.compositeImage);
    this.mainPanel.add(this.imagePanel);
  }

  /**
   * Renders a component that displays error messages to the user.
   */
  private void renderErrorMessageDisplayComponent() {
    this.errorMessage = new JLabel("");
    this.errorMessage.setForeground(Color.RED);
    this.mainPanel.add(this.errorMessage);
  }

  /**
   * Creates a horizontally flowing component that holds components that allow a
   * user to add a new layer to the current project.
   */
  private void renderAddLayerComponent() {
    JPanel addLayerPanel = new JPanel();
    addLayerPanel.setLayout(new FlowLayout());
    JLabel addLayerName = new JLabel("Layer Name:");
    addLayerPanel.add(addLayerName);
    this.layerNameField = new JTextField(5);
    addLayerPanel.add(this.layerNameField);
    this.addLayerButton = new JButton("Add Layer");
    addLayerPanel.add(this.addLayerButton);
    this.mainPanel.add(addLayerPanel);
  }

  /**
   * Renders a component that allows a user to see which filter is applied to a given layer,
   * select a filter option from a list of filters, select a layer option from a list of layers,
   * and apply a selected filter to the selected layer.
   */
  private void renderDefaultFilterLayerContainerComponent() {
    this.showLayerFilterButton = new JButton("Show Layer Filter");
    this.layerFilterMessage = new JLabel("Selected Filter: ");

    this.layerDropdownContainer = new JPanel();
    this.layerDropdownContainer.setLayout(new FlowLayout());
    this.layerDropdownContainer.add(this.showLayerFilterButton);
    this.layerDropdownContainer.add(this.layerFilterMessage);

    this.layerDropdownPanel = new JPanel();
    this.layerDropdown = new JComboBox<>();
    this.layerDropdownPanel.add(this.layerDropdown);
    this.layerDropdownContainer.add(this.layerDropdownPanel);

    this.filterDropdown = new JComboBox<>();

    JPanel filterDropdownPanel = new JPanel();
    filterDropdownPanel.setBorder(BorderFactory.createTitledBorder("Filter Menu"));
    filterDropdownPanel.setLayout(new BoxLayout(filterDropdownPanel, BoxLayout.PAGE_AXIS));

    JLabel filterDropdownMessage = new JLabel("Select a Filter");
    filterDropdownPanel.add(filterDropdownMessage);

    for (String filterName : Util.FILTER_OPTIONS) {
      this.filterDropdown.addItem(filterName);
    }

    filterDropdownPanel.add(this.filterDropdown);

    this.layerDropdownContainer.add(filterDropdownPanel);
    this.confirmSetLayerFilterButton = new JButton("Apply Filter To Layer");
    this.layerDropdownContainer.add(this.confirmSetLayerFilterButton);

    this.mainPanel.add(this.layerDropdownContainer);
  }

  /**
   * Updates the layer dropdown component based on the current layer to filter name mapping.
   */
  private void renderLayerDropdown() {
    this.layerDropdownContainer.remove(this.layerDropdownPanel);
    this.repaint();
    this.layerDropdownPanel = new JPanel();

    this.layerDropdownPanel.setBorder(BorderFactory.createTitledBorder("Layer Menu"));
    this.layerDropdownPanel.setLayout(new BoxLayout(this.layerDropdownPanel, BoxLayout.PAGE_AXIS));

    this.layerDropdownMessage = new JLabel("Select a Layer");
    this.layerDropdownPanel.add(this.layerDropdownMessage);

    this.layerDropdown.removeAllItems();

    for (String layerName : this.layerNameToFilterName.keySet()) {
      this.layerDropdown.addItem(layerName);
    }

    this.layerDropdownPanel.add(layerDropdown);
    this.layerDropdownContainer.add(this.layerDropdownPanel);
  }

  /**
   * Renders a component that allows the user to add an image to the project.
   */
  private void renderAddImageComponent() {
    JPanel addImagePanel = new JPanel();
    addImagePanel.setLayout(new FlowLayout());

    this.openFileButton = new JButton("Select Image");
    this.selectedFileMessage = new JLabel("Selected Image: ");
    this.imageRowDisplacementField = new JTextField(5);
    this.imageColDisplacementField = new JTextField(5);
    this.confirmImageUploadButton = new JButton("Confirm Upload");

    JLabel rowLabel = new JLabel("Row Displacement:");
    JLabel colLabel = new JLabel("Column Displacement:");

    addImagePanel.add(this.openFileButton);
    addImagePanel.add(this.selectedFileMessage);
    addImagePanel.add(rowLabel);
    addImagePanel.add(this.imageRowDisplacementField);
    addImagePanel.add(colLabel);
    addImagePanel.add(this.imageColDisplacementField);
    addImagePanel.add(this.confirmImageUploadButton);

    this.mainPanel.add(addImagePanel);
  }

  /**
   * Renders the composite image in the collage project.
   * @param image the composite image to display.
   */
  private void renderImage(ImageModel<Pixel> image) {
    Image compositeImage = this.createImageFromScratch(image);

    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(compositeImage));

    this.imagePanel.remove(this.compositeImage);
    this.compositeImage = new JScrollPane(imageLabel);
    this.compositeImage.setPreferredSize(new Dimension(500, 500));
    this.imagePanel.add(this.compositeImage);
  }

  /**
   * Renders the commands in the collage project that a user can interact with.
   */
  private void renderProjectCommandComponent() {
    JPanel commandPanel = new JPanel();
    commandPanel.setLayout(new FlowLayout());

    this.uploadProjectButton = new JButton("Upload Project");
    this.saveProjectButton = new JButton("Save Project");
    this.saveImageButton = new JButton("Save Image");

    commandPanel.add(this.uploadProjectButton);
    commandPanel.add(this.saveProjectButton);
    commandPanel.add(this.saveImageButton);

    this.mainPanel.add(commandPanel);
  }

  /**
   * Creates a java.awt.Image from integer RGBA values.
   * @param compositeImage the image to convert into a java.awt Image.
   * @return the java.awt.Image.
   */
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
    Util.anyNull(new IllegalArgumentException("Controller features is null"), features);

    this.newProjectButton.addActionListener(evt -> {
      try {
        int canvasWidth = Integer.parseInt(this.widthField.getText(), 10);
        int canvasHeight = Integer.parseInt(this.heightField.getText(), 10);
        features.makeNewProject(canvasWidth, canvasHeight);
      } catch (NumberFormatException e) {
        this.renderErrorMessage("Please enter a number greater than zero.");
      }
    });

    this.layerDropdown.addActionListener(evt -> {
      JComboBox<String> box = (JComboBox<String>) evt.getSource();
      this.layerDropdownMessage.setText("Selected Layer: " + (String) box.getSelectedItem());
      this.selectedLayer = (String) box.getSelectedItem();
    });

    this.filterDropdown.addActionListener(evt -> {
      JComboBox<String> box = (JComboBox<String>) evt.getSource();
      this.selectedFilter = (String) box.getSelectedItem();
    });

    this.showLayerFilterButton.addActionListener(evt -> {
      this.layerFilterMessage.setText("Selected Filter: " +
              this.layerNameToFilterName.get(this.selectedLayer));
    });

    this.openFileButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("PPM Images", "ppm");
      fileChooser.setFileFilter(filter);

      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        this.imageFilePath = f.getAbsolutePath();
        this.selectedFileMessage.setText("Selected Image: " + f.getName());
      }
    });

    this.confirmImageUploadButton.addActionListener(evt -> {
      try {
        int rowDisplacement = Integer.parseInt(this.imageRowDisplacementField.getText(), 10);
        int colDisplacement = Integer.parseInt(this.imageColDisplacementField.getText(), 10);
        features.addImageToLayer(this.imageFilePath, this.selectedLayer, rowDisplacement,
                colDisplacement);
      } catch (NumberFormatException e) {
        this.renderErrorMessage("Row and column displacement must be numbers.");
      }
    });

    this.confirmSetLayerFilterButton.addActionListener(evt -> {
      features.applyFilter(this.selectedLayer, this.selectedFilter);
    });

    this.addLayerButton.addActionListener(evt -> {
      features.addLayer(this.layerNameField.getText());
    });

    this.saveProjectButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      int returnValue = fileChooser.showSaveDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        features.saveProject(f.getAbsolutePath());
      }
    });

    this.saveImageButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      int returnValue = fileChooser.showSaveDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        features.saveImage(f.getAbsolutePath());
      }
    });

    this.uploadProjectButton.addActionListener(evt -> {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Project Files", "collage");
      fileChooser.setFileFilter(filter);

      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
        features.loadProject(f.getAbsolutePath());
      }
    });
  }

  @Override
  public void refresh(ImageModel<Pixel> compositeImage, Map<String, String> layerNameToFilterName) {
    Util.anyNull(new IllegalArgumentException("Composite image or mapping is null"),
            compositeImage, layerNameToFilterName);
    this.layerNameToFilterName = layerNameToFilterName;
    this.renderErrorMessage("");
    this.renderImage(compositeImage);
    this.renderLayerDropdown();

    this.setVisible(true);
  }
}
