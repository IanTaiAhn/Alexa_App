package com.weber.cs3230;

public enum AlexaIntent  {
    QUESTION_1("PullAltitude", new PullAltitude()),
    QUESTION_2("SkydivingGear", new SkydivingGear()),
    QUESTION_3("CircleOfAwareness", new CircleOfAwareness()),
    QUESTION_4("SkydivingRush", new SkydivingRush()),
    QUESTION_5("DecisionAltitude", new DecisionAltitude()),
    QUESTION_6("StayCalm", new StayCalm()),
    QUESTION_7("ParamountPartOfSkydiving", new ParamountPartOfSkydiving()),
    QUESTION_8("Jumprun", new Jumprun()),
    QUESTION_9("WindsAloft", new WindsAloft()),
    QUESTION_10("Freefall", new Freefall());

    private final String intentName;
    private final AnswerGenerator answerGenerator;

    AlexaIntent(String intentName, AnswerGenerator answerGenerator) {
        this.intentName = intentName;
        this.answerGenerator = answerGenerator;
    }

    public AnswerGenerator getAnswerGenerator() {
        return answerGenerator;
    }

    public static AlexaIntent getIntentFromString(String intentString) {
        for (AlexaIntent alexaIntent : AlexaIntent.values()) {
            if (alexaIntent.intentName.equalsIgnoreCase(intentString)) {
                return alexaIntent;
            }
        }
        return null;
    }
}