package tests;

import entities.abonnement;
import entities.packs;
import services.ServiceAbonnement;
import services.ServicePack;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
        ServicePack servicePacks = new ServicePack();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Creer les dates
            Date startDate = Date.valueOf("2015-07-12");
            Date endDate = Date.valueOf("2018-02-10");

            // creer un objet abonnement
            abonnement a = new abonnement(12, startDate, endDate);

            // ajouter un  abonnement
            serviceAbonnement.modifier(a);

            // afficher la liste des abonnements
            List<abonnement> abonnements = serviceAbonnement.afficher();
            for (abonnement ab : abonnements) {
                System.out.println(ab);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
