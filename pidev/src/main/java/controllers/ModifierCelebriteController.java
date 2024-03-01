package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Servicecelebrite;
import entities.celebrite;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifierCelebriteController {

    @FXML
    private Button modifierCelebrite;

    @FXML
    private Button deleteCelebrite;

    @FXML
    private TextField tf_idCelebrite;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_dateNais;

    @FXML
    private TextField tf_nationalite;

    @FXML
    private TextField tf_profession;

    private Servicecelebrite servicecelebrite;

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
    public ModifierCelebriteController() {
        // Initialize the service class
        servicecelebrite = new Servicecelebrite();
    }

    @FXML
    void initialize() {
        if (modifierCelebrite != null) {
            modifierCelebrite.setOnAction(event -> {
                try {
                    // Get the ID of the celebrite to modify
                    int idCelebrite = Integer.parseInt(tf_idCelebrite.getText());

                    // Get values from text fields for modification
                    String nom = tf_nom.getText();
                    Date dateNais = parseDate(tf_dateNais.getText());
                    String nationalite = tf_nationalite.getText();
                    String profession = tf_profession.getText();

                    // Create a new celebrite object with updated values
                    celebrite modifiedCelebrite = new celebrite(idCelebrite, nom, dateNais, nationalite, profession);

                    // Call the service method to modify the celebrite
                    servicecelebrite.modifier(modifiedCelebrite);

                    // Show success message
                    System.out.println("Celebrite with ID " + idCelebrite + " modified successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid input format.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error modifying celebrite: " + ex.getMessage());
                }
            });
        }

        if (deleteCelebrite != null) {
            deleteCelebrite.setOnAction(event -> {
                try {
                    // Get the ID of the celebrite to delete
                    int idCelebrite = Integer.parseInt(tf_idCelebrite.getText());

                    // Delete the celebrite based on ID
                    servicecelebrite.supprimer(idCelebrite);

                    // Show success message
                    System.out.println("Celebrite with ID " + idCelebrite + " deleted successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid celebrite ID format.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error deleting celebrite: " + ex.getMessage());
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