package com.weber.cs3230;

public class NoAvailableAnswerException extends Exception{
    private AlexaIntent alexaIntent;

    public NoAvailableAnswerException(AlexaIntent alexaIntent) {
        this.alexaIntent = alexaIntent;
    }

    public NoAvailableAnswerException() {

    }

    public AlexaIntent getAlexaIntent() {
        return alexaIntent;
    }
}
