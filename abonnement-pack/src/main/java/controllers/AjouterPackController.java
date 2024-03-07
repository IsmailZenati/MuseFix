package controllers;

import entities.packs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePack;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterPackController {
    ServicePack servicePack = new ServicePack();

    @FXML
    private TextField tf_advantage;

    @FXML
    private TextField tf_packtype;

    @FXML
    private TextField tf_price;

    @FXML
    void AfficherPack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPack.FXML"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void AjouterPack(ActionEvent event) {
        // Vérifier si les champs sont vides
        if (tf_packtype.getText().isEmpty() || tf_price.getText().isEmpty() || tf_advantage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return; // Sortir de la méthode si un champ est vide
        }

        try {
            servicePack.ajouter(new packs(tf_packtype.getText(), Float.parseFloat(tf_price.getText()), tf_advantage.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Pack ajouté");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
