package controllers;

import entities.quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Servicequiz;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjouterQuizController {

    private final Servicequiz servicequiz = new Servicequiz();

    @FXML
    private TextField tf_description;

    @FXML
    private TextField tf_difficulte;

    @FXML
    private TextField tf_idCelebrite;

    @FXML
    private TextField tf_titre;

    @FXML
    private TextField tf_DateCreation;

    @FXML
    private TextField tf_idQuiz;

    @FXML
    private Button goToAjouterCelebriteButton;

    @FXML
    void goToAjouterCelebrite(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCelebrite.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Ajouter Celebrite");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AfficherQuiz(ActionEvent event) {
        // Code for navigating to the AfficherQuiz screen
    }

    @FXML
    void ModifierQuiz(ActionEvent event) {
        try {
            // Load the FXML file for ModifierQuiz.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierQuiz.fxml"));
            Parent modifyRoot = loader.load();

            // Set the modify interface as the root of the scene
            Scene modifyScene = new Scene(modifyRoot);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(modifyScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void AjouterQuiz(ActionEvent event) {
        try {
            String dateString = tf_DateCreation.getText();
            Date dateCreation = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);

            quiz nouveauQuiz = new quiz(
                    Integer.parseInt(tf_idQuiz.getText()),
                    tf_titre.getText(),
                    tf_description.getText(),
                    tf_difficulte.getText(),
                    dateCreation,
                    Integer.parseInt(tf_idCelebrite.getText())
            );

            servicequiz.ajouter(nouveauQuiz);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le quiz a été ajouté avec succès !");
            alert.showAndWait();
        } catch (SQLException | ParseException | NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du quiz : " + e.getMessage());
            alert.showAndWait();
        }
    }
}

