package com.weber.cs3230.Alexa;

import com.weber.cs3230.generators.*;

import java.util.Arrays;

public enum AlexaIntent  {
    PULL_ALTITUDE("PullAltitude"),
    SKYDIVING_GEAR("SkydivingGear"),
    CIRCLE_OF_AWARENESS("CircleOfAwareness"),
    SKYDIVING_RUSH("SkydivingRush"),
    DECISION_ALTITUDE("DecisionAltitude"),
    STAY_CALM("StayCalm"),
    PARAMOUNT_PART_OF_SKYDIVING("ParamountPartOfSkydiving"),
    JUMP_RUN("Jumprun"),
    WINDS_ALOFT("WindsAloft"),
    FREEFALL("Freefall");

    private final String intentName;
//    private final AnswerGenerator answerGenerator;

    public String getIntentName() {
        return intentName;
    }

    AlexaIntent(String intentName) {
        this.intentName = intentName;
//        this.answerGenerator = answerGenerator;
    }

//    public AnswerGenerator getAnswerGenerator() {
//        return answerGenerator;
//    }

    public static AlexaIntent getIntentFromString(String intentString) {
        return Arrays.stream(AlexaIntent.values())
                .filter(s -> s.intentName.equalsIgnoreCase(intentString))
                .findFirst()
                .orElse(null);
    }
}