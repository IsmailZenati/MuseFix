package controllers;

import entities.Panier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import services.ServicePanier;


import java.io.IOException;
import java.sql.SQLException;

public class AjouterPanierController {

    ServicePanier servicePanier = new ServicePanier();

    @FXML
    private TextField tf_idProduit;

    @FXML
    private TextField tf_prixUnite;

    @FXML
    private TextField tf_qte;


    @FXML
    private TextField tf_userid;

    @FXML
    void AjouterPanier(ActionEvent event) {

        try {
            servicePanier.ajouter(new Panier(Integer.parseInt(tf_userid.getText()),Integer.parseInt(tf_idProduit.getText()),Integer.parseInt(tf_qte.getText()),Float.parseFloat(tf_prixUnite.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("ajouté au Panier");
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void AfficherPanier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherPanier.fxml"));
            tf_userid.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
