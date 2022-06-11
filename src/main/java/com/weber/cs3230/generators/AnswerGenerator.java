package com.weber.cs3230.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AnswerGenerator {
    public List<String> answerListA = new ArrayList<>();
    abstract void addStrings();
    public String getAnswerText() {
        addStrings();
        String lastStr = answerListA.get(0);
        while (lastStr.equals(answerListA.get(0))) {
            Collections.shuffle(answerListA);
        }
        return answerListA.get(0);
    }
}
