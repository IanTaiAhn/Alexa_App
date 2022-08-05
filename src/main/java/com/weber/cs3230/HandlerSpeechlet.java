package com.weber.cs3230;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import com.weber.cs3230.alexa.AlexaIntentHandler;
import com.weber.cs3230.alexa.AlexaUtils;
import com.weber.cs3230.dto.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandlerSpeechlet implements SpeechletV2 {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
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
        log.info("Skybot was successfully launched from your phone! Craaazy.");
        return alexaUtils.getOnLaunchResponse(requestEnvelope.getSession(), "Launching...", "Welcome to skybot");
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
        try {
            Answer answer = alexaIntentHandler.handleIntent(requestEnvelope.getRequest().getIntent().getName());
            if (answer == null) {
                log.warn("The Intent was null, or unrecognizable");
                return alexaUtils.getUnrecognizedResponse(requestEnvelope.getSession(), "Failed to recognize", "I don't understand what you're asking me.");
            }
            log.info("onIntent did something good I believe..");
            return alexaUtils.getNormalResponse(requestEnvelope.getSession(), "Skybot says...", answer.getText());
        } catch (NoAvailableAnswerException e)  {
            log.error("error message: ", e.getAlexaIntent());
            return alexaUtils.getOnLaunchResponse(requestEnvelope.getSession(), "Launch failed differently", "Skybot won't boot...");
        } catch (Exception e)  {
            log.error("error message: ", e);
            return alexaUtils.getOnLaunchResponse(requestEnvelope.getSession(), "Launch failed", "Something terribly wrong has occurred");
        }
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
    log.info("onSessionEnded method has ended.");
    }
}
