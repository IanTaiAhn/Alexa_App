package com.weber.cs3230.Alexa;

import com.weber.cs3230.NoAvailableAnswerException;
import com.weber.cs3230.dto.Answer;
import com.weber.cs3230.generators.DBAnswerGenerator;
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

    public Answer handleIntent(@PathVariable String intentString) throws NoAvailableAnswerException {
        AlexaIntent intent = AlexaIntent.getIntentFromString(intentString);
        log.info("intentString that gets passed in: " + intentString);
        log.info("AlexaIntent Enum: " + intent);
         if (intent == null)    {
             return null;
         }
        return new Answer(dbAnswerGenerator.getAnswerText(intentString));
    }
}