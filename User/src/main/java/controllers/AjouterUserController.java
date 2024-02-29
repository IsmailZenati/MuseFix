package controllers;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceUser;
import services.Encryptor;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AjouterUserController {
    private final Encryptor encryptor = new Encryptor();

    private final ServiceUser serviceUser = new ServiceUser();

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_role;

    @FXML
    private TextField tf_tel;

    @FXML
    private TextField tf_adresse;

    @FXML
    private DatePicker dp_signUpDate;

    @FXML
    void ajouter(ActionEvent event) {
        try {
            User newUser = new User();
            newUser.setEmail(tf_email.getText());
            newUser.setNom(tf_nom.getText());
            newUser.setPrenom(tf_prenom.getText());

// Inside your method where you want to encrypt a password
            String encryptedPassword = encryptor.encryptString(tf_password.getText());            newUser.setPassword(encryptedPassword); // Encrypt the password
            newUser.setRole(tf_role.getText());
            newUser.setAdresse(tf_adresse.getText());
            newUser.setTel(Integer.parseInt(tf_tel.getText()));
            newUser.setSignupDate(java.sql.Date.valueOf(dp_signUpDate.getValue()));
            serviceUser.ajouter(newUser);
            showAlert("Success", "Personne ajoutée avec succès");
        } catch (SQLException e) {
            showAlert("Error", "Erreur lors de l'ajout de la personne: " + e.getMessage());
        } catch (NumberFormatException e) {
            showAlert("Error", "Veuillez saisir un numéro de téléphone valide.");
        } catch (NoSuchAlgorithmException e) {
            showAlert("Error", "Erreur lors de l'encryption du mot de passe: " + e.getMessage());
        }
    }

    @FXML
    void annuler(ActionEvent event) {
        clearFields();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        tf_email.clear();
        tf_nom.clear();
        tf_prenom.clear();
        tf_password.clear();
        tf_role.clear();
        tf_tel.clear();
        tf_adresse.clear();
        dp_signUpDate.setValue(null);
    }
}
