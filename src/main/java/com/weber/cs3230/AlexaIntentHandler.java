package com.weber.cs3230;

import com.weber.cs3230.dto.Answer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class AlexaIntentHandler {

    public Answer handleIntent(@PathVariable String intentString) {
        AlexaIntent intent = AlexaIntent.getIntentFromString(intentString);
        /*
         * Once you finish what the assignment says to do, you'll be able to do something like this:
         *Make this compile!
         *
         * AnswerGenerator answerGenerator = intent.getAnswerGenerator();
         * return new Answer(answerGenerator.getAnswerText());
         */
         AnswerGenerator answerGenerator = intent.getAnswerGenerator();
         return new Answer(answerGenerator.getAnswerText());
//        return new Answer("nice job dood " + intentString); //TODO delete me! And get what's up there working
    }
}