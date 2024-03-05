package services;

import entities.quiz;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Servicequiz implements IService<quiz> {
    Connection connection;

    public Servicequiz() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(quiz quiz) throws SQLException {
        // Check if the nom exists before inserting the quiz
        if (!checkNomExists(quiz.getNom())) {
            System.out.println("Error: nom does not exist in the Celebrite table");
            return;
        }

        String req = "INSERT INTO quiz (titre, description, difficulte, DateCreation, nom) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, quiz.getTitre());
        ps.setString(2, quiz.getDescription());
        ps.setString(3, quiz.getDifficulte());
        ps.setDate(4, new java.sql.Date(quiz.getDateCreation().getTime()));
        ps.setString(5, quiz.getNom());

        ps.executeUpdate();

        System.out.println("quiz ajouté");
    }

    // Method to check if the given nom exists in the Celebrite table
    private boolean checkNomExists(String nom) throws SQLException {
        String query = "SELECT * FROM Celebrite WHERE nom = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Returns true if a row is found, indicating that the nom exists
    }

    @Override
    public void modifier(String titre, quiz quiz) throws SQLException {
        String req = "UPDATE quiz SET nom=?, description=?, difficulte=?, DateCreation=? WHERE titre=?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, quiz.getNom());
        ps.setString(2, quiz.getDescription());
        ps.setString(3, quiz.getDifficulte());
        ps.setDate(4, new java.sql.Date(quiz.getDateCreation().getTime()));
        ps.setString(5, titre);

        ps.executeUpdate();
        System.out.println("quiz modifie");
    }


    @Override
    public void supprimer(String titre) throws SQLException {
        String req = "DELETE FROM quiz WHERE titre=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, titre);
        ps.executeUpdate();
        System.out.println("Quiz supprimée");
    }

    @Override
    public List<quiz> afficher() throws SQLException {
        List<quiz> quizzes = new ArrayList<>();
        String req = "SELECT * FROM quiz";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                quiz q = new quiz();
                q.setIdQuiz(rs.getInt("idQuiz"));
                q.setTitre(rs.getString("titre"));
                q.setDescription(rs.getString("description"));
                q.setDifficulte(rs.getString("difficulte"));
                q.setDateCreation(rs.getDate("DateCreation"));
                q.setNom(rs.getString("nom"));

                quizzes.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}
