package com.weber.cs3230;

import com.google.gson.Gson;
import com.weber.cs3230.dto.Metric;
import org.springframework.http.HttpMethod;

public class MetricRecorder {
    private final HttpCommunicator httpCommunicator = new HttpCommunicator();

    public void saveMetric(String eventName)   {
        Metric metric = new Metric();
        String json = new Gson().toJson(metric);
        metric.setEventName(eventName);
        metric.setAppName("Skybot");
        // I'm not sure if this is what is needed to communicate with HTTPCommunicator. I don't know if i'm saving metric data or not.
        httpCommunicator.communicate(HttpMethod.POST, "https://alexa-ghost.herokuapp.com/metric", json, Metric.class);
//        return httpCommunicator.communicate(HttpMethod.POST, "https://alexa-ghost.herokuapp.com/metric", json, Metric.class);

    }
}
