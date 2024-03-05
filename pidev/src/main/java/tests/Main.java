package tests;

import entities.celebrite;
import entities.quiz;
import javafx.application.Application;
import javafx.stage.Stage;
import services.Servicecelebrite;
import services.Servicequiz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Servicequiz servicequiz= new Servicequiz();
        Servicecelebrite servicecelebrite= new Servicecelebrite();
        String dateString = "2024-02-14 12:08:26";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date DateCreation = dateFormat.parse(dateString);

           // quiz q1 = new quiz("titreABC","descriptionABC","easy", DateCreation,"teta");

             celebrite c1 = new celebrite("Picasso",DateCreation,"Africa","Artiste");

          //  servicecelebrite.ajouter(c1);

          //  servicequiz.ajouter(q1);
           // servicequiz.afficher();
           // servicequiz.supprimer(q1);

           // servicequiz.modifier(q1);

           //   System.out.println(servicequiz.afficher());

        }
        catch (ParseException e){
            System.out.println("Failed to parse date:"+ dateString);
            e.printStackTrace();
        }

    }


}
