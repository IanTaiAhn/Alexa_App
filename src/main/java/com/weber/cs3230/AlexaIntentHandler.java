package com.weber.cs3230;

import com.weber.cs3230.dto.Answer;
import com.weber.cs3230.generators.AnswerGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class AlexaIntentHandler {

    public Answer handleIntent(@PathVariable String intentString) {
        AlexaIntent intent = AlexaIntent.getIntentFromString(intentString);
         AnswerGenerator answerGenerator = intent.getAnswerGenerator();
         if (intent == null)    {
             return null;
         }
         return new Answer(answerGenerator.getAnswerText());
    }
}