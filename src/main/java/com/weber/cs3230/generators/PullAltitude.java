package com.weber.cs3230.generators;

public class PullAltitude extends AnswerGenerator {
    @Override
    void addStrings() {
        answerListA.add("5500");
        answerListA.add("4500");
        answerListA.add("5000");
    }
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
