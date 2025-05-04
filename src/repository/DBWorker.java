package repository;

import model.Battlefield;
import model.HeavyTank;
import model.LightTank;
import model.Tank;

import java.sql.*;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "tanks.db";

    static {
        try {
            // Регистрация драйвера
            Class.forName("org.sqlite.JDBC");

            // Подключение к БД
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB_FILE)) {
                // Создание таблицы, если её нет
                createTables(connection);

                System.out.println("Подключение к SQLite успешно установлено");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Ошибка при работе с БД: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Таблица для танков (общие свойства)
            stmt.execute("CREATE TABLE IF NOT EXISTS tanks (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "hp INTEGER NOT NULL, " +
                    "type TEXT NOT NULL CHECK (type IN ('HEAVY', 'LIGHT')), " +
                    "special_value INTEGER NOT NULL)");
        }
    }

    public static void saveBattlefield(Battlefield battlefield) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB_FILE)) {
            // Очищаем старые данные
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("DELETE FROM tanks");
            }

            // Сохраняем новые данные
            try (PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO tanks (id, name, hp, type, special_value) VALUES (?, ?, ?, ?, ?)")) {

                for (Tank tank : battlefield.getTanks()) {
                    pstmt.setInt(1, tank.getId());
                    pstmt.setString(2, tank.getName());
                    pstmt.setInt(3, tank.getHPTank());

                    if (tank instanceof HeavyTank) {
                        pstmt.setString(4, "HEAVY");
                        pstmt.setInt(5, ((HeavyTank) tank).getArmorThickness());
                    } else if (tank instanceof LightTank) {
                        pstmt.setString(4, "LIGHT");
                        pstmt.setInt(5, ((LightTank) tank).getViewRange());
                    }

                    pstmt.addBatch();
                }

                pstmt.executeBatch();
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении в БД: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Battlefield loadBattlefield() {
        Battlefield battlefield = new Battlefield();
        battlefield.getTanks().clear(); // Очищаем стандартные танки

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + PATH_TO_DB_FILE);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tanks")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int hp = rs.getInt("hp");
                String type = rs.getString("type");
                int specialValue = rs.getInt("special_value");

                if ("HEAVY".equals(type)) {
                    battlefield.addTank(new HeavyTank(id, name, hp, specialValue));
                } else if ("LIGHT".equals(type)) {
                    battlefield.addTank(new LightTank(id, name, hp, specialValue));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при загрузке из БД: " + e.getMessage());
            e.printStackTrace();
        }

        return battlefield;
    }
}