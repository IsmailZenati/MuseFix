package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a VBox to hold the menu bar and scene content
        VBox root = new VBox();

        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu userMenu = new Menu("User");

        // Create menu items
        MenuItem addUserItem = new MenuItem("Add User");
        MenuItem viewUserItem = new MenuItem("View Users");

        // Add menu items to the user menu
        userMenu.getItems().addAll(addUserItem, viewUserItem);

        // Add the user menu to the menu bar
        menuBar.getMenus().add(userMenu);

        // Add menu bar to the VBox
        root.getChildren().add(menuBar);

        // Set action for Add User menu item
        addUserItem.setOnAction(event -> {
            try {
                // Load AjouterUser.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterUser.fxml"));
                Parent addUserRoot = loader.load();
                primaryStage.setScene(new Scene(addUserRoot, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Set action for View Users menu item
        viewUserItem.setOnAction(event -> {
            try {
                // Load AfficherUser.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherUser.fxml"));
                Parent viewUserRoot = loader.load();
                primaryStage.setScene(new Scene(viewUserRoot, 600, 400));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Create and configure the primary stage
        primaryStage.setTitle("User Management System");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
