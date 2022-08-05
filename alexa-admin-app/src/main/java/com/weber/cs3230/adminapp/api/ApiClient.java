package com.weber.cs3230.adminapp.api;

import com.google.gson.Gson;

import java.util.List;

public class ApiClient {

    private final HttpCommunicator httpCommunicator = new HttpCommunicator();
    private final String baseUrl = "http://localhost:8080";

    // test with main method for this here i think.
    public static void main(String[] args)  {
        ApiClient apiClient = new ApiClient();
        // test apiClient methods here.
//        System.out.println(apiClient.getMetrics().getMetrics().get(0).getEventName());
//        System.out.println("hmm");
        //intentID's start at 45, and ends at 54
        /*
        for (IntentDetail el : apiClient.getIntents().getIntents())  {
            System.out.println("IntentID: " + el.getIntentID());
            System.out.println("IntentName: " + el.getName());
            System.out.println("dateadded: " + el.getDateAdded());
        }

        System.out.println();

        for (IntentAnswer el : apiClient.getAnswers(46).getAnswers())  {
            System.out.println("AnswerID: " + el.getAnswerID());
            System.out.println("IntentID: " + el.getIntentID());
            System.out.println("IntentDesc: " + el.getText());
            System.out.println("dateadded: " + el.getDateAdded());
        }
        */
    }

    public boolean validateCreds(String username, String password) {
        try {
            Credentials credentials = new Credentials();
            credentials.setUsername(username);
            credentials.setPassword(password);
            String response = httpCommunicator.communicate("POST", baseUrl + "/credentials", new Gson().toJson(credentials), String.class);
            System.out.println("valid creds response: " + response);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to validate creds");
            e.printStackTrace();
            return false;
        }
    }

    public IntentDetailList getIntents() {
        return httpCommunicator.communicate("GET", baseUrl + "/intents", IntentDetailList.class);
    }

    public IntentDetail saveNewIntent(String name) {
        IntentDetail intent = new IntentDetail();
        intent.setName(name);
        return httpCommunicator.communicate("POST", baseUrl + "/intent", new Gson().toJson(intent), IntentDetail.class);
    }

    public IntentDetail updateIntent(long intentID, String name) {
        IntentDetail intent = new IntentDetail();
        intent.setIntentID(intentID);
        intent.setName(name);
        return httpCommunicator.communicate("PUT", baseUrl + "/intent/" + intentID, new Gson().toJson(intent), IntentDetail.class);
    }

    public void deleteIntent(long intentID) {
        httpCommunicator.communicate("DELETE", baseUrl + "/intent/" + intentID, String.class);
    }

    public IntentAnswerList getAnswers(long intentID) {
        return httpCommunicator.communicate("GET", baseUrl + "/intent/" + intentID + "/answers", IntentAnswerList.class);
    }

    public IntentAnswer saveNewAnswer(long intentID, String text) {
        IntentAnswer answer = new IntentAnswer();
        answer.setText(text);
        answer.setIntentID(intentID);
        return httpCommunicator.communicate("POST", baseUrl + "/intent/" + intentID + "/answer", new Gson().toJson(answer), IntentAnswer.class);
    }

    public IntentAnswer updateAnswer(long intentID, long answerID, String text) {
        IntentAnswer answer = new IntentAnswer();
        answer.setText(text);
        answer.setIntentID(intentID);
        answer.setAnswerID(answerID);
        return httpCommunicator.communicate("PUT", baseUrl + "/intent/" + intentID + "/answer/" + answerID, new Gson().toJson(answer), IntentAnswer.class);
    }

    public void deleteAnswer(long intentID, long answerID) {
        httpCommunicator.communicate("DELETE", baseUrl + "/intent/" + intentID + "/answer/" + answerID, String.class);
    }

    public MetricUIList getMetrics() {
        return httpCommunicator.communicate("GET", baseUrl + "/metrics", MetricUIList.class);
    }
}
