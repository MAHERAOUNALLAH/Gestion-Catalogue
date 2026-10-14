package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {

    private static Connection connection;

    private SingletonConnexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/catalogue", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (connection == null)
            new SingletonConnexion();
        return connection;
    }
}