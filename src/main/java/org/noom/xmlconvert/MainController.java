package org.noom.xmlconvert;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

import static org.noom.xmlconvert.FileHandler.*;

public class MainController {

    private final String REGEX_FILE = ".\\[a-zA-Z0-9]+$";

    public Button folderSourceButton;
    public Button folderTargetButton;
    public Button convertButton;

    public TextField targetPathInput;
    public TextField sourcePathInput;

    public TableView tableView;
    public TableColumn fileNameColumn;
    public TableColumn resultColumn;
    public Button searchButton;

    @FXML
    public void initialize() {
        // Set the functions on sources buttons
        folderSourceButton.setOnAction(event -> onFolderButtonClick(sourcePathInput));

        // Set the functions on target buttons
        folderTargetButton.setOnAction(event -> onFolderButtonClick(targetPathInput));

        // Set the function to convert button
        convertButton.setOnAction(event -> {
            try {
                onConvertButtonClick(sourcePathInput.getText(), targetPathInput.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Set the function to search button
        searchButton.setOnAction(event -> onSearchButtonClick(sourcePathInput.getText(), targetPathInput.getText()));

        // Initialize the columns from tableview for the files paths
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("targetPath"));
    }

    @FXML
    protected void onFolderButtonClick(TextField fieldInputSelected) {
        // Get the directory chooser api
        DirectoryChooser directoryChooser = new DirectoryChooser();

        // Set initial path
        directoryChooser.setInitialDirectory(Paths.get(System.getProperty("user.dir")).toFile());

        // Open the dir manager win
        var selectedDirectory = directoryChooser.showDialog(null);

        // Verify if it's a valid path
        if (selectedDirectory != null) fieldInputSelected.setText(selectedDirectory.getAbsolutePath());

    }

    @FXML
    protected void onFileButtonClick(TextField fieldInputSelected) {
        // Get the directory chooser api
        FileChooser fileChooser = new FileChooser();

        // Set initial path
        fileChooser.setInitialDirectory(Paths.get(System.getProperty("user.dir")).toFile());

        System.out.println(fieldInputSelected.getId());

        // Set the filter for each button
        if (fieldInputSelected.getId().contains("source"))
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        else fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));

        // Open the dir manager win
        var selectedDirectory = fileChooser.showOpenDialog(null);

        // Verify if it's a valid path
        if (selectedDirectory != null) fieldInputSelected.setText(String.valueOf(selectedDirectory.getAbsoluteFile()));

    }

    @FXML
    protected void onConvertButtonClick(String originPath, String targetPath) {
        try {
            if (originPath.isBlank() || targetPath.isBlank()) throw new NullPointerException();
            File[] filesToConvert = folderSearch(originPath);
            List<File> txtFilesToConvert;
            txtFilesToConvert = getTxtFiles(filesToConvert);

            if (filesToConvert.length != 0) for (File file : txtFilesToConvert) {
                String targetFilePath = targetPath + "/" + file.getName().replace(".txt", ".xml");
                convertTxtToXml(file.getAbsolutePath(), targetFilePath);
            }
            else throw new NullPointerException();
            showSuccessDialog("Arquivos convertidos com sucesso!");

        } catch (NullPointerException e) {
            showAlertDialog("Não foi encontrado nenhum arquivo txt no caminho de origem!");
        } catch (Exception e) {
            showAlertDialog("Caminho de origem ou destino vazio!");
        }

    }

    @FXML
    protected void onSearchButtonClick(String originPath, String targetPath) {
        try {
            if (originPath.isBlank() || targetPath.isBlank()) throw new NullPointerException();
            File[] filesToConvert = folderSearch(originPath);
            assert filesToConvert != null;

            // Check if the array has files in it
            List<File> txtFilesToConvert = getTxtFiles(filesToConvert);

            // Populate TableView with files to be converted
            populateTableView(txtFilesToConvert, targetPath);
        } catch (NullPointerException e) {
            showAlertDialog("Não foi encontrado nenhum arquivo txt no caminho de origem!");
        } catch (Exception e) {
            showAlertDialog("Caminho de origem inválido ou vazio");
        }
    }

    private void populateTableView(List<File> files, String targetPath) {
        // Set teh collection list to populate tableview
        ObservableList<FileConversionData> data = FXCollections.observableArrayList();

        // Get all the origin path for xml files
        for (File file : files) {
            String targetFilePath = targetPath + "/" + file.getName().replace(".txt", ".xml");
            data.add(new FileConversionData(file.getName(), targetFilePath));
        }
        tableView.setItems(data);
    }

    private void showAlertDialog(String message) {
        // Set the warning screen function
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Alerta");
        alert.setContentText(message);
        alert.showAndWait();
        alert.setX(100);
        alert.setY(100);
    }

    private void showSuccessDialog(String message) {
        // Set the success screen function
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Sucesso");
        alert.setContentText(message);
        alert.showAndWait();
        alert.setX(100);
        alert.setY(100);
    }


}
