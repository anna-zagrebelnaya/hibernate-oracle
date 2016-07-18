package com.anka.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {

            Properties dbConnectionProperties = new Properties();
            try {
                dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getSystemClassLoader()
                        .getResourceAsStream("db.properties"));
            } catch(Exception e) {
                System.err.println("Hibernate properties loading failed." + e);
                throw new ExceptionInInitializerError(e);
            }

            return new Configuration()
                    .setProperty("hibernate.connection.url", dbConnectionProperties.getProperty("url"))
                    .setProperty("hibernate.connection.driver_class", dbConnectionProperties.getProperty("driver"))
                    .setProperty("hibernate.connection.username", dbConnectionProperties.getProperty("username"))
                    .setProperty("hibernate.connection.password", dbConnectionProperties.getProperty("password"))
                    .setProperty("hibernate.default_schema", dbConnectionProperties.getProperty("default_schema"))
                    .configure("hibernate.cfg.xml").buildSessionFactory();


        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
