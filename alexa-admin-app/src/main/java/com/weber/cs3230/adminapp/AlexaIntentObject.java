package com.weber.cs3230.adminapp;

public class AlexaIntentObject {

    private long ID;
    private String intentName;
    private String dateAdded;

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public AlexaIntentObject(long ID, String intentName, String dateAdded) {
        this.ID = ID;
        this.intentName = intentName;
        this.dateAdded = dateAdded;
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
}
