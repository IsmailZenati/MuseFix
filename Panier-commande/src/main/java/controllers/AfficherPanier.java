package controllers;

import entities.Panier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServicePanier;

import java.sql.SQLException;

public class AfficherPanier {

    ServicePanier servicePanier = new ServicePanier();
    @FXML
    private TableColumn<Panier, Integer> col_idProduit;

    @FXML
    private TableColumn<Panier, Float> col_prixUnite;

    @FXML
    private TableColumn<Panier, Integer> col_qte;

    @FXML
    private TableColumn<Panier, Float> col_sousTotal;

    @FXML
    private TableColumn<Panier, Integer> col_userId;

    @FXML
    private TableView<Panier> tv_panier;

    @FXML
    void initialize() {
        try {
            ObservableList<Panier> Paniers = FXCollections.observableList(servicePanier.afficher());
            tv_panier.setItems(Paniers);
            col_userId.setCellValueFactory(new PropertyValueFactory<>("userID"));
            col_idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
            col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
            col_prixUnite.setCellValueFactory(new PropertyValueFactory<>("prixUnite"));
            col_sousTotal.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
