package com.weber.cs3230;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.weber.cs3230.dto.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerSpeechlet implements SpeechletV2 {

    private final AlexaIntentHandler alexaIntentHandler;
    private final AlexaUtils alexaUtils = new AlexaUtils("Is there anything else you'd like to ask me?");

    @Autowired
    public HandlerSpeechlet(AlexaIntentHandler alexaIntentHandler) {
        this.alexaIntentHandler = alexaIntentHandler;
    }

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {

    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
        return alexaUtils.getOnLaunchResponse(requestEnvelope.getSession(), "Launching...", "Welcome to skybot");
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        Answer answer = alexaIntentHandler.handleIntent(requestEnvelope.getRequest().getIntent().getName());
        if (answer == null)    {
            return alexaUtils.getUnrecognizedResponse(requestEnvelope.getSession(), "Failed to recognize", "I don't understand what you're asking me.");
        }
        return alexaUtils.getNormalResponse(requestEnvelope.getSession(), "Skyboy says...", answer.getText());
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {

    }
}
