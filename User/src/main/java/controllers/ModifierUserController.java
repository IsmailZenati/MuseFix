package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Encryptor;
import services.ServiceUser;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

public class ModifierUserController {

    private final ServiceUser serviceUser = new ServiceUser();
    private final Encryptor encryptor = new Encryptor();

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField tf_password;


    @FXML
    private TextField tf_role;

    @FXML
    private TextField tf_tel;

    @FXML
    private TextField tf_adresse;

    @FXML
    private DatePicker dp_signUpDate;

    private User selectedUser;

    public void initData(User user) {
        selectedUser = user;
        populateFields();
    }

    private void populateFields() {
        if (selectedUser != null) {
            tf_nom.setText(selectedUser.getNom());
            tf_prenom.setText(selectedUser.getPrenom());
            tf_email.setText(selectedUser.getEmail());
            tf_password.setText(selectedUser.getPassword());
            tf_role.setText(selectedUser.getRole());
            tf_tel.setText(String.valueOf(selectedUser.getTel()));
            tf_adresse.setText(selectedUser.getAdresse());
            if (selectedUser.getSignupDate() != null) {
                dp_signUpDate.setValue(selectedUser.getSignupDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
        }
    }

    @FXML
    void modifier(ActionEvent event) {
        if (selectedUser != null) {
            // Update user data
            selectedUser.setNom(tf_nom.getText());
            selectedUser.setPrenom(tf_prenom.getText());
            selectedUser.setEmail(tf_email.getText());

            try {
                String encryptedPassword = encryptor.encryptString(tf_password.getText());
                selectedUser.setPassword(encryptedPassword); // Encrypt the new password
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                // Handle encryption error
            }
            selectedUser.setRole(tf_role.getText());
            selectedUser.setTel(Integer.parseInt(tf_tel.getText()));
            selectedUser.setAdresse(tf_adresse.getText());
            Date date = Date.from(dp_signUpDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            selectedUser.setSignupDate(date);

            try {
                serviceUser.modifier(selectedUser);
                // Handle successful modification
                // You can add code here to show a success message or navigate back to the AfficherUser.fxml file
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle modification error
                // You can add code here to show an error message
            }
        }
    }


    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the stage from the action event
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
