package com.weber.cs3230;

import java.util.*;

/**
 *  Loops through the questions the chatbot knows, and has 3 other commands it knows.
 */
public class ChatBotQuestions {
    private List<String> list = new ArrayList<>();
    private Map<String, Integer> map = new HashMap<>();
    private Set<String> set = new HashSet<>();
    private boolean exitLoop = true;
    private int counter = 0;

    private String question1 = "What is the correct pull altitude?";
    private String question2 = "What is a slider?";
    private String question3 = "What altitude do you lock-on at?";
    private String question4 = "Why is skydiving fun?";
    private String question5 = "What is your decision altitude?";
    private String question6 = "When do you panic?";
    private String question7 = "What is the most important part in a skydive?";
    private String question8 = "What altitude do you drop at?";
    private String question9 = "What are the maximum winds you can jump at?";
    private String question10 = "How do you regain stability during free fall?";

    private String answer1 = "5500";
    private String answer2 = "The small square that helps keep your canopy untangled.";
    private String answer3 = "6000";
    private String answer4 = "Adrenaline is crazy";
    private String answer5 = "2500";
    private String answer6 = "Never, always stay calm.";
    private String answer7 = "Pull";
    private String answer8 = "13000";
    private String answer9 = "14mph";
    private String answer10 = "Push your hips forwards, and arch.";

    public void Questions()   {
        String string;
        Scanner scnr = new Scanner(System.in);
        string = scnr.nextLine();

        while (exitLoop) {
            if (string.equals(question1)) {
//                add question to hash set
                set.add(question1);
                System.out.println(answer1);
                Questions();
            }   else if (string.equals(question2))    {
                set.add(question2);
                System.out.println(answer2);
                Questions();
            }   else if (string.equals(question3)) {
                set.add(question3);
                System.out.println(answer3);
                Questions();
            }   else if (string.equals(question4)) {
                set.add(question4);
                System.out.println(answer4);
                Questions();
            }   else if (string.equals(question5)) {
                set.add(question5);
                System.out.println(answer5);
                Questions();
            }   else if (string.equals(question6)) {
                set.add(question6);
                System.out.println(answer6);
                Questions();
            }   else if (string.equals(question7)) {
                set.add(question7);
                System.out.println(answer7);
                Questions();
            }   else if (string.equals(question8)) {
                set.add(question8);
                System.out.println(answer8);
                Questions();
            }   else if (string.equals(question9)) {
                set.add(question9);
                System.out.println(answer9);
                Questions();
            }   else if (string.equals(question10)) {
                set.add(question10);
                System.out.println(answer10);
                Questions();
            }   else if (string.equals("exit")) {
                exitLoop = false;
                System.out.println("Questions Asked:");
                if (set.size() == 0)    {
                    System.out.println("You didn't ask anything :(");
                }
                for(String questions : set)    {
                    System.out.println(questions);
                }
                System.out.println();
                System.out.println("Dum dum Chatbot powering down...");
            }   else if (string.equals("commands"))    {
                ChatBotCommands commands = new ChatBotCommands();
                commands.Commands();
                Questions();
            }   else if (string.equals("list")) {
                QuestionList();
                Questions();
            }
            else {
                System.out.println("I don't know about this one, ask another?");
                Questions();
            }
        }
    }

    public void QuestionList()   {
        // lists all available questions..
            System.out.println(question1);
            System.out.println(question2);
            System.out.println(question3);
            System.out.println(question4);
            System.out.println(question5);
            System.out.println(question6);
            System.out.println(question7);
            System.out.println(question8);
            System.out.println(question9);
            System.out.println(question10);
        }
}
