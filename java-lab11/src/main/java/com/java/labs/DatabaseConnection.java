package com.java.labs;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConnection implements AutoCloseable {

    private Connection connection;
    private final Logger logger = Logger.getLogger("dbLogger");

    public DatabaseConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties props = loadProperties();

        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        String driver = props.getProperty("db.driver");

        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
        logger.info("Підключення до бази даних встановлено.");
    }

    private Properties loadProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = DatabaseConnection.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new IOException("Файл db.properties не знайдено!");
            }
            props.load(input);
        }
        return props;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("З’єднання з базою даних закрито.");
            } catch (SQLException e) {
                logger.severe("Помилка при закритті з’єднання: " + e.getMessage());
            }
        }
    }
}
