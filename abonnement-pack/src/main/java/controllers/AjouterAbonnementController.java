package  controllers;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javafx.scene.Parent;
import entities.abonnement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceAbonnement;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AjouterAbonnementController {
    ServiceAbonnement serviceAbonnement = new ServiceAbonnement();

    @FXML
    private TextField tf_enddate;

    @FXML
    private TextField tf_idpack;

    @FXML
    private TextField tf_startdate;

    @FXML
    private TextField tf_userid;

    @FXML
    void afficherAbonnement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Afficherabonnement.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void AjouterAbonnement(ActionEvent actionEvent) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        Date endDate = null;

        // Vérification de la saisie des champs
        if (!validateFields()) {
            return;
        }

        try {
            startDate = new Date(sdf.parse(tf_startdate.getText()).getTime());
            endDate = new Date(sdf.parse(tf_enddate.getText()).getTime());

            if (startDate.after(endDate)) {
                showAlert("Erreur de saisie", "La date de début doit être antérieure à la date de fin.");
                return;
            }


            // Vérification de l'unicité des valeurs UserID et IDPack
            int userId = Integer.parseInt(tf_userid.getText());
            int packId = Integer.parseInt(tf_idpack.getText());

            if (serviceAbonnement.isUserIdExists(userId) || serviceAbonnement.isPackIdExists(packId)) {
                showAlert("Erreur de saisie", "Les ID utilisateur et ID pack doivent être uniques.");
                return;
            }

            serviceAbonnement.ajouter(new abonnement(userId, packId, startDate, endDate));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Abonnement ajouté avec succès !");
            alert.showAndWait();

        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    // Méthode pour valider les champs de saisie
    private boolean validateFields() {
        // Expression régulière pour vérifier si le champ est un entier positif
        Pattern pattern = Pattern.compile("^\\d+$");

        if (!pattern.matcher(tf_userid.getText()).matches() || !pattern.matcher(tf_idpack.getText()).matches()) {
            showAlert("Erreur de saisie", "Les champs ID utilisateur et ID pack doivent être des entiers positifs.");
            return false;
        }

        // Vérification si les ID sont égaux à zéro
        int userId = Integer.parseInt(tf_userid.getText());
        int packId = Integer.parseInt(tf_idpack.getText());




        if (userId == 0 || packId == 0) {
            showAlert("Erreur de saisie", "Les ID utilisateur et ID pack ne peuvent pas être égaux à zéro.");
            return false;
        }

        // Vérification si les dates sont valides
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);

            // Vérification de la validité de la date de début
            Date startDate = new Date(sdf.parse(tf_startdate.getText()).getTime());
            if (startDate.getTime() <= 0) {
                showAlert("Erreur de saisie", "La date de début ne peut pas être négative ou égale à zéro.");
                return false;
            }

            // Vérification de la validité de la date de fin
            Date endDate = new Date(sdf.parse(tf_enddate.getText()).getTime());
            if (endDate.getTime() <= 0) {
                showAlert("Erreur de saisie", "La date de fin ne peut pas être négative ou égale à zéro.");
                return false;
            }
        } catch (ParseException e) {
            showAlert("Erreur de saisie", "Format de date invalide. Utilisez le format dd-MM-yyyy.");
            return false;
        }

        return true;
    }

    // Méthode pour afficher une boîte de dialogue d'alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}