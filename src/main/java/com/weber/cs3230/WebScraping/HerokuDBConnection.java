package com.weber.cs3230.WebScraping;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HerokuDBConnection {
    public static void main(String[] args) {

        HerokuDBConnection db = new HerokuDBConnection();
        db.insertWindsAloft();
    }
    public void insertWindsAloft() {


//        INSERT INTO public."WindsAloft"(
//                "WindsAloftID", "WindsAloftDate", "WindsAloftTime", "WindDirection", "WindSpeed", "WindGust")
//        VALUES (?, ?, ?, ?, ?, ?);
        final String sql = "SELECT FROM WindsAloft WHERE WindsAloftID = 1";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.executeUpdate();
        } catch (SQLException | URISyntaxException e) {
            throw new RuntimeException("Failed to insert WindsAloft data" , e);
        }
//        return true;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }
}
