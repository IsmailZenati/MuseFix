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
    private TableColumn<abonnement,Integer> col_idpack;

    @FXML
    private TableView<abonnement> tv_abonnement;

    @FXML
    void initialize() {
        try {
            ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
            ObservableList<abonnement> abonnements= FXCollections.observableArrayList(serviceAbonnement.afficher());
            System.out.println(abonnements);
            tv_abonnement.setItems(abonnements);
            col_idabonnement.setCellValueFactory(new PropertyValueFactory<>("idAbonnement"));
            col_iduser.setCellValueFactory(new PropertyValueFactory<>("userID"));
            col_idpack.setCellValueFactory(new PropertyValueFactory<>("IdPack"));
            col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}