package com.weber.cs3230;

import com.weber.cs3230.generators.*;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AlexaIntent  {
    PULL_ALTITUDE("PullAltitude", new PullAltitude()),
    SKYDIVING_GEAR("SkydivingGear", new SkydivingGear()),
    CIRCLE_OF_AWARENESS("CircleOfAwareness", new CircleOfAwareness()),
    SKYDIVING_RUSH("SkydivingRush", new SkydivingRush()),
    DECISION_ALTITUDE("DecisionAltitude", new DecisionAltitude()),
    STAY_CALM("StayCalm", new StayCalm()),
    PARAMOUNT_PART_OF_SKYDIVING("ParamountPartOfSkydiving", new ParamountPartOfSkydiving()),
    JUMP_RUN("Jumprun", new Jumprun()),
    WINDS_ALOFT("WindsAloft", new WindsAloft()),
    FREEFALL("Freefall", new Freefall());

    private final String intentName;
    private final AnswerGenerator answerGenerator;

    public String getIntentName() {
        return intentName;
    }

    AlexaIntent(String intentName, AnswerGenerator answerGenerator) {
        this.intentName = intentName;
        this.answerGenerator = answerGenerator;
    }

    public AnswerGenerator getAnswerGenerator() {
        return answerGenerator;
    }

    public static AlexaIntent getIntentFromString(String intentString) {
        return Arrays.stream(AlexaIntent.values())
                .filter(s -> s.intentName.equalsIgnoreCase(intentString))
                .findFirst()
                .orElse(null);
    }
}