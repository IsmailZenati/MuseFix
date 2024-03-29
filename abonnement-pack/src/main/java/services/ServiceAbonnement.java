package services;

import entities.abonnement;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAbonnement implements IService<abonnement> {
    Connection connection;

    public ServiceAbonnement() {
        connection = MyDatabase.getInstance().getConnection();
    }

    public void ajouter(abonnement abonnement) throws SQLException {

        String req = "INSERT INTO abonnement (userId, idPack, dateDeb, dateFin) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, abonnement.getUserId());
        ps.setInt(2, abonnement.getIdPack());
        ps.setDate(3, abonnement.getDateDeb());
        ps.setDate(4, abonnement.getDateFin());
        ps.executeUpdate();
        System.out.println("abonnement ajouté");
    }

    public void modifier(abonnement abonnement) throws SQLException {
        String req = "UPDATE abonnement SET dateDeb = ?, dateFin = ? WHERE IdAbonnement = ?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setDate(1, abonnement.getDateDeb());
        ps.setDate(2, abonnement.getDateFin());
        ps.setInt(3, abonnement.getIdAbonnement());
        ps.executeUpdate();
        System.out.println("abonnement modifié");
    }

    public void supprimer(int IdAbonnement) throws SQLException {
        String req = "DELETE FROM abonnement WHERE IdAbonnement = ? ";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, IdAbonnement);
        ps.executeUpdate();
        System.out.println("abonnement supprimé");
    }

    @Override
    public List<abonnement> afficher() throws SQLException {
        List<abonnement> abonnements = new ArrayList<>();
        String req = "SELECT * FROM abonnement";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                abonnement ab = new abonnement();
                ab.setIdAbonnement(resultSet.getInt("IdAbonnement"));
                ab.setUserId(resultSet.getInt("userID"));
                ab.setIdPack(resultSet.getInt("idPack"));
                ab.setDateDeb(resultSet.getDate("dateDeb"));
                ab.setDateFin(resultSet.getDate("dateFin"));
                abonnements.add(ab);
            }
        }
        return abonnements;
    }

    // Méthode pour vérifier si un UserID existe déjà dans la base de données
    public boolean isUserIdExists(int userId) throws SQLException {
        String req = "SELECT COUNT(*) AS count FROM abonnement WHERE userId = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("count") > 0;
    }

    // Méthode pour vérifier si un IDPack existe déjà dans la base de données
    public boolean isPackIdExists(int packId) throws SQLException {
        String req = "SELECT COUNT(*) AS count FROM abonnement WHERE idPack = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, packId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("count") > 0;
    }
}
