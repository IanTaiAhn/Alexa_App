package com.weber.cs3230;

import java.util.*;

public class Question1 implements AnswerGenerator{
    //LastAnswerCache suggestion class for the no duplicate answer twice in a row.
//
//    private static Map<AlexaIntent, String> lastAnswerCache = new HashMap<>();
//
//    public static void addAnswer(AlexaIntent alexaIntent, String answer)    {
//        lastAnswerCache.put(alexaIntent, answer);
//    }
//
    @Override
    public String getAnswerText() {
        List<String> answerList = new ArrayList<>();
//        String answer = answerList.get(0);
//        if (first) {
            answerList.add("5500");
            answerList.add("4500");
            answerList.add("5000");
            Collections.shuffle(answerList);
            String answer = answerList.get(0);
        // all of the randomizing, and duplicates, and multiple answers will happen here
        return answer;
    }
}
