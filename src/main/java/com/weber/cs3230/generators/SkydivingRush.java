package com.weber.cs3230.generators;

import java.util.List;

public class SkydivingRush extends QuestionVariable {
    @Override
    void addStrings() {
        answerListA.add("It's sick.");
        answerListA.add("Because it is.");
        answerListA.add("Adrenaline is crazy.");
    }
    @Override
    public List<String> questionVariables() {
        questionVariables.add("scary?");
        questionVariables.add("awesome?");
        return questionVariables;
    }
}
