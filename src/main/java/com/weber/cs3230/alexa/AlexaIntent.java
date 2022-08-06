package com.weber.cs3230.alexa;

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
    FREEFALL("Freefall"),
    SCRAPEWEB("ScrapeWeb"),
    TRUNCATEWINDS("TruncateWinds");
//    RandomEnum

    private final String intentName;

    public String getIntentName() {
        return intentName;
    }

    AlexaIntent(String intentName) {
        this.intentName = intentName;
    }

    public static AlexaIntent getIntentFromString(String intentString) {
        return Arrays.stream(AlexaIntent.values())
                .filter(s -> s.intentName.equalsIgnoreCase(intentString))
                .findFirst()
                .orElse(null);
    }
}