package services;

import entities.packs;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePack implements IService<packs> {
    Connection connection;

    public ServicePack() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(packs pack) throws SQLException {
        String req = "INSERT INTO pack (typePack, prix,avantages) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, pack.getTypePack());
            preparedStatement.setFloat(2, pack.getPrix());
            preparedStatement.setString(3, pack.getAvantages());
            preparedStatement.executeUpdate();
            System.out.println("Pack ajouté");

    }

    @Override
    public void modifier(packs pack) throws SQLException {
        String req = "UPDATE pack SET typePack = ?, prix = ?, avantages = ? WHERE IdPack = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, pack.getTypePack());
            preparedStatement.setFloat(2, pack.getPrix());
            preparedStatement.setString(3, pack.getAvantages());
            preparedStatement.setInt(4 , pack.getIdPack());
            preparedStatement.executeUpdate();
            System.out.println("Pack modifié");

        }
    }

    @Override
    public void supprimer(int idPack) throws SQLException {
        String req = "DELETE FROM pack WHERE IdPack=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, idPack);
            preparedStatement.executeUpdate();
            System.out.println("Pack supprimé");
        }
    }

 @Override
    public List<packs> afficher() throws SQLException {
        List<packs> packs = new ArrayList<>();
        String req = "SELECT * FROM pack";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {
            while (resultSet.next()) {
                packs pack = new packs();

                pack.setTypePack(resultSet.getString("typePack"));
                pack.setPrix(resultSet.getFloat("prix"));
                pack.setAvantages(resultSet.getString("avantages"));
                pack.setIdPack(resultSet.getInt("IdPack"));
                packs.add(pack);
            }
        }
        return packs;
    }
}
