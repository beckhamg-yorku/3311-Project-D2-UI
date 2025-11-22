package Backend;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int ORG_ID_LENGTH = 9;

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            return false;
        }
        
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }
        
        return hasUppercase && hasLowercase && hasDigit && hasSpecial;
    }

    public static boolean isValidOrgId(String orgId) {
        if (orgId == null) {
            return false;
        }
        return orgId.length() == ORG_ID_LENGTH;
    }

    public static String getPasswordRequirements() {
        return "Password must be at least 8 characters and contain uppercase, lowercase, number, and special character.";
    }

    public static String getOrgIdRequirements() {
        return "Organization ID must be exactly 9 characters.";
    }

    public static String getEmailRequirements() {
        return "Please enter a valid email address.";
    }
}