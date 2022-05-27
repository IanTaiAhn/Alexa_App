package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question2 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("The small square that helps keep your canopy untangled.");
        answerList.add("Part of the canopy that helps keep lines untangled.");
        answerList.add("Helps inflate canopy by separating lines");
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