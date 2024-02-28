package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.User;
import java.util.Date;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceUser;

import java.sql.SQLException;
import java.util.List;

public class AfficherUserController {

    private final ServiceUser serviceUser = new ServiceUser();

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, String> nomColumn;

    @FXML
    private TableColumn<User, String> prenomColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, Date> signupDateColumn;

    @FXML
    private TableColumn<User, String> adresseColumn;

    public void initialize() {
        // Initialize the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        signupDateColumn.setCellValueFactory(new PropertyValueFactory<>("signupDate"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        // Load data into TableView
        loadData();
    }

    private void loadData() {
        try {
            List<User> userList = serviceUser.afficher();
            ObservableList<User> data = FXCollections.observableArrayList(userList);
            userTableView.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
