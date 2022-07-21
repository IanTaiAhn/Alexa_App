package com.weber.cs3230.adminapp;

import java.util.ArrayList;

public class AlexaIntent {

    private long ID;
    private String intentName;
    private String dateAdded;

    private ArrayList intentAnswers;



    public AlexaIntent(long ID, String intentName, String dateAdded, ArrayList intentAnswers) {
        this.ID = ID;
        this.intentName = intentName;
        this.dateAdded = dateAdded;
        this.intentAnswers = intentAnswers;
    }

    public long getID() {
        return ID;
    }

    public String getIntentName() {
        return intentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }
    public ArrayList getIntentAnswerList() {
        return intentAnswers;
    }


    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }
}
