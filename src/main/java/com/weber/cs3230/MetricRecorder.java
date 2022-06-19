package com.weber.cs3230;

import com.google.gson.Gson;
import com.weber.cs3230.dto.Metric;
import org.springframework.http.HttpMethod;

public class MetricRecorder {

    private HttpCommunicator httpCommunicator = new HttpCommunicator();
    private Metric metric = new Metric();
    final String json = new Gson().toJson(metric);

    // makes metric object

    public void saveMetric(String eventName)   {
        metric.setEventName(eventName);
        metric.setAppName("Skybot");
    // pass the HTTPCommunicator what it needs...
//        HttpCommunicator httpCommunicator = new HttpCommunicator();
        httpCommunicator.communicate(HttpMethod.POST, "http://localhost:8080", json, metric.getClass());

        // metric.class gives me the class definition
        // calls it metrics.class
        // post, url , json
    }
}
