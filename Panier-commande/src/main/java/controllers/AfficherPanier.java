package controllers;

import entities.Commande;
import entities.Panier;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.collections4.Predicate;
import services.ServicePanier;

import java.io.IOException;
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
    private TableColumn<Panier, Void> col_modifier;

    @FXML
    private TableColumn<Panier, Void> col_supprimer;

    @FXML
    void initialize() throws SQLException {
        // Initialisation du prédicat de filtrage
        filterPredicate = panier -> true;

        // Mettre à jour le prédicat de filtrage lors de la saisie dans les champs de texte
        tf_prixUniteFilter.textProperty().addListener((observable, oldValue, newValue) -> updateFilterPredicate());
        tf_qteFilter.textProperty().addListener((observable, oldValue, newValue) -> updateFilterPredicate());
        ObservableList<Panier> paniers = FXCollections.observableList(servicePanier.afficher());
        tv_panier.setItems(paniers);
        // Définition des cellules de la TableView
        col_userId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        col_idProduit.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        col_qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        col_prixUnite.setCellValueFactory(new PropertyValueFactory<>("prixUnite"));
        col_sousTotal.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));

        // Ajouter les colonnes Modifier et Supprimer

        col_modifier.setCellFactory(param -> new TableCell<Panier, Void>() {
            private final Button modifierButton = new Button("Modifier");

            {
                modifierButton.setOnAction(event -> {
                    Panier panierSelectionne = getTableView().getItems().get(getIndex());
                    if (panierSelectionne != null) {
                        openModifierPanierDialog(panierSelectionne);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(modifierButton);
                }
            }
        });

        col_supprimer.setCellFactory(param -> new TableCell<Panier, Void>() {
            private final Button supprimerButton = new Button("Supprimer");

            {
                supprimerButton.setOnAction(event -> {
                    Panier panierSelectionne = getTableView().getItems().get(getIndex());
                    if (panierSelectionne != null) {
                        try {
                            servicePanier.supprimer(panierSelectionne.getIdPanier()); // Adapter la méthode supprimer selon votre implémentation
                            tv_panier.getItems().remove(panierSelectionne);
                            System.out.println("Panier with ID " + panierSelectionne.getIdPanier() + " deleted successfully.");
                        } catch (SQLException e) {
                            System.out.println("Error deleting Panier: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid Panier ID format.");
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(supprimerButton);
                }
            }
        });


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
    public void openModifierPanierDialog(Panier panier) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierPanier.fxml"));
        Parent root;
        try {
            root = loader.load();
            ModifierPanierController controller = loader.getController();
            controller.initData(panier);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openModifierPanierDialog(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
        Parent root;
        try {
            root = loader.load();
            AjouterCommandeController controller = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
