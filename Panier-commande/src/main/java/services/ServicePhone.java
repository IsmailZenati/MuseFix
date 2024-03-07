package services;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicePhone {
    public static boolean validateNumber(String phoneNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://phonenumbervalidatefree.p.rapidapi.com/ts_PhoneNumberValidateTest.jsp?number=%2B216" + phoneNumber + "&country=TN"))
                .header("X-RapidAPI-Key", "8731dbfc05mshf8e8d51e7c79519p1b906bjsn0e77ae68521b")
                .header("X-RapidAPI-Host", "phonenumbervalidatefree.p.rapidapi.com")
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur lors de l'appel à l'API
        }

        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(response.body());
        } catch (JSONException e) {
            System.err.println("Réponse invalide de l'API.");
            return false; // Retourne false si la réponse JSON n'est pas valide
        }

        // Vérifie si le champ "isValidNumber" est présent dans la réponse JSON
        if (jsonResponse.has("isValidNumber")) {
            return jsonResponse.getBoolean("isValidNumber");
        } else {
            System.err.println("Réponse invalide de l'API.");
            return false;
        }
    }
}

