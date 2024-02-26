package tests;

import entities.User;
import services.ServiceUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ServiceUser serviceUser= new ServiceUser();
        String dateString = "2024-02-14 12:08:26";
        try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date signupDate = dateFormat.parse(dateString);

        User u2 = new User(1, "taib.mahmoudi@esprit.tn", "1234", "mahmoudi", "khalil", "1", signupDate, "Esprit rue 1er ingenieur", 50505050);



           // serviceUser.ajouter(u2);
            // serviceUser.supprimer(3);

             serviceUser.modifier(u2);
            System.out.println(serviceUser.afficher());


        }
        catch (ParseException e){
            System.out.println("Failed to parse date:"+ dateString);
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
