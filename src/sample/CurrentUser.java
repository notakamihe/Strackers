package sample;

import sample.models.User;

public class CurrentUser
{
    static User user;

    public static void setUser (User newUser)
    {
        user = newUser;
    }

    public static User getUser ()
    {
        return user;
    }

    public static void removeUser () {
        user = null;
    }
}
