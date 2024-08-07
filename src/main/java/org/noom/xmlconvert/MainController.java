package org.noom.xmlconvert;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.nio.file.Paths;

public class MainController {

    private final String REGEX_FILE = ".\\[a-zA-Z0-9]+$";

    public Button folderSourceButton;
    public Button folderTargetButton;
    public Button fileSourceButton;
    public Button fileTargetButton;
    public Button convertButton;

    public TextField targetPathInput;
    public TextField sourcePathInput;

    public TableView tableView;
    public TableColumn fileNameColumn;
    public TableColumn resultColumn;
    public Button searchButton;

    @FXML
    public void initialize() {
        // Set the functions on the sources buttons
        folderSourceButton.setOnAction(event -> onFolderButtonClick(sourcePathInput));
        fileSourceButton.setOnAction(event -> onFileButtonClick(sourcePathInput));

        // Set the functions on the target buttons
        folderTargetButton.setOnAction(event -> onFolderButtonClick(targetPathInput));
        fileTargetButton.setOnAction(actionEvent -> onFileButtonClick(targetPathInput));
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

        // Open the dir manager win
        var selectedDirectory = fileChooser.showOpenDialog(null);

        // Verify if it's a valid path
        if (selectedDirectory != null) fieldInputSelected.setText(String.valueOf(selectedDirectory.getAbsoluteFile()));

    }

    @FXML
    protected void onConvertButtonClick(String originPath, String targetPath) throws Exception {

    }

    private void showAlertDialog(String message) {
        // Set the warning screen function
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Caminho inválido");
        alert.setContentText(message);
        alert.showAndWait();
    }



}
