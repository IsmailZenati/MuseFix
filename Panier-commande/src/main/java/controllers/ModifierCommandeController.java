package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceCommande;
import entities.Commande;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifierCommandeController {

    @FXML
    private Button modifierCommande;

    @FXML
    private Button supprimerCommande;


    @FXML
    private TextField tf_adresseLivraison;

    @FXML
    private TextField tf_fraisLivraison;

    @FXML
    private TextField tf_idCommande;

    @FXML
    private TextField tf_modePaiement;

    @FXML
    private TextField tf_orderDate;

    @FXML
    private TextField tf_status;

    @FXML
    private TextField tf_total;

    @FXML
    private TextField tf_userID;

    @FXML
    private TextField tf_idPanier;
    private ServiceCommande serviceCommande;

    public ModifierCommandeController() {
        // Initialize the service class
         serviceCommande = new ServiceCommande();
    }

    @FXML
    void initialize() {
        if (modifierCommande != null) {
            modifierCommande.setOnAction(event -> {
                try {
                    // Get the ID of the Commande to modify
                    int idCommande = Integer.parseInt(tf_idCommande.getText());
                    int idPanier = Integer.parseInt(tf_idPanier.getText());
                    // Get values from text fields for modification
                    int userId = Integer.parseInt(tf_userID.getText());
                    String adresseLivraison = tf_adresseLivraison.getText();
                    float fraisLivraison = Float.parseFloat(tf_fraisLivraison.getText());
                    String status = tf_status.getText();
                    String modePaiement = tf_modePaiement.getText();
                    float total = Float.parseFloat(tf_total.getText());
                    Date DateCommande = parseDate(tf_orderDate.getText());

                    // Create a new quiz object with updated values
                    Commande modifiedCommande = new Commande(idCommande,idPanier, DateCommande, status, modePaiement, adresseLivraison,fraisLivraison,total);

                    // Call the service method to modify the command
                    serviceCommande.modifier(modifiedCommande);

                    // Show success message
                    System.out.println("Commande with ID " + userId + " modified successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid input format.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error modifying Commande: " + ex.getMessage());
                }
            });
        }

        if (supprimerCommande != null) {
            supprimerCommande.setOnAction(event -> {
                try {
                    // Get the ID of the quiz to delete
                    int idCommande = Integer.parseInt(tf_idCommande.getText());

                    // Delete the quiz based on ID
                    serviceCommande.supprimer(idCommande);

                    // Show success message
                    System.out.println("Commande with ID " + idCommande + " deleted successfully.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Invalid Commande ID format.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Error deleting Commande: " + ex.getMessage());
                }
            });
        }
    }

    // Method to parse date string to Date object
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
            return null;
        }
    }
}