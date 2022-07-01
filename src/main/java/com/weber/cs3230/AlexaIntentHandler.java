package com.weber.cs3230;

import com.weber.cs3230.dto.Answer;
import com.weber.cs3230.generators.AnswerGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class AlexaIntentHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Answer handleIntent(@PathVariable String intentString) throws NoAvailableAnswerException {
        AlexaIntent intent = AlexaIntent.getIntentFromString(intentString);
        log.info("intentString that gets passed in: " + intentString);
        log.info("AlexaIntent Enum: " + intent);
         AnswerGenerator answerGenerator = intent.getAnswerGenerator();

         if (intent == null)    {
             return null;
         }
         return new Answer(answerGenerator.getAnswerText());
    }
}