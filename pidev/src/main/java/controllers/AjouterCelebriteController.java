package controllers;

import entities.celebrite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Servicecelebrite;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjouterCelebriteController {

    Servicecelebrite servicecelebrite = new Servicecelebrite();

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

    @FXML
    private Button goToAjouterQuizButton;

    @FXML
    void goToAjouterQuiz(ActionEvent event) {
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

    @FXML
    void AfficherCelebrite(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherCelebrite.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // Create a new stage for the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Afficher Celebrite");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ModifierCelebrite(ActionEvent event) {
        try {
            // Load the FXML file for ModifierCelebrite.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCelebrite.fxml"));
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
    void AjouterCelebrite(ActionEvent event) {
        try {
            String dateString = tf_dateNais.getText().toString();
            Date dateNais = null;

            try {
                // Définissez le format de votre date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Vous devez utiliser le format adapté à votre chaîne de date

                // Convertissez la chaîne en objet Date
                dateNais = dateFormat.parse(dateString);
            } catch (ParseException e) {
                // Gérez l'erreur de parsing de la date
                e.printStackTrace(); // Ou affichez un message d'erreur à l'utilisateur
            }

            // Créer une instance de celebrite en utilisant les valeurs des champs de texte
            celebrite nouveauCelebrite = new celebrite(Integer.parseInt(tf_idCelebrite.getText()), tf_nom.getText(), dateNais, tf_nationalite.getText(), tf_profession.getText());

            // Ajouter le nouveau celebrite en utilisant le service approprié
            servicecelebrite.ajouter(nouveauCelebrite);

            // Afficher une boîte de dialogue d'information pour indiquer que le celebrite a été ajouté avec succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le celebrite a été ajouté avec succès !");
            alert.showAndWait();
        } catch (SQLException e) {
            // En cas d'erreur SQL, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du celebrite : " + e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // En cas d'erreur de conversion de chaîne en entier, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un numéro valide pour l'ID du celebrite !");
            alert.showAndWait();
        }
    }
}
