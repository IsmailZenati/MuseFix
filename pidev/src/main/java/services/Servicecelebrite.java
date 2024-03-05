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
    public void modifier(String nom, celebrite celebrite) throws SQLException {
        String req = "UPDATE celebrite SET dateNais=?, nationalite=?, profession=? WHERE nom=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setTimestamp(1, new Timestamp(celebrite.getDateNais().getTime()));
            ps.setString(2, celebrite.getNationalite());
            ps.setString(3, celebrite.getProfession());
            ps.setString(4, nom); // use the original nom to identify the celebrite to modify
            ps.executeUpdate();
            System.out.println("Celebrite modifiée");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void supprimer(String nom) throws SQLException {
        String req = "DELETE FROM celebrite WHERE nom=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, nom);
            ps.executeUpdate();
            System.out.println("Celebrite supprimée");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
