package services;

import com.sun.security.auth.UserPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CustomLoginModule implements LoginModule {

    private CallbackHandler callbackHandler;
    private String username;
    private boolean succeeded = false;
    private Subject subject;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password", false);

        try {
            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            char[] password = ((PasswordCallback) callbacks[1]).getPassword();

            // Perform authentication logic with the username and password
            // Example: Call your authentication service or perform database queries here

            // For demonstration, let's print the username and password
            System.out.println("Received username: " + username);
            System.out.println("Received password: " + new String(password));

            // For simplicity, returning true assuming authentication succeeds
            succeeded = true; // Set succeeded to true upon successful authentication
            return true;
        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
            throw new LoginException("Error handling callbacks");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded) {
            // Authentication succeeded, establish the user's identity and role
            if (subject.isReadOnly()) {
                throw new LoginException("Subject is read-only");
            }

            // Add user principal
            subject.getPrincipals().add(new UserPrincipal(username));

            // Create an instance of AuthenticationService
            AuthenticationService authService = new AuthenticationService();

            // Determine user's role based on the value retrieved from the database
            boolean isAdmin = false;
            try {
                isAdmin = authService.isAdmin(username);
            } catch (SQLException e) {
                // Handle SQLException
                e.printStackTrace();
                throw new LoginException("Error determining user role");
            }

            // Add a role Principal based on the user's role
            if (isAdmin) {
                subject.getPrincipals().add(new RolePrincipal("admin"));
            } else {
                subject.getPrincipals().add(new RolePrincipal("client"));
            }

            // Clear the password as we don't need it anymore
            clearPassword();

            // Authentication succeeded, return true
            return true;
        } else {
            // Authentication failed, return false
            clearPassword();
            return false;
        }
    }


    @Override
    public boolean abort() throws LoginException {
        // Abort logic
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        // Logout logic
        return true;
    }

    private void clearPassword() {
        // Clear password logic if needed
    }
}
