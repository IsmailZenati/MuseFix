package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.stage.Stage;
import services.AuthenticationService;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private MFXTextField usernameField;

    @FXML
    private MFXPasswordField CustomPasswordField;

    @FXML
    private MFXButton loginButton;

    @FXML
    private Label errorMessage;
    @FXML

    private Stage mainStage;
    @FXML

    private Scene loginScene;


    private final AuthenticationService authenticationService = new AuthenticationService();

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = CustomPasswordField.getText();

        try {
            boolean isAuthenticated = authenticationService.authenticate(username, password);

            if (isAuthenticated) {
                // Check if the user is an admin or not
                boolean isAdmin = authenticationService.isAdmin(username);

                if (isAdmin) {
                    loadAdminScene();
                } else {
                    loadUserScene();
                }
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "An error occurred while authenticating the user.");
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }

    private void loadAdminScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminMain.fxml"));
            Parent adminMainRoot = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(adminMainRoot, 600, 400));
            stage.setTitle("Admin Main");
            stage.show();

            // Close the login window
            ((Stage) usernameField.getScene().getWindow()).close();
        } catch (IOException e) {
            showAlert("Error", "Failed to load Admin Main scene.");
            e.printStackTrace();
        }
    }


    private void loadUserScene() {
        // Load the user main scene
        // You can implement this method according to your file structure
    }
    @FXML
    private void initialize() {
        // Disable login button initially
        loginButton.setDisable(true);

        // Add listeners to enable/disable the button based on input validation
        usernameField.textProperty().addListener((observable, oldValue, newValue) ->
                updateLoginButtonState());

        CustomPasswordField.textProperty().addListener((observable, oldValue, newValue) ->
                updateLoginButtonState());
    }

    private void updateLoginButtonState() {
        String username = usernameField.getText();
        String password = CustomPasswordField.getText();
        boolean isInputValid = !username.isEmpty() && !password.isEmpty();

        // Enable/disable login button based on input validation
        loginButton.setDisable(!isInputValid);
    }
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
