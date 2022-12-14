package com.weber.cs3230.adminapp.api;

public class IntentAnswer {

    private long answerID;
    private String text;
    private long intentID;
    private String dateAdded;

    public long getAnswerID() {
        return answerID;
    }

    public void setAnswerID(long answerID) {
        this.answerID = answerID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getIntentID() {
        return intentID;
    }

    public void setIntentID(long intentID) {
        this.intentID = intentID;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
