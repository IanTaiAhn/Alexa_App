package com.weber.cs3230;

import java.util.Scanner;

public class ChatBotMain {

    public static void main(String[] args) {
        // perhaps give an option to provide a list of questions available for testing..
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the dumb chat bot extravaganza");
        System.out.println("Ask a question!");

        ChatBotQuestions.Questions();
//        System.out.println(sTest);

    }
}
