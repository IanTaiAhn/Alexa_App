package com.weber.cs3230;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Question1 implements GenerateAnswers{
    // "create an implementation of this interface for each of my questions... "
    private List<String> answerList = new ArrayList<>();


    @Override
    public String answers() {
    answerList.add("5500");
    answerList.add("6500");
    answerList.add("4500");
        Collections.shuffle(answerList);
        return answerList.get(0);
        // i have to add the no repeating answer logic here... After i figure out how to set this up hehe
    }
}
