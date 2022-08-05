package com.weber.cs3230.webscraping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@Component
public class HerokuDBConnection {

    @Value("${db.host}") private String dbHost = "ec2-18-214-35-70.compute-1.amazonaws.com";
    @Value("${db.port}") private String dbPort = "5432";
    @Value("${db.name}") private String dbName = "d1auj72juq3fgq";
    @Value("${db.user}") private String dbUser = "ofzlwollcxbevm";
    @Value("${db.password}") private String dbPassword = "6c975cd29e840a0d2de16113c126d25d41e13287b15434bfbf98efb358a52784";
    public static void main(String[] args) {

        HerokuDBConnection db = new HerokuDBConnection();
//        db.insertWindsAloft();
        // runs query
//        System.out.println(db.insertWindsAloft());
        System.out.println(db.truncateTable());

//        JSoupScraper jSoupScraper = new JSoupScraper();
//        WindsAloft windsAloft = new WindsAloft(jSoupScraper.scrapeData());
//        for (WindsAloft el : windsAloft.getWindsAloftList())  {
//            System.out.println(el.getDate() + " " + el.getTime() + " " + el.getWindDirection() + " " + el.getWindSpeed() + " " + el.getWindGust());
//        }
    }
    public boolean insertWindsAloft() {
        final String sql = "INSERT INTO public.\"WindsAloft\"(\"WindsAloftDate\", \"WindsAloftTime\", \"WindDirection\", \"WindSpeed\", \"WindGust\")\n" +
                "VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            JSoupScraper jSoupScraper = new JSoupScraper();
            WindsAloft windsAloft = new WindsAloft(jSoupScraper.scrapeData());
            for (WindsAloft el : windsAloft.getWindsAloftList())  {
                statement.setString(1, el.getDate());
                statement.setString(2, el.getTime());
                statement.setString(3, el.getWindDirection());
                statement.setString(4, el.getWindSpeed());
                statement.setString(5, el.getWindGust());
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to run query in remotedb." , e);
        }
    }

    public boolean truncateTable() {
        final String sql = "TRUNCATE public.\"WindsAloft\" RESTART IDENTITY";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to run query in remotedb." , e);
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }

//    private static Connection getConnection() throws URISyntaxException, SQLException {
//        String dbUrl = System.getenv("JDBC_DATABASE_URL");
//        return DriverManager.getConnection(dbUrl);
//    }
}
