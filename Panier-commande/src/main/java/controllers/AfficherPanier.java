package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import entities.Panier;
import services.ServicePanier;

import java.sql.SQLException;

public class AfficherPanier {

    ServicePanier servicePanier = new ServicePanier();

    @FXML
    private TableView<Panier> tv_panier;

    @FXML
    private TextField tf_prixUniteFilter;

    @FXML
    private TextField tf_qteFilter;

    @FXML
    private TableColumn<Panier, Integer> col_idProduit;

    @FXML
    private TableColumn<Panier, Float> col_prixUnite;

    @FXML
    private TableColumn<Panier, Integer> col_qte;

    @FXML
    private TableColumn<Panier, Float> col_sousTotal;

    @FXML
    private TableColumn<Panier, Integer> col_userId;

    private ObservableList<Panier> paniersData = FXCollections.observableArrayList();

    private Predicate<Panier> filterPredicate;

    @FXML
    void initialize() {
        // Initialisation du prédicat de filtrage
        filterPredicate = panier -> true;

        // Mettre à jour le prédicat de filtrage lors de la saisie dans les champs de texte
        tf_prixUniteFilter.textProperty().addListener((observable, oldValue, newValue) -> updateFilterPredicate());
        tf_qteFilter.textProperty().addListener((observable, oldValue, newValue) -> updateFilterPredicate());

        // Définition des cellules de la TableView
        col_userId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_prixUnite.setCellValueFactory(new PropertyValueFactory<>("prixUnite"));
        col_sousTotal.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));

        // Charger et afficher les données initiales
        try {
            afficherDonnees();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void afficherDonnees() throws SQLException {
        // Récupérer les données initiales et appliquer le filtrage
        paniersData.setAll(servicePanier.afficher());
        applyFilter();
    }

    private void updateFilterPredicate() {
        // Mettre à jour le prédicat de filtrage en fonction des valeurs saisies par l'utilisateur
        filterPredicate = panier -> {
            boolean qtePass = true;
            boolean prixPass = true;

            // Vérifier si la quantité est saisie et respecte le filtre
            if (!tf_qteFilter.getText().isEmpty()) {
                qtePass = panier.getQte() >= Integer.parseInt(tf_qteFilter.getText());
            }

            // Vérifier si le prix unitaire est saisie et respecte le filtre
            if (!tf_prixUniteFilter.getText().isEmpty()) {
                prixPass = panier.getPrixUnite() <= Float.parseFloat(tf_prixUniteFilter.getText());
            }

            return qtePass && prixPass;
        };

        // Appliquer le nouveau prédicat de filtrage
        applyFilter();
    }

    private void applyFilter() {
        // Appliquer le prédicat de filtrage et mettre à jour la TableView
        ObservableList<Panier> filteredPaniers = FXCollections.observableArrayList();
        for (Panier panier : paniersData) {
            if (filterPredicate.evaluate(panier)) {
                filteredPaniers.add(panier);
            }
        }
        tv_panier.setItems(filteredPaniers);
    }

    @FXML
    void filtrerDonnees() {
        updateFilterPredicate();
    }

}
