package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question9 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("Depending on the wind direction, 15mph.");
        answerList.add("B licensed jumpers make their own calls.");
        answerList.add("14mph");
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