package eiu.cse456.flightbookingapi.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

public class AuthHelpers {
    public static MyResponse validatePassword(String password) {
        if (password.length() < 6) {
            return new MyResponse(true, "Password must be at least 6 characters long");
        } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return new MyResponse(true, "Password must contain at least one uppercase letter");
        } else if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return new MyResponse(true, "Password must contain at least one lowercase letter");
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return new MyResponse(true, "Password must contain at least one number");
        } else if (!Pattern.compile("[^A-Za-z0-9]").matcher(password).find()) {
            return new MyResponse(true, "Password must contain at least one special character");
        }
        return new MyResponse(false, null);
    }

    public static String makeHashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean comparePasswords(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
