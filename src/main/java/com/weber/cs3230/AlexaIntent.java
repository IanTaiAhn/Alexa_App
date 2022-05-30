package com.weber.cs3230;

public enum AlexaIntent  {
    // not the actual question, and not the actual answer.
    // we can switch the names later, but lets develop in ints.
    // TODO switch the intentName variable once testing is complete.
    QUESTION_1("1", new Question1()),
    QUESTION_2("2", new Question2()),
    QUESTION_3("3", new Question3()),
    QUESTION_4("4", new Question4()),
    QUESTION_5("5", new Question5()),
    QUESTION_6("6", new Question6()),
    QUESTION_7("7", new Question7()),
    QUESTION_8("8", new Question8()),
    QUESTION_9("9", new Question9()),
    QUESTION_10("10", new Question10());

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