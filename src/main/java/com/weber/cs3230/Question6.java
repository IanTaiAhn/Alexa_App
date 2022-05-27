package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question6 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("Never, always stay calm.");
        answerList.add("Calm people live.");
        answerList.add("If your main, and reserve parachute fail...");
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