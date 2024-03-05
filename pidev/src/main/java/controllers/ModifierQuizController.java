package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Servicequiz;
import entities.quiz;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifierQuizController {

    @FXML
    private Button modifierQuiz;

    @FXML
    private Button deleteQuiz;

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

    private Servicequiz servicequiz;

    @FXML
    private Button goToAjouterQuizButton;

    @FXML
    public void goToAjouterQuiz(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterQuiz.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Ajouter Quiz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ModifierQuizController() {
        // Initialize the service class
        servicequiz = new Servicequiz();
    }

    @FXML
    void initialize() {
        if (modifierQuiz != null) {
            modifierQuiz.setOnAction(event -> {
                try {
                    // Get the titre of the quiz to modify
                    String titre = tf_titre.getText();

                    // Get values from text fields for modification
                    String nom = tf_nom.getText();
                    String description = tf_description.getText();
                    String difficulte = tf_difficulte.getText();
                    Date dateCreation = parseDate(tf_DateCreation.getText());

                    // Create a new quiz object with updated values
                    quiz modifiedQuiz = new quiz();
                    modifiedQuiz.setTitre(titre);
                    modifiedQuiz.setDescription(description);
                    modifiedQuiz.setDifficulte(difficulte);
                    modifiedQuiz.setDateCreation(dateCreation);
                    modifiedQuiz.setNom(nom);

                    // Call the service method to modify the quiz
                    servicequiz.modifier(titre, modifiedQuiz);

                    // Show success message
                    System.out.println("Quiz with titre " + titre + " modified successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid input format.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error modifying quiz: " + ex.getMessage());
                }
            });
        }

        if (deleteQuiz != null) {
            deleteQuiz.setOnAction(event -> {
                try {
                    // Get the nom of the quiz to delete
                    String titre = tf_titre.getText();

                    // Delete the quiz based on nom
                    servicequiz.supprimer(titre);

                    // Show success message
                    System.out.println("Quiz with titre " + titre + " deleted successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid quiz titre.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error deleting quiz: " + ex.getMessage());
                }
            });
        }
    }


    // Method to parse date string to Date object
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }


}