package com.weber.cs3230;

import java.util.Scanner;

/**
 *  Loops through the questions the chatbot knows, and has 3 other commands it knows.
 */
public class ChatBotQuestions {
    static boolean exitLoop = true;
    public static void Questions()   {
        String string;
        Scanner scnr = new Scanner(System.in);
        string = scnr.nextLine();

        while (exitLoop) {
            if (string.equals("What is the correct pull altitude?")) {
                System.out.println("5500");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What is a slider?"))    {
                System.out.println("The small square that helps keep your canopy untangled.");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What altitude do you lock-on at?")) {
                System.out.println("6000");
                ChatBotQuestions.Questions();
            }   else if (string.equals("Why is skydiving fun?")) {
                System.out.println("Adrenaline is crazy");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What is your decision altitude?")) {
                System.out.println("2500");
                ChatBotQuestions.Questions();
            }   else if (string.equals("When do you panic?")) {
                System.out.println("Never, always stay calm.");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What is the most important part in a skydive?")) {
                System.out.println("Pull");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What altitude do you drop at?")) {
                System.out.println("13000");
                ChatBotQuestions.Questions();
            }   else if (string.equals("What are the maximum winds you can jump at?")) {
                System.out.println("14mph");
                ChatBotQuestions.Questions();
            }   else if (string.equals("How do you regain stability during free fall?")) {
                System.out.println("Push your hips forwards, and arch.");
                ChatBotQuestions.Questions();
            }   else if (string.equals("exit")) {
                exitLoop = false;
                System.out.println("Dum dum Chatbot powering down...");
            }   else if (string.equals("commands"))    {
                ChatBotCommands.Commands();
                ChatBotQuestions.Questions();
            }   else if (string.equals("list")) {
                ChatBotQuestions.QuestionList();
                ChatBotQuestions.Questions();
            }
            else {
                System.out.println("I don't know about this one, ask another?");
                ChatBotQuestions.Questions();
            }
        }
    }

    public static void QuestionList()   {
        // lists all available questions..
            System.out.println("What is the correct pull altitude?");
            System.out.println("What is a slider?");
            System.out.println("What altitude do you lock-on at?");
            System.out.println("Why is skydiving fun?");
            System.out.println("What is your decision altitude?");
            System.out.println("When do you panic?");
            System.out.println("What is the most important part in a skydive?");
            System.out.println("What altitude do you drop at?");
            System.out.println("What are the maximum winds you can jump at?");
            System.out.println("How do you regain stability during free fall?");
        }
}
