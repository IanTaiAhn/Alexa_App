package com.weber.cs3230;

import java.util.*;

/**
 *  Loops through the questions the chatbot knows, and has 3 other commands it knows.
 */
public class ChatBotQuestions {
    private List<String> list = new ArrayList<>();
    private HashMap<String, Integer> map = new HashMap<>();
    private HashSet<String> set = new HashSet<>();
    private boolean exitLoop = true;
    private int counter = 0;
    public void Questions()   {
        String string;
        Scanner scnr = new Scanner(System.in);
        string = scnr.nextLine();

        while (exitLoop) {
            if (string.equals("What is the correct pull altitude?")) {
                list.add("5500");
                list.add("4500");
                list.add("5000");
                // maybe.. randomly generate a number from 0-2 to choose the list item that will be selevted, and dispalyed as an ansewr.
                // we have a list of size 3, when we choose the list elemetn to represent we can increment hashmap's integer. use map.get(String),
//                and if the string matches, display it and increment the integer.
                // if the questions gets asked again, iterate through the map's integer value, and choose the smallest one.
                if (map.getOrDefault(list.get(0), 0) == 0)  {
//                    this code means we have been asked this question for the first time.
                    System.out.println(list.get(0));
                    map.put(list.get(0), 1);
                }
                if (map.get(list.get(0)) == 1)   {
                    System.out.println(list.get(1));
                    map.put(list.get(1), 1);
                }
                if (map.get(list.get(1)) == 1)  {
                    System.out.println(list.get(2));
                    map.put(list.get(1), 1);
                }

                for (String el : list)  {
//                    int counter = 0;
                    if (map.get(el) == 1)   {
                        counter++;
                    }
                    if (counter == list.size()) {
                        map.clear();
                        list.clear();
                    }
                }

//                System.out.println("5500");
            // Fill array list with multiple strings.

                Questions();
            }   else if (string.equals("What is a slider?"))    {
                System.out.println("The small square that helps keep your canopy untangled.");
                Questions();
            }   else if (string.equals("What altitude do you lock-on at?")) {
                System.out.println("6000");
                Questions();
            }   else if (string.equals("Why is skydiving fun?")) {
                System.out.println("Adrenaline is crazy");
                Questions();
            }   else if (string.equals("What is your decision altitude?")) {
                System.out.println("2500");
                Questions();
            }   else if (string.equals("When do you panic?")) {
                System.out.println("Never, always stay calm.");
                Questions();
            }   else if (string.equals("What is the most important part in a skydive?")) {
                System.out.println("Pull");
                Questions();
            }   else if (string.equals("What altitude do you drop at?")) {
                System.out.println("13000");
                Questions();
            }   else if (string.equals("What are the maximum winds you can jump at?")) {
                System.out.println("14mph");
                Questions();
            }   else if (string.equals("How do you regain stability during free fall?")) {
                System.out.println("Push your hips forwards, and arch.");
                Questions();
            }   else if (string.equals("exit")) {
                exitLoop = false;
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
