package controllers;


import entities.packs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePack;

import java.sql.SQLException;

public class ModifierPackController {

    private final ServicePack servicePack = new ServicePack();

    @FXML
    private TextField tf_packId;

    @FXML
    private TextField tf_typepack;

    @FXML
    private TextField tf_prix;

    @FXML
    private TextField tf_avantages;

    @FXML
    void modifierPack(ActionEvent event) {
        int packId = Integer.parseInt(tf_packId.getText());
        try {
            String typePack = tf_typepack.getText();
            double prix = Double.parseDouble(tf_prix.getText());
            String avantages = tf_avantages.getText();

            // Appel de la méthode ajouter du service ServicePack
            servicePack.ajouter(new packs(typePack, (float) prix, avantages));

            // Affichage d'un message d'alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Pack modifié");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Gérer les exceptions liées à la conversion des valeurs
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid input for price");
            alert.showAndWait();
        } catch (SQLException e) {
            // Gérer les exceptions liées à la base de données
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred while adding the pack");
            alert.showAndWait();
        }
    }
    @FXML
    void supprimerPack(ActionEvent event) {
        int packId = Integer.parseInt(tf_packId.getText());
        try {
            // Votre logique pour supprimer le pack ici
            servicePack.supprimer(packId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Pack supprimé");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

