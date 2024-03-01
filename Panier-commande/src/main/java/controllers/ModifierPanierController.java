package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import services.ServicePanier;
import entities.Panier;
import java.sql.SQLException;



public class ModifierPanierController {

    @FXML
    private Button modifierPanier;

    @FXML
    private Button supprimerPanier;

    @FXML
    private TextField tf_idPanier;

    @FXML
    private TextField tf_prixUnite;

    @FXML
    private TextField tf_qte;

    @FXML
    private TextField tf_idProduit;

    private ServicePanier servicePanier;

    public ModifierPanierController() {
        // Initialize the service class
        servicePanier = new ServicePanier();
    }

    @FXML
    void initialize() {
        if (modifierPanier != null) {
            modifierPanier.setOnAction(event -> {
                try {
                    // Get the ID of the panier to modify
                    int idPanier = Integer.parseInt(tf_idPanier.getText());

                    // Get values from text fields for modification
                    int qte = Integer.parseInt(tf_qte.getText());
                    float prixUnite = Float.parseFloat(tf_prixUnite.getText());
                    int idProduit = Integer.parseInt(tf_idProduit.getText());

                    // Create a new panier object with updated values
                    Panier modifiedPanier = new Panier(idPanier, idProduit, qte, prixUnite);

                    // Call the service method to modify the panier
                    servicePanier.modifier(modifiedPanier);

                    // Show success message
                    System.out.println("Produit avec ID " + idProduit + " modifié avec succès.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Format de saisie invalide.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Erreur en modifiant le Panier: " + ex.getMessage());
                }
            });
        }

        if (supprimerPanier != null) {
            supprimerPanier.setOnAction(event -> {
                try {
                    // Get the ID of the quiz to delete
                    int idPanier = Integer.parseInt(tf_idPanier.getText());

                    // Delete the quiz based on ID
                    servicePanier.supprimer(idPanier);

                    // Show success message
                    System.out.println("Panier avec ID " + idPanier + " supprimé avec succès.");

                } catch (NumberFormatException e) {
                    // Handle invalid input
                    System.out.println("Format d'ID de Panier invalide.");
                } catch (SQLException ex) {
                    // Handle SQL exception
                    System.out.println("Erreur en supprimant le Panier: " + ex.getMessage());
                }
            });
        }
    }
}


