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
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
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
    private TextField tf_nom;

    @FXML
    private TextField tf_titre;

    @FXML
    private TextField tf_DateCreation;

    @FXML
    private Button goToAjouterCelebriteButton;

    @FXML
    private Button playquizbtn;

    @FXML
    private void initialize() {

        playquizbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisstage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // Create a new stage for the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Afficher Quiz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    tf_titre.getText(),
                    tf_description.getText(),
                    tf_difficulte.getText(),
                    dateCreation,
                    tf_nom.getText()
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

