package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des commandes");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Charger une deuxième interface FXML
            Parent root2 = FXMLLoader.load(getClass().getResource("/AjouterPanier.fxml"));

            // Créer une nouvelle scène avec la deuxième interface FXML chargée
            Scene scene2 = new Scene(root2);

            // Créer une nouvelle fenêtre pour la deuxième scène
            Stage secondStage = new Stage();
            secondStage.setScene(scene2);
            secondStage.setTitle("Gestion du panier");
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Charger une deuxième interface FXML
            Parent root2 = FXMLLoader.load(getClass().getResource("/ModifierPanier.fxml"));

            // Créer une nouvelle scène avec la deuxième interface FXML chargée
            Scene scene2 = new Scene(root2);

            // Créer une nouvelle fenêtre pour la deuxième scène
            Stage secondStage = new Stage();
            secondStage.setScene(scene2);
            secondStage.setTitle("Gestion du panier");
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Charger une deuxième interface FXML
            Parent root2 = FXMLLoader.load(getClass().getResource("/ModifierCommande.fxml"));

            // Créer une nouvelle scène avec la deuxième interface FXML chargée
            Scene scene2 = new Scene(root2);

            // Créer une nouvelle fenêtre pour la deuxième scène
            Stage secondStage = new Stage();
            secondStage.setScene(scene2);
            secondStage.setTitle("Gestion des commandes");
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Charger une deuxième interface FXML
            Parent root2 = FXMLLoader.load(getClass().getResource("/Stripe.fxml"));

            // Créer une nouvelle scène avec la deuxième interface FXML chargée
            Scene scene2 = new Scene(root2,400,400);

            // Créer une nouvelle fenêtre pour la deuxième scène
            Stage secondStage = new Stage();
            secondStage.setScene(scene2);
            secondStage.setTitle("Gestion de paiement");
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            // Charger une deuxième interface FXML
            Parent root2 = FXMLLoader.load(getClass().getResource("/Phonevalidate.fxml"));

            // Créer une nouvelle scène avec la deuxième interface FXML chargée
            Scene scene2 = new Scene(root2,400,400);

            // Créer une nouvelle fenêtre pour la deuxième scène
            Stage secondStage = new Stage();
            secondStage.setScene(scene2);
            secondStage.setTitle("Vérification du numéro de téléphone");
            secondStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
