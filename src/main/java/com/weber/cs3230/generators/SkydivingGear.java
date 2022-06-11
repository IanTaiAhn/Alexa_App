package com.weber.cs3230.generators;

import java.util.List;

public class SkydivingGear extends QuestionVariable {
    @Override
    void addStrings() {
        answerListA.add("The small square that helps keep your canopy untangled.");
        answerListA.add("Part of the canopy that helps keep lines untangled.");
        answerListA.add("Helps inflate canopy by separating lines");
    }
    @Override
    public List<String> questionVariables() {
        questionVariables.add("AAD?");
        questionVariables.add("pilot chute?");
        return questionVariables;
    }
}