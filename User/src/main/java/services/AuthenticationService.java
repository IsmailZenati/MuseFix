package services;

import utils.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;

public class AuthenticationService {
    private final Connection connection;
    public AuthenticationService() {
        this.connection = MyDatabase.getInstance().getConnection();
    }

    public boolean authenticate(String username, String password) throws SQLException {
        // Your existing code for authentication
        String query = "SELECT COUNT(*) FROM user WHERE email = ? AND passwd = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count == 1) {
                        // Authentication succeeded, perform JAAS login
                        try {
                            LoginContext lc = new LoginContext("CustomLoginModule", new CustomCallbackHandler(username, password));
                            lc.login();

                            // If authentication is successful, check if the user is an admin
                            return isAdmin(username);
                        } catch (LoginException e) {
                            e.printStackTrace(); // Handle login failure
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isAdmin(String username) throws SQLException {
        String query = "SELECT role FROM user WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int role = resultSet.getInt("role");
                    // Check if role is admin (1)
                    return role == 0;
                }
            }
        }
        // If user not found or error occurred, return false
        return false;
    }
}
