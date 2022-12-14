package com.weber.cs3230.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JSoupScraper {
//    private ArrayList<WindsAloft> passedDataList = new ArrayList<>();
    public ArrayList<WindsAloft> scrapeData() {
        try {
            Document page = Jsoup.connect("https://www.windfinder.com/forecast/erda_utah_usa").get();

            Elements date = page.select("h3.h");
            Elements time = page.select("div.data-time");
            Elements windDirection = page.select("div.directionarrow");
            Elements windSpeeds = page.select("div.speed");
            Elements windGusts = page.select("div.data-gusts");

            WindsAloft windsAloftList = new WindsAloft();

            ArrayList<String> windDirectionList = new ArrayList<>();
            for (Element el : windDirection) {
                windDirectionList.add(el.attributes().get("title"));
            }

            int dateCounter = 0;
            int loops = 0;
            for (int i = 0; i < time.size(); i++) {
                WindsAloft windsAloft = new WindsAloft(date.get(dateCounter).text(), time.get(i).text(), windDirectionList.get(i), windSpeeds.get(i).text(), windGusts.get(i).text());
                loops++;
                windsAloftList.addWindsAloft(windsAloft);
                if (loops == 8) {
                    dateCounter++;
                    loops = 0;
                }
            }
            return windsAloftList.getWindsAloftList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("failed");
        return null;
    }

}

// Keeping these comments here if I need to test stuff from the web scraper.
//            gives me all that good data.
//            for (WindsAloft el : windsAloftList.getWindsAloftList()) {
//                System.out.println(el.getDate() + " " + el.getTime() + " " + el.getWindDirection() + " " + el.getWindSpeed() + " " + el.getWindGust());
//            }

// Testing the info from website.
            /*
            for (Element el : date)  {
                System.out.println("date: " + el.text());
            }
            System.out.println(date.size());
            for (Element el : time)  {
                System.out.println("time: " + el.text());
            }
            System.out.println(time.size());
            for (Element el : windDirection)  {
                System.out.println("Direction: " + el.attributes().get("title"));
            }
            System.out.println(windDirection.size());
            for (Element el : windGusts)  {
                System.out.println("Gusts: " + el.text());
            }
            System.out.println(windGusts.size());
            for (Element el : windSpeeds)  {
                System.out.println("speeds: " + el.text());
            }
            System.out.println(windSpeeds.size());
            for (Element el: time)   {
                WindsAloft windsAloft = new WindsAloft(el.text())
            }
            */
