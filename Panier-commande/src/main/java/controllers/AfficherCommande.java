package controllers;

import entities.Commande;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceCommande;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AfficherCommande {

    ServiceCommande serviceCommande = new ServiceCommande();

    @FXML
    private TableColumn<Commande, Integer> col_userID;

    @FXML
    private TableColumn<Commande, String> col_adresseLivraison;

    @FXML
    private TableColumn<Commande, String> col_modedePaiement;

    @FXML
    private TableColumn<Commande, String> col_status;

    @FXML
    private TableColumn<Commande, String> col_orderDate;

    @FXML
    private TableColumn<Commande, Float> col_fraisLivraison;

    @FXML
    private TableColumn<Commande, Float> col_total;

    @FXML
    private TableView<Commande> tv_commande;

    @FXML
    void initialize() {
        try {
            ObservableList<Commande> commandes = FXCollections.observableList(serviceCommande.afficher());
            tv_commande.setItems(commandes);

            col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));

            col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            col_modedePaiement.setCellValueFactory(new PropertyValueFactory<>("modePaiement"));
            col_adresseLivraison.setCellValueFactory(new PropertyValueFactory<>("adresseLivraison"));
            col_fraisLivraison.setCellValueFactory(new PropertyValueFactory<>("fraisLivraison"));
            col_total.setCellValueFactory(new PropertyValueFactory<>("total"));

            // Convert Date to String for display
            col_orderDate.setCellValueFactory(cellData -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return new SimpleStringProperty(dateFormat.format(cellData.getValue().getOrderDate()));
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}