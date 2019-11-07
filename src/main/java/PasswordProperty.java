import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordProperty {


    public static boolean isPasswordLengthCorrect(String password, String password2) {
        boolean isCorrectPassword = true;
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(password);

        if (password.length() <= 8)
            return false;
        if (!password.equals(password2))
            return false;
        if (matcher.find())
            return false;
        if (!password.matches(".*[0-9].+"))
            return false;
        return true;
    }


}
