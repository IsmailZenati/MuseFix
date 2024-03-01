package controllers;

import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import services.ServiceCommande;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class AjouterCommandeController {

    ServiceCommande serviceCommande = new ServiceCommande();
    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField tf_adresseLivraison;

    @FXML
    private TextField tf_fraisLivraison;

    @FXML
    private TextField tf_modePaiement;

    @FXML
    private TextField tf_status;

    @FXML
    private TextField tf_total;

    @FXML
    private TextField tf_userid;

    @FXML
    void AjouterCommande(ActionEvent event) {
        Date dp_orderDate = java.sql.Date.valueOf(datePicker.getValue());
        try {
            serviceCommande.ajouter(new Commande(Integer.parseInt(tf_userid.getText()), dp_orderDate,tf_status.getText(),tf_modePaiement.getText(),tf_adresseLivraison.getText(),Float.parseFloat(tf_fraisLivraison.getText()),Float.parseFloat(tf_total.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Commande ajouté");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AfficherCommande(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherCommande.fxml"));
            tf_userid.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
