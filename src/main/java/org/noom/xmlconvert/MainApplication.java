package org.noom.xmlconvert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/org/noom/xmlconvert/main-view.fxml"));

        // Set the scene with the loaded FXML content
        Scene scene = new Scene(root);

        // Set the scene to the stage
        stage.setScene(scene);

        // Set the title text windows
        stage.setTitle("XMLConverter");
        stage.setResizable(false);

        // Set the position of the window
        stage.setX(100);
        stage.setY(100);

        // Add the icon to the stage 
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.ico")));

        // Show the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}