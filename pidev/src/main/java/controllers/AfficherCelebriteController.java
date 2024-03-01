package controllers;

import entities.celebrite;
import entities.quiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Servicecelebrite;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AfficherCelebriteController {

    Servicecelebrite servicecelebrite = new Servicecelebrite();

    @FXML
    private TableView<celebrite> tv_celebrite;

    @FXML
    private TableColumn<celebrite, Integer> col_idCelebrite;

    @FXML
    private TableColumn<celebrite, String> col_nom;

    @FXML
    private TableColumn<celebrite, String> col_nationalite;

    @FXML
    private TableColumn<celebrite, String> col_profession;

    @FXML
    private TableColumn<celebrite, String> col_dateNais;

    @FXML
    void initialize() {
        try {
            ObservableList<celebrite> celebrites = FXCollections.observableList(servicecelebrite.afficher());
            tv_celebrite.setItems(celebrites);

            // Initialize columns
            col_idCelebrite.setCellValueFactory(new PropertyValueFactory<>("idCelebrite"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_nationalite.setCellValueFactory(new PropertyValueFactory<>("nationalite"));
            col_profession.setCellValueFactory(new PropertyValueFactory<>("profession"));

            // Convert Date to String for display
            col_dateNais.setCellValueFactory(cellData -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return new SimpleStringProperty(dateFormat.format(cellData.getValue().getDateNais()));
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
