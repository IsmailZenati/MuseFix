package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.concurrent.Task;
import javafx.scene.control.Alert.AlertType;

public class FrontendController {

    @FXML
    private ChoiceBox<String> packChoiceBox;

    @FXML
    private Button getPremiumButton;

    @FXML
    private Button learnMoreButton;

    @FXML
    private Button buyPackButton;

    @FXML
    private TextField subscriptionIDTextField;

    @FXML
    private ProgressIndicator progressindicator;

    @FXML
    private ProgressIndicator progressindicator2;

    @FXML
    private void initialize() {
        // Hide progress indicator initially
        progressindicator.setVisible(false);
        progressindicator2.setVisible(false);
    }

    @FXML
    private void ajouterabonnement(ActionEvent event) {
        // Show progress indicator
        progressindicator.setVisible(true);

        // Simulate some task (e.g., API call) with a delay of 5 seconds
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(5000); // Simulate a delay of 5 seconds
                return null;
            }
        };

        // When the task is finished (after 5 seconds), hide the progress indicator and show a message
        task.setOnSucceeded(e -> {
            progressindicator.setVisible(false);
            showAlert(AlertType.INFORMATION, "Success", "You have successfully obtained the premium Subscription!");
        });

        // Start the task
        new Thread(task).start();
    }

    @FXML
    private void handleLearnMoreButtonClick() {
        showAlert(AlertType.INFORMATION, "Subscription Confirmed", "Your subscription has been confirmed.");
    }

    @FXML
    private void ajouterpack(ActionEvent event) throws IOException {
        progressindicator2.setVisible(true);

        // Start a background task to simulate a time-consuming operation
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate a delay of 5 seconds
                Thread.sleep(5000);
                return null;
            }
        };

        // When the task is finished (after 5 seconds), update the UI
        task.setOnSucceeded(e -> {
            progressindicator2.setVisible(false); // Hide the progress indicator
            showAlert(AlertType.INFORMATION, "Success", "The pack purchase has been completed successfully.");
        });

        // Start the task in a background thread
        new Thread(task).start();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
