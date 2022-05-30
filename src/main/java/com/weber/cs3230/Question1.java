package com.weber.cs3230;

import java.util.*;

public class Question1 implements AnswerGenerator{
    private final List<String> answerList = new ArrayList<>();
    private void addStrings() {
        answerList.add("5500");
        answerList.add("4500");
        answerList.add("5000");
    }

    @Override
    public String getAnswerText() {
        addStrings();
        String lastStr = answerList.get(0);
            while (lastStr.equals(answerList.get(0)))   {
                Collections.shuffle(answerList);
            }
        return answerList.get(0);

//      START OF TESTS
//        lets test something. lets test if this method gets called everytime we refresh the page.
        // if the variable is local, inside the method, then the counter only updated once.

        // what happens when we use an instance varaible out side the local method.
        // the number increments, and remains.
        // if we change the url variable(intentName) then the number won't increment.
        // I'm assuming this is because we are creating the object that implements the interface,
        // so there is no reason why the object gets called
        // counter resets when we terminate the program.
//        END OF TESTS

    }
}
