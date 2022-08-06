package com.weber.cs3230.alexa;

import com.weber.cs3230.NoAvailableAnswerException;
import com.weber.cs3230.dto.Answer;
import com.weber.cs3230.generators.DBAnswerGenerator;
import com.weber.cs3230.webscraping.HerokuDBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class AlexaIntentHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final DBAnswerGenerator dbAnswerGenerator;
    @Autowired
    public AlexaIntentHandler(DBAnswerGenerator dbAnswerGenerator)  {
        this.dbAnswerGenerator = dbAnswerGenerator;
    }

    // alex enum intent for inserting into database.
    public Answer handleIntent(@PathVariable String intentString) throws NoAvailableAnswerException {
        AlexaIntent intent = AlexaIntent.getIntentFromString(intentString);
        log.info("intentString that gets passed in: " + intentString);
        log.info("AlexaIntent Enum: " + intent);
         if (intent == null)    {
             return null;
         }
        if (intent.getIntentName() == "ScrapeWeb") {
            HerokuDBConnection db = new HerokuDBConnection();
            System.out.println(db.insertWindsAloft());
            log.info("Scraped web, and inserted into database");
            return new Answer("Scraped web, and inserted into database");
        }
        if (intent.getIntentName() == "TruncateWinds") {
            HerokuDBConnection db = new HerokuDBConnection();
          System.out.println(db.truncateTable());
            log.info("table has been truncated");
            return new Answer("Table Truncated");
        }
        if (intent.getIntentName() == "Today") {
            HerokuDBConnection db = new HerokuDBConnection();
            log.info("wind data for today has been uttered");
            String twelvePmWd = db.getWindsAloftToday().get(9);
            String twelvePmWs = db.getWindsAloftToday().get(10);
            String twelvePmWg = db.getWindsAloftToday().get(11);
            return new Answer("The wind direction today is " + twelvePmWd + ", the wind speed is " + twelvePmWs + ", the wind gusts are " + twelvePmWg);
        }
        if (intent.getIntentName() == "Tomorrow") {
            HerokuDBConnection db = new HerokuDBConnection();
            log.info("wind data for tomorrow has been uttered");
            String twelvePmWd = db.getWindsAloftTomorrow().get(9);
            String twelvePmWs = db.getWindsAloftTomorrow().get(10);
            String twelvePmWg = db.getWindsAloftTomorrow().get(11);
            return new Answer("The wind direction tomorrow is " + twelvePmWd + ", the wind speed is " + twelvePmWs + ", the wind gusts are " + twelvePmWg);
        }


        return new Answer(dbAnswerGenerator.getAnswerText(intentString));
    }
}