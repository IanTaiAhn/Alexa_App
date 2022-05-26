package com.weber.cs3230;

public enum AlexaIntent implements GenerateAnswers{
    QUESTION_1("What is the correct pull altitude?");
//    QUESTION_2("What is a slider?"),
//    QUESTION_3("What altitude do you lock-on at?"),
//    QUESTION_4("Why is skydiving fun?"),
//    QUESTION_5("What is your decision altitude?"),
//    QUESTION_6("When do you panic?"),
//    QUESTION_7("What is the most important part in a skydive?"),
//    QUESTION_8("What altitude do you drop at?"),
//    QUESTION_9("What are the maximum winds you can jump at?"),
//    QUESTION_10("How do you regain stability during free fall?");

    private final String intentName;
    private Question1 q1;
    private GenerateAnswers answers;



    AlexaIntent(String intentName) {
        this.intentName = intentName;
//        this.answers = answers;
    }

//    AlexaIntent(GenerateAnswers answers) {
//        this.answers = answers;
//    }

    public GenerateAnswers getAnswers() {
//        System.out.println(q1.answers());
        return answers;
    }

    public static AlexaIntent getIntentFromString(String intentString) {
        for (AlexaIntent alexaIntent : AlexaIntent.values()) {
            if (alexaIntent.intentName.equalsIgnoreCase(intentString)) {
                return alexaIntent;
            }
        }
        return null;
    }


    @Override
    public String answers() {
        return "What am I doing";
    }
}