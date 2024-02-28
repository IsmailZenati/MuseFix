package controllers;

import entities.abonnement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceAbonnement;

import java.sql.SQLException;

public class ModifierAbonnementController {

    private final ServiceAbonnement serviceAbonnement = new ServiceAbonnement();

    @FXML
    private TextField tf_subscriptionId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModify;

    @FXML
    void modifierAbonnement(ActionEvent event) {
        int subscriptionId = Integer.parseInt(tf_subscriptionId.getText());
        try {
            // Votre logique pour modifier l'abonnement ici
            serviceAbonnement.modifier(new abonnement());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement modifié");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void supprimerAbonnement(ActionEvent event) {
        int subscriptionId = Integer.parseInt(tf_subscriptionId.getText());
        try {
            // Votre logique pour supprimer l'abonnement ici
            serviceAbonnement.supprimer(subscriptionId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement supprimé");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
