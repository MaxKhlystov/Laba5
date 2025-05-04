package Data;

import java.sql.*;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "tanks.db";  // Файл БД в корне проекта

    static {
        try {
            // 1. Регистрируем драйвер (для старых версий Java)
            Class.forName("org.sqlite.JDBC");

            // 2. Подключаемся к БД (правильный URL)
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB_FILE);

            if (connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("Подключено к: " + metaData.getDriverName());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite драйвер не найден!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к БД");
            e.printStackTrace();
        }
    }
}