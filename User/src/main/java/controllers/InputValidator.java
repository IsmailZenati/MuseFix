package controllers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String NAME_REGEX = "^[a-zA-Z\\s]*$";

    private static final String ROLE_REGEX = "^[01]$";

    private static final String PHONE_REGEX = "^\\d{8}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern ROLE_PATTERN = Pattern.compile(ROLE_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public static boolean validateEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean validateName(String name) {
        Matcher matcher = NAME_PATTERN.matcher(name);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        // Check if password is not null
        return password != null;
    }

    public static boolean validateRole(String role) {
        Matcher matcher = ROLE_PATTERN.matcher(role);
        return matcher.matches();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }
}
