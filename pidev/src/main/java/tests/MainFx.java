package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {

    public static void main(String[] args) {launch(args);}
/**/
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjouterQuiz.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des Quiz");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de AjouterQuiz.fxml : " + e.getMessage());
        }
    }
}
/*

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjouterCelebrite.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des Celebrites");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de AjouterCelebrite.fxml : " + e.getMessage());
        }
    }
}
 */