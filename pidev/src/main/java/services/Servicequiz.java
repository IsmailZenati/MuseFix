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
        // Check if the idCelebrite exists before inserting the quiz
        if (!checkCelebriteExists(quiz.getIdCelebrite())) {
            System.out.println("Error: idCelebrite does not exist");
            return;
        }

        String req = "INSERT INTO quiz (titre, description, difficulte, DateCreation, idCelebrite) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, quiz.getTitre());
        ps.setString(2, quiz.getDescription());
        ps.setString(3, quiz.getDifficulte());
        ps.setDate(4, new java.sql.Date(quiz.getDateCreation().getTime()));
        ps.setInt(5, quiz.getIdCelebrite());

        ps.executeUpdate();

        System.out.println("quiz ajouté");
    }

    // Method to check if the given idCelebrite exists in the Celebrite table
    private boolean checkCelebriteExists(int idCelebrite) throws SQLException {
        String query = "SELECT * FROM Celebrite WHERE idCelebrite = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idCelebrite);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // Returns true if a row is found, indicating that the idCelebrite exists
    }

    @Override
    public void modifier(quiz quiz) throws SQLException {
        String req = "UPDATE quiz SET titre=?, description=?, difficulte=?, DateCreation=?, idCelebrite=? WHERE idQuiz=?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, quiz.getTitre());
        ps.setString(2, quiz.getDescription());
        ps.setString(3, quiz.getDifficulte());
        ps.setDate(4, new java.sql.Date(quiz.getDateCreation().getTime()));
        ps.setInt(5, quiz.getIdCelebrite());
        ps.setInt(6, quiz.getIdQuiz());

        ps.executeUpdate();
        System.out.println("quiz modifie");
    }

    @Override
    public void supprimer(int idQuiz) throws SQLException {
        String req = "DELETE FROM quiz WHERE idQuiz=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1, idQuiz);
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
                q.setIdCelebrite(rs.getInt("idCelebrite"));

                quizzes.add(q);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}
