package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainController {

    @FXML
    private MenuItem ajouterUserMenuItem;

    @FXML
    private MenuItem afficherUserMenuItem;

    @FXML
    private void initialize() {
        // Add event handlers for menu items
        ajouterUserMenuItem.setOnAction(event -> loadAjouterUserScene());
        afficherUserMenuItem.setOnAction(event -> loadAfficherUserScene());
    }

    private void loadAjouterUserScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Ajouter Utilisateur");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Ajouter User scene.");
            e.printStackTrace();
        }
    }

    private void loadAfficherUserScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Afficher Utilisateurs");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Afficher User scene.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
