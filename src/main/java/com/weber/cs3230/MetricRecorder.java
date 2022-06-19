package com.weber.cs3230;

import com.google.gson.Gson;
import com.weber.cs3230.dto.Metric;
import org.springframework.http.HttpMethod;

public class MetricRecorder {

    private final HttpCommunicator httpCommunicator = new HttpCommunicator();
    private final Metric metric = new Metric();
    final String json = new Gson().toJson(metric);

    // makes metric object

    public void saveMetric(String eventName)   {
        metric.setEventName(eventName);
        metric.setAppName("Skybot");
        // I'm not sure if this is what is needed to communicate with HTTPCommunicator. I don't know if i'm saving metric data or not.
        httpCommunicator.communicate(HttpMethod.POST, "https://alexa-knows-skydiving.herokuapp.com/metric", json, metric.getClass());
    }
}
