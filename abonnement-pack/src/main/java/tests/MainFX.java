package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjouterPack.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/AfficherPack.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Parent root2 = fxmlLoader2.load();

            Scene scene = new Scene(root);
            Scene scene2 = new Scene(root2);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Ajouter les packs");
            primaryStage.show();

            Stage secondaryStage = new Stage();
            secondaryStage.setScene(scene2);
            secondaryStage.setTitle(" Afficher les packs");
            secondaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}