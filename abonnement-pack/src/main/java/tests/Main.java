package tests;

import entities.abonnement;
import entities.packs;
import services.ServiceAbonnement;
import services.ServicePack;

import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ServiceAbonnement serviceAbonnement= new ServiceAbonnement();
        ServicePack servicePacks = new ServicePack();

        Date date = Date.valueOf("2024-11-05");
        try {
        abonnement a1= new abonnement(5,2,date,date);
        packs p1=new packs("lambadouza" ,30.50F ,"sha9fa");
            //packs p2=new packs("fruit", 35.60F, "banane" );
        //packs p3=new packs("melon",12.5F,"bonne"  );
           serviceAbonnement.ajouter(a1);
            servicePacks.ajouter(p1);
           System.out.println(serviceAbonnement.afficher());

           // servicePacks.modifier(new packs("performant","wasze", 2, 14.5F));
         //servicePacks.ajouter(p2);
           //  servicePacks.ajouter(p3);
             System.out.println(servicePacks.afficher());


        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
