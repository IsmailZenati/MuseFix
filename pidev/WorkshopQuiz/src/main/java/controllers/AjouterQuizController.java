package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import entities.quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.Servicequiz;

public class AjouterQuizController {


    Servicequiz servicequiz= new Servicequiz();


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
    void AfficherQuiz(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherQuiz.fxml"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void AjouterQuiz(ActionEvent event) {
        try {

            String dateString = tf_DateCreation.getText();
            Date dateCreation = null;

            try {
                // Définissez le format de votre date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Vous devez utiliser le format adapté à votre chaîne de date

                // Convertissez la chaîne en objet Date
                dateCreation = dateFormat.parse(dateString);
            } catch (ParseException e) {
                // Gérez l'erreur de parsing de la date
                e.printStackTrace(); // Ou affichez un message d'erreur à l'utilisateur
            }

            // Créer une instance de quiz en utilisant les valeurs des champs de texte
            quiz nouveauQuiz = new quiz(Integer.parseInt(tf_idQuiz.getText()) ,tf_titre.getText(), tf_description.getText(), tf_difficulte.getText(), dateCreation, Integer.parseInt(tf_idCelebrite.getText()));

            // Ajouter le nouveau quiz en utilisant le service approprié
            servicequiz.ajouter(nouveauQuiz);

            // Afficher une boîte de dialogue d'information pour indiquer que le quiz a été ajouté avec succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Le quiz a été ajouté avec succès !");
            alert.showAndWait();
        } catch (SQLException e) {
            // En cas d'erreur SQL, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du quiz : " + e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // En cas d'erreur de conversion de chaîne en entier, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un numéro valide pour l'ID de la célébrité !");
            alert.showAndWait();
        }
    }


}
