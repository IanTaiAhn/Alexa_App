package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question5 implements AnswerGenerator{
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
        answerList.add("It's safer to pull before 2500.");
        answerList.add("You don't have to wait till 2500, the quicker the better.");
        answerList.add("2500");
        Collections.shuffle(answerList);
        String answer = answerList.get(0);
        // all of the randomizing, and duplicates, and multiple answers will happen here
        return answer;
    }
}