package controllers;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceAbonnement;
import entities.packs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import services.ServicePack;
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
            col_prix.setCellValueFactory((new PropertyValueFactory<>("avantages")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }
}
