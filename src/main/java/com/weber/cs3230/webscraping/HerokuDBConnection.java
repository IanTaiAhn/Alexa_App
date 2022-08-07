package com.weber.cs3230.webscraping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HerokuDBConnection {
    private String dbHost = "ec2-18-214-35-70.compute-1.amazonaws.com";
    private String dbPort = "5432";
    private String dbName = "d1auj72juq3fgq";
    private String dbUser = "ofzlwollcxbevm";
    private String dbPassword = "6c975cd29e840a0d2de16113c126d25d41e13287b15434bfbf98efb358a52784";

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

    public List<String> getWindsAloftToday() {
        JSoupScraper jSoupScraper = new JSoupScraper();
        WindsAloft windsAloft = new WindsAloft(jSoupScraper.scrapeData());

        final List<String> windsData = new ArrayList<>();
        final String sql = "SELECT \"WindDirection\", \"WindSpeed\", \"WindGust\" FROM public.\"WindsAloft\"\n" +
                "WHERE \"WindsAloftDate\" = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, windsAloft.getWindsAloftList().get(0).getDate());
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    windsData.add(rs.getString("WindDirection"));
                    windsData.add(rs.getString("WindSpeed"));
                    windsData.add(rs.getString("WindGust"));
                }
            }
            return windsData;
        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException("Failed to get answers", e);
        }
    }

    public List<String> getWindsAloftTomorrow() {
        JSoupScraper jSoupScraper = new JSoupScraper();
        WindsAloft windsAloft = new WindsAloft(jSoupScraper.scrapeData());

        final List<String> windsData = new ArrayList<>();
        final String sql = "SELECT \"WindDirection\", \"WindSpeed\", \"WindGust\" FROM public.\"WindsAloft\"\n" +
                "WHERE \"WindsAloftDate\" = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, windsAloft.getWindsAloftList().get(8).getDate());
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    windsData.add(rs.getString("WindDirection"));
                    windsData.add(rs.getString("WindSpeed"));
                    windsData.add(rs.getString("WindGust"));
                }
            }
            return windsData;
        } catch (ClassNotFoundException|SQLException e) {
            throw new RuntimeException("Failed to get answers", e);
        }
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        return DriverManager.getConnection(url, dbUser, dbPassword);
    }
}
