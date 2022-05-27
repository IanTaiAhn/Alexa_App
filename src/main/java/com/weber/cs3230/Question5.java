package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question5 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("It's safer to pull before 2500.");
        answerList.add("You don't have to wait till 2500, the quicker the better.");
        answerList.add("2500");
    }

    @Override
    public String getAnswerText() {
        addStrings();
        String lastStr = answerList.get(0);
        while (lastStr.equals(answerList.get(0))) {
            Collections.shuffle(answerList);
        }
        return answerList.get(0);
    }
}