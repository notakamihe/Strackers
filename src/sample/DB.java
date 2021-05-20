package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/strackers";
    public static Connection connection;

    public static void connect () {
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "password");
            System.out.println("Connected to database!");
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
