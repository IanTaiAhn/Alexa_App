package com.weber.cs3230.webscraping;

import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class HerokuDBConnection {

    private String dbHost = "ec2-18-214-35-70.compute-1.amazonaws.com";
    private String dbPort = "5432";
    private String dbName = "d1auj72juq3fgq";
    private String dbUser = "ofzlwollcxbevm";
    private String dbPassword = "6c975cd29e840a0d2de16113c126d25d41e13287b15434bfbf98efb358a52784";
    public static void main(String[] args) {

        HerokuDBConnection db = new HerokuDBConnection();
//      Runs my queries.
//        System.out.println(db.insertWindsAloft());
//        System.out.println(db.truncateTable());
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
}
