package controllers;

import entities.Commande;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import services.ServiceCommande;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AfficherCommande {

    ServiceCommande serviceCommande = new ServiceCommande();

    @FXML
    private TableColumn<Commande, Integer> col_panierID;

    @FXML
    private TableColumn<Commande, Integer> col_userID;

    @FXML
    private TableColumn<Commande, String> col_adresseLivraison;

    @FXML
    private TableColumn<Commande, String> col_modedePaiement;

    @FXML
    private TableColumn<Commande, String> col_status;

    @FXML
    private TableColumn<Commande, String> col_orderDate;

    @FXML
    private TableColumn<Commande, Float> col_fraisLivraison;

    @FXML
    private TableColumn<Commande, Float> col_total;

    @FXML
    private TableColumn<Commande, Void> col_modifier;

    @FXML
    private TableColumn<Commande, Void> col_supprimer;

    @FXML
    private TableView<Commande> tv_commande;

    @FXML
    void initialize() {
        try {
            ObservableList<Commande> commandes = FXCollections.observableList(serviceCommande.afficher());
            tv_commande.setItems(commandes);
            col_panierID.setCellValueFactory(new PropertyValueFactory<>("idPanier"));
            col_userID.setCellValueFactory(new PropertyValueFactory<>("userID"));
            col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
            col_modedePaiement.setCellValueFactory(new PropertyValueFactory<>("modePaiement"));
            col_adresseLivraison.setCellValueFactory(new PropertyValueFactory<>("adresseLivraison"));
            col_fraisLivraison.setCellValueFactory(new PropertyValueFactory<>("fraisLivraison"));
            col_total.setCellValueFactory(new PropertyValueFactory<>("total"));

            // Convert Date to String for display
            col_orderDate.setCellValueFactory(cellData -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return new SimpleStringProperty(dateFormat.format(cellData.getValue().getOrderDate()));
            });

            // Set the cell factory for col_modifier
            col_modifier.setCellFactory(param -> new TableCell<Commande, Void>() {
                private final Button modifierButton = new Button("Modifier");

                {
                    modifierButton.setOnAction(event -> {
                        Commande commandeSelectionnee = getTableView().getItems().get(getIndex());
                        if (commandeSelectionnee != null) {
                            ModifierCommandeController controller = new ModifierCommandeController();
                            controller.initData(commandeSelectionnee);
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

            // Set the cell factory for col_supprimer
            col_supprimer.setCellFactory(param -> new TableCell<Commande, Void>() {
                private final Button supprimerButton = new Button("Supprimer");

                {
                    supprimerButton.setOnAction(event -> {
                        Commande commandeSelectionnee = getTableView().getItems().get(getIndex());
                        if (commandeSelectionnee != null) {
                            try {
                                serviceCommande.supprimer(commandeSelectionnee.getIdCommande());
                                tv_commande.getItems().remove(commandeSelectionnee);
                                System.out.println("Commande with ID " + commandeSelectionnee.getIdCommande() + " deleted successfully.");
                            } catch (SQLException e) {
                                System.out.println("Error deleting Commande: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Invalid Commande ID format.");
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
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur de chargement des commandes
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors du chargement des commandes : " + e.getMessage());
            alert.showAndWait();
        }
    }
}
