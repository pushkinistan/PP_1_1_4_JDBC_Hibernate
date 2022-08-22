package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";



    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "SoStrong#256");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {

        SessionFactory sessionFactory = null;
        try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, DRIVER);
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/userdb");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "SoStrong#256");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                properties.put(Environment.SHOW_SQL, "true");

                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

             //   properties.put(Environment.HBM2DDL_AUTO, "update");



                configuration.setProperties(properties);

                configuration.addAnnotatedClass(User.class);



                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Hibernate: Нет коннекта к базе");
                e.printStackTrace();
            }

        return sessionFactory;
    }

}
