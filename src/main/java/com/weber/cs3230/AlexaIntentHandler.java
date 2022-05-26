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
         *
         * AnswerGenerator answerGenerator = intent.getAnswerGenerator();
         * return new Answer(answerGenerator.getAnswerText());
         */
//        AnswerGenerator answerGenerator = intent.getAnswerGenerator(); // getting the enum, that is the interface type
//        return new Answer(answerGenerator.getAnswerText()); // we return the answer somehow..
//            GenerateAnswers generateAnswers = intent.getAnswers();
//            return new Answer(generateAnswers.answers());

        return new Answer("nice job, your service is up and running! Here's what you passed: " + intentString); //TODO delete me! And get what's up there working
    }
}