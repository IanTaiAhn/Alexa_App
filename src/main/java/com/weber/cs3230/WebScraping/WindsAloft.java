package com.weber.cs3230.WebScraping;

import java.util.ArrayList;

public class WindsAloft {
    private String date;
    private String time;
    private String windDirection;
    private String windSpeed;
    private String windGust;
    private ArrayList<WindsAloft> windsAloftList = new ArrayList<>();

    public WindsAloft(String date, String time, String windDirection, String windSpeed, String windGust) {
        this.date = date;
        this.time = time;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
    }
    public WindsAloft() {

    }

    public ArrayList<WindsAloft> getWindsAloftList() {
        return windsAloftList;
    }

    public int getListSize() {
        return windsAloftList.size();
    }
    public void addWindsAloft(WindsAloft obj)   {
        windsAloftList.add(obj);
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindGust() {
        return windGust;
    }
}