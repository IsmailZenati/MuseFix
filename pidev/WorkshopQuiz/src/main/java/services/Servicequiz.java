package services;


import entities.quiz;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;

public class Servicequiz implements  IService<quiz>{
    Connection connection;
    public Servicequiz(){
        connection= MyDatabase.getInstance().getConnection();

    }
    @Override
    public void ajouter(quiz quiz) throws SQLException {
        // Check if the idCelebrite exists before inserting the quiz
        if (!checkCelebriteExists(quiz.getidCelebrite())) {
            System.out.println("Error: idCelebrite does not exist");
            return;
        }

        String req ="insert into quiz (titre , description, difficulte, DateCreation, idCelebrite)"+
                "values(?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setString(1, quiz.gettitre());
        ps.setString(2, quiz.getdescription());
        ps.setString(3, quiz.getdifficulte());
        ps.setTimestamp(4, new Timestamp(quiz.getDateCreation().getTime()));
        ps.setInt(5, quiz.getidCelebrite());

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
        String req="update quiz set  titre=? ,description=? , difficulte=? , DateCreation=? ,idCelebrite=? where idQuiz=?";
        PreparedStatement ps= connection.prepareStatement(req);

        ps.setInt(1, quiz.getidCelebrite());
        ps.setString(2, quiz.gettitre());
        ps.setString(3, quiz.getdescription());
        ps.setString(4, quiz.getdifficulte());
        ps.setDate(5, new java.sql.Date(quiz.getDateCreation().getTime()));
        ps.setInt(6, quiz.getidQuiz());

        ps.executeUpdate();
        System.out.println("quiz modifie");

    }

    @Override
    public void supprimer(int idQuiz) throws SQLException {
        String req = "DELETE FROM quiz WHERE idQuiz=?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,idQuiz);
        ps.executeUpdate();
        System.out.println("Quiz supprimée");

    }

    @Override
    public List<quiz> afficher() throws SQLException {

        List<quiz> quizzes= new ArrayList<>();
        String req="select * from quiz";

        try(Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req)){

       while (rs.next()){
           quiz q = new quiz();
           q.setidQuiz(rs.getInt("idQuiz"));
           q.settitre(rs.getString("titre"));
           q.setdescription(rs.getString("description"));
           q.setdifficulte(rs.getString("difficulte"));
           q.setidCelebrite(rs.getInt("idCelebrite"));

           Timestamp timestamp = rs.getTimestamp("DateCreation");
           if (timestamp != null) {
               q.setDateCreation(new Date(timestamp.getTime()));
           }
           quizzes.add(q);
       }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return quizzes;
    }
}
