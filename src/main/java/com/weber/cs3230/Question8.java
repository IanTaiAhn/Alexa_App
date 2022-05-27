package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question8 implements AnswerGenerator{
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
        answerList.add("13000");
        answerList.add("15000");
        answerList.add("14000");
        Collections.shuffle(answerList);
        String answer = answerList.get(0);
        // all of the randomizing, and duplicates, and multiple answers will happen here
        return answer;
    }
}