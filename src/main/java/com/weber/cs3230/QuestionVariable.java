package com.weber.cs3230;

import java.util.ArrayList;
import java.util.List;

public abstract class QuestionVariable extends AnswerGenerator{
    public List<String> questionVariables = new ArrayList<>();
    abstract public List<String> questionVariables();
}
