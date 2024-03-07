package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controllers.LoginController;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            AnchorPane root = loader.load();  // Load as AnchorPane
            Scene loginScene = new Scene(root, 600, 400);

            // Set the login scene
            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Login");
            primaryStage.show();

            // Get the controller from the login scene
            LoginController loginController = loader.getController();
            loginController.setMainStage(primaryStage);
            loginController.setLoginScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (log it, show an alert, etc.)
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
