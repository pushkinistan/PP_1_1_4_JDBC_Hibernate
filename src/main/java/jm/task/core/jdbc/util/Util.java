package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД


    public static Connection getConnection() {

        Connection connection = null;

        try {
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "SoStrong#256");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;

        }

}
