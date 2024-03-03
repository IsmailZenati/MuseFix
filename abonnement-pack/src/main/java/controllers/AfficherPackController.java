package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceAbonnement;
import entities.packs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;

public class AfficherPackController {

    @FXML
    private TableColumn<packs, String> col_avantage;

    @FXML
    private TableColumn<packs, Integer> col_idpack;

    @FXML
    private TableColumn<packs, Float> col_prix;

    @FXML
    private TableColumn<packs, String> col_typepack;

    @FXML
    private TableView<packs> tv_pack;

    @FXML
    void initialize() {
        try {
            ServicePack servicePack = new ServicePack();
            ObservableList<packs> pack = FXCollections.observableList(servicePack.afficher());
            tv_pack.setItems(pack);
            col_idpack.setCellValueFactory(new PropertyValueFactory<>("idPack"));
            col_typepack.setCellValueFactory(new PropertyValueFactory<>("TypePack"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            col_avantage.setCellValueFactory((new PropertyValueFactory<>("avantages")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
    @FXML
    void modifierpack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ModifierPack.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void supprimerpack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Modifierpack.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
