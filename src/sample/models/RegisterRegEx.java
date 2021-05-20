package sample.models;

public class RegisterRegEx
{

    public static boolean isValidUsername(String username)
    {
        return username.matches("^[A-z0-9_]+$");
    }

    public static boolean isValidEmail (String email)
    {
        return email.matches("^[A-z0-9]+@[A-z0-9]+.(com|org|net|edu)$");
    }

    public static boolean isValidPassword (String password)
    {
        return password.matches("^[\\d\\D]{8,15}$");
    }
}