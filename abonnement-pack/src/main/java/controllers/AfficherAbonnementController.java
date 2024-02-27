package controllers;
import entities.abonnement;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAbonnement;
import java.sql.SQLException;
import java.util.Date;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AfficherAbonnementController {


    @FXML
    private TableColumn<abonnement, Date> col_datedebut;

    @FXML
    private TableColumn<abonnement, Date> col_datefin;

    @FXML
    private TableColumn<abonnement, Integer> col_idabonnement;

    @FXML
    private TableColumn<abonnement, Integer> col_iduser;

    @FXML
    private TableColumn<abonnement,Integer> col_idPack;

    @FXML
    private TableView<abonnement> tv_abonnement;

    @FXML
    void initialize() {
        ObservableList<abonnement> abonnements = null;
        try {
            ServiceAbonnement sa = new ServiceAbonnement();
            abonnements = FXCollections.observableArrayList();
            abonnements.addAll(sa.afficher());


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            tv_abonnement.setItems(abonnements);
            col_idabonnement.setCellValueFactory(new PropertyValueFactory<>("idAbonnement"));
            col_iduser.setCellValueFactory(new PropertyValueFactory<>("userID"));
            col_idPack.setCellValueFactory(new PropertyValueFactory<>("idPack"));
            col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));


        }
    }
}