package controllers;
import com.stripe.param.ChargeCreateParams;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.abonnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceAbonnement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class AfficherAbonnementController {

    @FXML
    private TableColumn<abonnement, Date> col_datedebut;

    @FXML
    private TableColumn<abonnement, Date> col_datefin;

    @FXML
    private TableColumn<abonnement, Integer> col_idabonnement;

    @FXML
    private TableColumn<abonnement, Integer> col_iduser;

    @FXML
    private TableColumn<abonnement, Integer> col_idpack;

    @FXML
    private TableView<abonnement> tv_abonnement;

    @FXML
    private Button gererqrcode;

    @FXML
    void initialize() {
        try {
            ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
            ObservableList<abonnement> abonnements = FXCollections.observableArrayList(serviceAbonnement.afficher());
            System.out.println(abonnements);
            tv_abonnement.setItems(abonnements);
            col_idabonnement.setCellValueFactory(new PropertyValueFactory<>("idAbonnement"));
            col_iduser.setCellValueFactory(new PropertyValueFactory<>("userID"));
            col_idpack.setCellValueFactory(new PropertyValueFactory<>("IdPack"));
            col_datedebut.setCellValueFactory(new PropertyValueFactory<>("dateDeb"));
            col_datefin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifierabonnement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Modifierabonnement.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void supprimerabonnement(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Modifierabonnement.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void gererQRcode(ActionEvent event) {
        try {
            abonnement selectedAbonnement = tv_abonnement.getSelectionModel().getSelectedItem();
            if (selectedAbonnement != null) {
                showAlert(Alert.AlertType.INFORMATION, "Stripe API Utilisée", "Paiement en cours de traitement...");
                // Intégration Stripe : Exemple de création d'un paiement
                Stripe.apiKey = "sk_test_51OqiEOGjfoBklovpNyFwgkNUmmPvbzZYjF4hCmYozOTi6YjYDTaC9dC65LPzucDuxNNsbak6i295szOk7zoHAuum00kJVS4yNM";
                Charge charge = Charge.create(
                        new ChargeCreateParams.Builder()
                                .setAmount(999L) // Montant en centimes
                                .setCurrency("eur")
                                .setDescription("Paiement pour l'abonnement")
                                .setSource("tok_visa") // Token de carte de crédit
                                .build()
                );
                showAlert(Alert.AlertType.INFORMATION, "Paiement Réussi", "Le paiement a été effectué avec succès.");
                // Génération du QR code après le paiement réussi ou toute autre logique que vous souhaitez ajouter
                generateQRCode(selectedAbonnement);
            } else {
                showAlert(Alert.AlertType.WARNING, "Aucun Abonnement Sélectionné", "Veuillez sélectionner un abonnement pour générer le QR code.");
            }
        } catch (StripeException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur Stripe", "Erreur lors de la transaction avec Stripe : " + e.getMessage());
        }
    }


    private void generateQRCode(abonnement selectedAbonnement) {
        try {
            // Génération du contenu du QR code
            String qrCodeContent = "ID Abonnement: " + selectedAbonnement.getIdAbonnement() + "\n" +
                    "ID Utilisateur: " + selectedAbonnement.getUserId() + "\n" +
                    "ID Pack: " + selectedAbonnement.getIdPack() + "\n" +
                    "Date Début: " + selectedAbonnement.getDateDeb() + "\n" +
                    "Date Fin: " + selectedAbonnement.getDateFin();

            // Création du QR code en mémoire
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeContent, BarcodeFormat.QR_CODE, 200, 200);

            // Conversion du QR code en image
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            javafx.scene.image.Image image = new javafx.scene.image.Image(inputStream);

            // Affichage du QR code dans une fenêtre d'alerte
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("QR Code Généré");
            alert.setHeaderText("Le QR code a été généré avec succès.");

            ImageView imageView = new ImageView(image);
            alert.setGraphic(imageView);

            alert.showAndWait();
        } catch (WriterException | IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la génération du QR code : " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}