package services;

import entities.celebrite;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Servicecelebrite implements IService<celebrite> {
    Connection connection;

    public Servicecelebrite() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(celebrite celebrite) throws SQLException {
        String req = "insert into celebrite (nom, dateNais, nationalite, profession)" + "values(?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, celebrite.getNom());
        ps.setTimestamp(2, new Timestamp(celebrite.getDateNais().getTime()));
        ps.setString(3, celebrite.getNationalite());
        ps.setString(4, celebrite.getProfession());

        ps.executeUpdate();

        System.out.println("celebrite ajouté");
    }

    @Override
    public void modifier(celebrite celebrite) throws SQLException {
        String req = "update celebrite set nom=?, dateNais=?, nationalite=?, profession=?  where idCelebrite=?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, celebrite.getNom());
        ps.setDate(2, new java.sql.Date(celebrite.getDateNais().getTime()));
        ps.setString(3, celebrite.getNationalite());
        ps.setString(4, celebrite.getProfession());
        ps.setInt(5, celebrite.getIdCelebrite());

        ps.executeUpdate();
        System.out.println("celebrite modifie");
    }

    @Override
    public void supprimer(int idCelebrite) throws SQLException {
        String req = "DELETE FROM celebrite WHERE idCelebrite=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idCelebrite);
        ps.executeUpdate();
        System.out.println("celebrite supprimée");
    }

    @Override
    public List<celebrite> afficher() throws SQLException {
        List<celebrite> celebrites = new ArrayList<>();
        String req = "select * from celebrite";

        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                celebrite c = new celebrite();
                c.setIdCelebrite(rs.getInt("idCelebrite"));
                c.setNom(rs.getString("nom"));
                c.setNationalite(rs.getString("nationalite"));
                c.setProfession(rs.getString("profession"));

                Timestamp timestamp = rs.getTimestamp("dateNais");
                if (timestamp != null) {
                    c.setDateNais(new Date(timestamp.getTime()));
                }
                celebrites.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return celebrites;
    }
}
