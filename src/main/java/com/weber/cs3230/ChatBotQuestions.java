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

    private Map<String, List<String>> questionsAndAnswers = new HashMap<>();
    private Map<String, String> mostRecentQuestion = new HashMap<>();
    private List<String> answers1 = new ArrayList<>();
    private List<String> answers2 = new ArrayList<>();
    private List<String> answers3 = new ArrayList<>();
    private List<String> answers4 = new ArrayList<>();
    private List<String> answers5 = new ArrayList<>();
    private List<String> answers6 = new ArrayList<>();
    private List<String> answers7 = new ArrayList<>();
    private List<String> answers8 = new ArrayList<>();
    private List<String> answers9 = new ArrayList<>();
    private List<String> answers10 = new ArrayList<>();
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

    private String answer1 = "5500.";
    private String answer2 = "The small square that helps keep your canopy untangled.";
    private String answer3 = "6000.";
    private String answer4 = "Adrenaline is crazy.";
    private String answer5 = "2500.";
    private String answer6 = "Never, always stay calm.";
    private String answer7 = "Pull.";
    private String answer8 = "13000.";
    private String answer9 = "14mph.";
    private String answer10 = "Push your hips forwards, and arch.";
    private boolean first = true;

    public void addAnswers()   {
        answers1.add(answer1);
        answers1.add("4500");
        answers1.add("5000");
        questionsAndAnswers.put(question1, answers1);

        answers2.add(answer2);
        answers2.add("Part of the canopy that helps keep lines untangled.");
        answers2.add("Helps inflate canopy by separating lines.");
        questionsAndAnswers.put(question2, answers2);

        answers3.add(answer3);
        answers3.add("5500");
        answers3.add("5000");
        questionsAndAnswers.put(question3, answers3);

        answers4.add(answer4);
        answers4.add("It's sick.");
        answers4.add("Because it is.");
        questionsAndAnswers.put(question4, answers4);

        answers5.add(answer5);
        answers5.add("It's safer to pull before 2500.");
        answers5.add("You don't have to wait till 2500, the quicker the better.");
        questionsAndAnswers.put(question5, answers5);

        answers6.add(answer6);
        answers6.add("Calm people live.");
        answers6.add("If your main, and reserve parachute fail...");
        questionsAndAnswers.put(question6, answers6);

        answers7.add(answer7);
        answers7.add("Pull at correct altitude.");
        answers7.add("Pull while stable.");
        questionsAndAnswers.put(question7, answers7);

        answers8.add(answer8);
        answers8.add("15000.");
        answers8.add("14000.");
        questionsAndAnswers.put(question8, answers8);

        answers9.add(answer9);
        answers9.add("Depending on the wind direction, 15mph.");
        answers9.add("B licensed jumpers make their own calls.");
        questionsAndAnswers.put(question9, answers9);

        answers10.add(answer10);
        answers10.add("Keep calm, and don't flail.");
        answers10.add("Create as much air resistance as possible.");
        questionsAndAnswers.put(question10, answers10);
    }
    public void Questions()   {
        String string;
        Scanner scnr = new Scanner(System.in);
        string = scnr.nextLine();
        while (exitLoop) {
            if (string.equals(question1)) {
                randomNoDoubleAnswer(question1);
                Questions();

            }   else if (string.equals(question2))    {
                randomNoDoubleAnswer(question2);
                Questions();

            }   else if (string.equals(question3)) {
                randomNoDoubleAnswer(question3);
                Questions();

            }   else if (string.equals(question4)) {
                randomNoDoubleAnswer(question4);
                Questions();

            }   else if (string.equals(question5)) {
                randomNoDoubleAnswer(question5);
                Questions();

            }   else if (string.equals(question6)) {
                randomNoDoubleAnswer(question6);
                Questions();

            }   else if (string.equals(question7)) {
                randomNoDoubleAnswer(question7);
                Questions();

            }   else if (string.equals(question8)) {
                randomNoDoubleAnswer(question8);
                Questions();

            }   else if (string.equals(question9)) {
                randomNoDoubleAnswer(question9);
                Questions();

            }   else if (string.equals(question10)) {
                randomNoDoubleAnswer(question10);
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

    private void randomNoDoubleAnswer(String question) {
        set.add(question);
//                only happen once
        if (first) {
            List<String> answers = questionsAndAnswers.get(question); // gives a list of answers
            Collections.shuffle(answers);
            mostRecentQuestion.put(question, answers.get(0));
        }
        first = false;
        boolean diff = true;
        while (diff) {
            List<String> answers = questionsAndAnswers.get(question); // gives a list of answers
            Collections.shuffle(answers);
            if (!answers.get(0).equals(mostRecentQuestion.get(question)))   {
                System.out.println(answers.get(0));
                mostRecentQuestion.put(question, answers.get(0));
                diff = false;
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
