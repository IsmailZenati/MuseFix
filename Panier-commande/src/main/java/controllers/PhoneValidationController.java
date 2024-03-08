package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServicePhone;
import java.io.IOException;

public class PhoneValidationController {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button validateButton;

    @FXML
    private ImageView imageView; // Ajout du champ imageView



    @FXML
    void validatePhoneNumber(ActionEvent event) {
        String phoneNumber = phoneNumberField.getText();
        try {
            boolean isValid = ServicePhone.validateNumber(phoneNumber);
            if (isValid) {
                showAlert("Numéro de téléphone valide", "Le numéro de téléphone est valide. Nous vous contacterons à ce numéro.");
            } else {
                showAlert("Numéro de téléphone invalide", "Le numéro de téléphone n'est pas valide.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while validating the phone number.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
