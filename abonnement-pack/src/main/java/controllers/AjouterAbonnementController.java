package controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        Date endDate = null;

        try {
            startDate = new Date(sdf.parse(tf_startdate.getText()).getTime());
            endDate = new Date(sdf.parse(tf_enddate.getText()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            serviceAbonnement.ajouter(new abonnement(
                  Integer.parseInt(tf_userid.getText()), Integer.parseInt(tf_idpack.getText()),startDate ,endDate));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("abonnement ajouté");
            alert.showAndWait();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}