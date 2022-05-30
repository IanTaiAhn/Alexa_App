package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question4 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("It's sick.");
        answerList.add("Because it is.");
        answerList.add("Adrenaline is crazy.");
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
