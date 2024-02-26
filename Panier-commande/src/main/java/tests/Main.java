package tests;
import entities.Commande;
import entities.Panier;
import services.ServiceCommande;
import services.ServicePanier;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ServiceCommande serviceCommande = new ServiceCommande() ;
        ServicePanier servicePanier = new ServicePanier();
        Panier p1 = new Panier(1,2,1,20);

        Commande c1 = new Commande(1, new Date(), "livree", "liquide", "123 Street, City", 10.5f, 100.6f);
        Commande c2 = new Commande(2, new Date(), "livree", "paypal", "456 Avenue, Town", 8.75f,150.6f);
        c1.setIdCommande(10); // Supposons que l'ID de la commande à modifier est 10
        c1.setStatus("annule");
        c1.setModePaiement("paypal");
        c1.setAdresseLivraison("whatever");
        try {
            // Create new Commandes
            serviceCommande.modifier(c1);
            serviceCommande.ajouter(c2);
            servicePanier.ajouter(p1);
            // Read all Commandes
            serviceCommande.afficher();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
