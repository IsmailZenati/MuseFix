package services;

import entities.Panier;
import utils.MyDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier {
    private Connection connection;

    public ServicePanier() {
        connection = MyDatabase.getInstance().getConnection();

    }

    // CRUD Operations

    // Create
    public void ajouter(Panier panier) throws SQLException {
        String req = "INSERT INTO panier (userID, idProduit, qte, prixUnite, sousTotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, panier.getUserID());
            statement.setInt(2, panier.getIdProduit());
            statement.setInt(3, panier.getQte());
            statement.setFloat(4, panier.getPrixUnite());
            statement.setFloat(5, panier.getSousTotal());
            statement.executeUpdate();
        }
    }

    // Read
    public List<Panier> afficher() throws SQLException {
        List<Panier> paniers = new ArrayList<>();
        String req = "SELECT * FROM panier";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {
            while (resultSet.next()) {
                Panier panier = new Panier(
                        resultSet.getInt("userID"),
                        resultSet.getInt("idProduit"),
                        resultSet.getInt("qte"),
                        resultSet.getFloat("prixUnite")
                );
                panier.setSousTotal(resultSet.getFloat("sousTotal"));
                panier.setIdPanier(resultSet.getInt("idPanier"));
                paniers.add(panier);
            }
        }
        return paniers;
    }

    // Update
    public void modifier(Panier panier) throws SQLException {
        String req = "UPDATE panier SET qte = ?, prixUnite = ?, sousTotal = ? WHERE idPanier = ?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, panier.getQte());
            statement.setFloat(2, panier.getPrixUnite());
            statement.setFloat(3, panier.getSousTotal());
            statement.setInt(4, panier.getIdPanier());
            statement.executeUpdate();
        }
    }

    // Delete
    public void supprimer(int idPanier) throws SQLException {
        String req = "DELETE FROM panier WHERE idPanier = ?";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, idPanier);
            statement.executeUpdate();
        }
    }




}
