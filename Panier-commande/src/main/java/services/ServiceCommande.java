package services;
import entities.Commande;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommande implements IService<Commande> {
    Connection connection;

    public ServiceCommande() {
        connection = MyDatabase.getInstance().getConnection();
    }



    // CRUD Operations

    // Create
    public void ajouter(Commande commande) throws SQLException {
        // Check if the userID exists in the user table or in another source of data
        if (!checkUserIDExists(commande.getUserID())) {
            System.out.println("Error: userID does not exist");
            return;
        }
        String req = "INSERT INTO commande (idPanier,userID, orderDate, status, modePaiement, adresseLivraison, fraisLivraison, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, commande.getIdPanier());
            // Utilisation de l'ID utilisateur fourni sans vérifier son existence
            statement.setInt(2, commande.getUserID());
            statement.setTimestamp(3, new java.sql.Timestamp(commande.getOrderDate().getTime()));
            statement.setString(4, commande.getStatus());
            statement.setString(5, commande.getModePaiement());
            statement.setString(6, commande.getAdresseLivraison());
            statement.setFloat(7, commande.getFraisLivraison());
            statement.setFloat(8, commande.getTotal());
            statement.executeUpdate();
        }
    }

    // Méthode pour vérifier si un utilisateur existe dans la table user ou dans une autre source de données valide
    private boolean checkUserIDExists(int userID) throws SQLException {
        String query = "SELECT * FROM user WHERE userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Returns true if a row is found, indicating that the userID exists
            }
        }
    }

    // Read
    public List<Commande> afficher() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM commande";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {
            while (resultSet.next()) {
                Commande commande = new Commande(
                        resultSet.getInt("idPanier"),
                        resultSet.getInt("userID"),
                        resultSet.getDate("orderDate"),
                        resultSet.getString("status"),
                        resultSet.getString("modePaiement"),
                        resultSet.getString("adresseLivraison"),
                        resultSet.getFloat("fraisLivraison"),
                        resultSet.getFloat("total")
                );
                commande.setIdCommande(resultSet.getInt("idCommande"));
                commandes.add(commande);
            }
        }
        return commandes;
    }


    // Update
    public void modifieradmin(Commande commande) throws SQLException {
        String req = "UPDATE commande SET status = ? WHERE idCommande = ?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, commande.getStatus());
            statement.setInt(2, commande.getIdCommande());
            statement.executeUpdate();
        }
    }
    public void modifier(Commande commande) throws SQLException {
        String req = "UPDATE commande SET modePaiement = ?,adresseLivraison = ? WHERE idCommande = ?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, commande.getModePaiement());
            statement.setString(2, commande.getAdresseLivraison());
            statement.setInt(3, commande.getIdCommande());
            statement.executeUpdate();
        }
    }


    // Delete
    public void supprimer(int idCommande) throws SQLException {
        String req = "DELETE FROM commande WHERE idCommande = ?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, idCommande);
            statement.executeUpdate();
        }
    }



}
