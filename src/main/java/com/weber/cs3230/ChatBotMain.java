package com.weber.cs3230;

import java.util.Scanner;

/**
 * Main class where the program begins, and the method that activates the chatbot is called.
 */
public class ChatBotMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the dumb chat bot extravaganza");
        System.out.println("Type 'commands' to get started with the chatbot!");
        ChatBotQuestions.Questions();
    }
}
