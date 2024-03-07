package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServicePhone;
import java.io.IOException;

public class PhoneValidationController {

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button validateButton;

    @FXML
    void validatePhoneNumber(ActionEvent event) {
        String phoneNumber = phoneNumberField.getText();
        try {
            boolean isValid = ServicePhone.validateNumber(phoneNumber);
            if (isValid) {
                showAlert("Valid Phone Number", "The phone number is valid.");
            } else {
                showAlert("Invalid Phone Number", "The phone number is not valid.");
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
