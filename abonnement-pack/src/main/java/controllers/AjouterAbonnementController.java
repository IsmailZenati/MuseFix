package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.Parent;
import entities.abonnement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceAbonnement;


public class AjouterAbonnementController {
ServiceAbonnement serviceAbonnement=new ServiceAbonnement();


    @FXML
    private TextField tf_enddate;

    @FXML
    private TextField tf_idpack;

    @FXML
    private TextField tf_startdate;

    @FXML
    private TextField tf_userid;

    @FXML
    void afficherAbonnement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherAbonnement.fxml"));
        } catch(IOException e){
            System.out.println(e.getMessage());

        }


    }



    public void AjouterAbonnement(ActionEvent actionEvent) {
        try {
            serviceAbonnement.ajouter(new abonnement());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("abonnement ajouté");
            alert.showAndWait();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}