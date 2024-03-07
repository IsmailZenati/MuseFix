package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class StripeController {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expMonthField;

    @FXML
    private TextField expYearField;

    @FXML
    private TextField cvcField;

    @FXML
    private Button payButton;

    @FXML
    private Label statusLabel;

    private static final String STRIPE_SECRET_KEY = "sk_test_51OrLeRLwWiZBvlVZT7ygltMJLWra04FpXRt8Osvx68vfiKHmxnNVD4ixXtfcscRZlxSG0FowfDJMwLL8wZ66yexm00wxdI6GRQ";

    public void initialize() {
        payButton.setOnAction(event -> processPayment());
    }

    private void processPayment() {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        String cardNumber = cardNumberField.getText();
        Integer expMonth = Integer.parseInt(expMonthField.getText());
        Integer expYear = Integer.parseInt(expYearField.getText());
        String cvc = cvcField.getText();

        ChargeCreateParams chargeParams =
                ChargeCreateParams.builder()
                        .setAmount(1000L) // Montant en centimes (ici 10€)
                        .setCurrency("eur")
                        .setSource("tok_visa") // Token de test
                        .build();

        try {
            Charge charge = Charge.create(chargeParams);
            statusLabel.setText("Payment successful: " + charge.getId());
        } catch (StripeException e) {
            e.printStackTrace();
            statusLabel.setText("Payment failed: " + e.getMessage());
        }
    }
}
