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
    private TextField tf_date1; // Declare tf_date1

    @FXML
    private TextField tf_date2;
    @FXML
     void modifierAbonnement(ActionEvent event) {
        try {
            int subscriptionId = Integer.parseInt(tf_subscriptionId.getText());

            // Check if the text fields are not empty
            if (tf_date1.getText().isEmpty() || tf_date2.getText().isEmpty()) {
                throw new IllegalArgumentException("Date fields cannot be empty");
            }

            // Récupérer les valeurs des champs de texte tf_date1 et tf_date2
            java.sql.Date dateDeb = java.sql.Date.valueOf(tf_date1.getText());
            java.sql.Date dateFin = java.sql.Date.valueOf(tf_date2.getText());

            // Appel de la méthode modifier du service ServiceAbonnement
            serviceAbonnement.modifier(new abonnement(subscriptionId, dateDeb, dateFin));

            // Affichage d'un message d'alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement modifié");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Gérer les exceptions liées à la conversion du texte en entier
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid input for subscription ID");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            // Gérer les exceptions liées aux champs de date vides ou invalides
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid date format or empty date fields");
            alert.showAndWait();
        } catch (SQLException e) {
            // Gérer les exceptions liées à la base de données
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("An error occurred while modifying the subscription");
            alert.showAndWait();
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
