package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.User;

import java.io.IOException;
import java.util.Date;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    @FXML
    void modifier(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                // Load the FXML file for the modification window
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierUser.fxml"));
                Parent root = loader.load();

                // Get the controller for the modification window
                ModifierUserController modifierController = loader.getController();

                // Pass the selected user object to the modification window controller
                modifierController.initData(selectedUser);

                // Create a new stage for the modification window
                Stage stage = new Stage();
                stage.setTitle("Modifier Utilisateur");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erreur de chargement", "Une erreur s'est produite lors du chargement de la fenêtre de modification.");
            }
        } else {
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à modifier.");
        }
    }

    @FXML
    void supprimer(ActionEvent event) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                serviceUser.supprimer(selectedUser.getId());
                userTableView.getItems().remove(selectedUser); // Remove the selected user from the table
                showAlert("Suppression réussie", "L'utilisateur a été supprimé avec succès.");
            } catch (SQLException e) {
                showAlert("Erreur de suppression", "Une erreur s'est produite lors de la suppression de l'utilisateur: " + e.getMessage());
            }
        } else {
            showAlert("Aucun utilisateur sélectionné", "Veuillez sélectionner un utilisateur à supprimer.");
        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
