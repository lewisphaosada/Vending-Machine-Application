package com.techelevator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class h2Server {
    private String URL = "jdbc:h2:./data/salesReport";
    private String USERNAME = "sa";
    private String PASSWORD = "";

    public void createAndInitializeDatabase() {
        try {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                if (tableExists(connection, "ALL_ITEMS")) return;
                String firstSql = "CREATE TABLE IF NOT EXISTS all_items (slot CHAR(2) PRIMARY KEY, name VARCHAR(55), price double, category VARCHAR(55), total_sold INT)";
                PreparedStatement statement = connection.prepareStatement(firstSql);
                statement.executeUpdate();

                String firstValue = "INSERT INTO all_items (slot, name, price, category, total_sold) VALUES ('A1', 'Potato Crisps', 3.05, 'Chip', 0), ('A2', 'Stackers', 1.45, 'Chip', 0), ('A3', 'Grain Waves', 2.75, 'Chip', 0), ('A4', 'Cloud Popcorn', 3.65, 'Chip', 0)," +
                        "('B1', 'Moonpie', 1.80, 'Candy', 0), ('B2', 'Cowtales', 1.50, 'Candy', 0), ('B3', 'Wonka Bar',1.50, 'Candy', 0), ('B4', 'Crunchie', 1.75, 'Candy', 0), ('C1', 'Cola', 1.25, 'Drink', 0), ('C2', 'Dr. Salt', 1.50, 'Drink', 0)," +
                        "('C3', 'Mountain Melter', 1.50, 'Drink', 0), ('C4', 'Heavy', 1.50, 'Drink', 0), ('D1', 'U-Chews', 0.85, 'Gum', 0), ('D2', 'Little League Chew', 0.95, 'Gum', 0), ('D3', 'CChiclets',0.75, 'Gum', 0),('D4', 'Triplemint', 0.75, 'Gum', 0)";
                statement = connection.prepareStatement(firstValue);
                statement.executeUpdate();

                String secondSql = "CREATE TABLE IF NOT EXISTS sales_table (transaction_num INT NOT NULL AUTO_INCREMENT PRIMARY KEY, slot CHAR(2), name VARCHAR(55), price double, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
                statement = connection.prepareStatement(secondSql);
                statement.executeUpdate();

                statement.close();
                connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSale(Item item) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO sales_table (slot, name, price) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, item.getSlot());
                statement.setString(2, item.getDescription());
                statement.setDouble(3, item.getPrice());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementTotalSold(String slot, int incrementValue) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE all_items SET total_sold = total_sold + ? WHERE slot = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, incrementValue);
                statement.setString(2, slot);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalDollarAmount() {
        double dollarAmount = 0;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT SUM(price) AS total_sales FROM sales_table";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    dollarAmount = resultSet.getDouble("total_sales");
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dollarAmount;
    }

    public void generateSalesReport() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM all_items";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();

                // Get the column names
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnName(i) + " | ");
                }
                System.out.println();

                // Display the data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + " | ");
                    }
                    System.out.println();
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
        return resultSet.next();
    }
}

