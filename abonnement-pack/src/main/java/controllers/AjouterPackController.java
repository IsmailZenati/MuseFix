package controllers;

import entities.packs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServicePack;

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
    void AfficherPack(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/AfficherPack.FXML"));
    }

    @FXML
    void AjouterPack(ActionEvent event) {
        try {
            servicePack.ajouter(new packs(tf_packtype.getText(), Float.parseFloat(tf_price.getText()), tf_advantage.getText()));
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("pack ajouté") ;
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println(e.getMessage());


        }
    }
}
