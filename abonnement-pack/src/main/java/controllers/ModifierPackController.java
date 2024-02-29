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
             float prix = Float.parseFloat((tf_prix.getText()));
            String avantages = tf_avantages.getText();

            servicePack.modifier(new packs(packId,typePack, prix, avantages));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Pack modifié");
            alert.showAndWait();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void supprimerPack(ActionEvent event) {
        int packId = Integer.parseInt(tf_packId.getText());
        try {
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

