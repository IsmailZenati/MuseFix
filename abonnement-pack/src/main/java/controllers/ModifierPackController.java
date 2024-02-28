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
    void modifierPack(ActionEvent event) {
        int packId = Integer.parseInt(tf_packId.getText());
        try {
            // Votre logique pour modifier le pack ici
            servicePack.modifier(new packs());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Pack modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

