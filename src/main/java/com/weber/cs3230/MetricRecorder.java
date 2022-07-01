package com.weber.cs3230;

import com.google.gson.Gson;
import com.weber.cs3230.dto.Metric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

public class MetricRecorder {
    private final HttpCommunicator httpCommunicator = new HttpCommunicator();

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void saveMetric(String eventName)   {
        try {
            Metric metric = new Metric();
            metric.setEventName(eventName);
            metric.setAppName("Skybot");
            String json = new Gson().toJson(metric);
            log.info("MetricID: " + metric.getMetricID());

            Metric metricRecorder =  httpCommunicator.communicate(HttpMethod.POST, "https://alexa-ghost.herokuapp.com/metric", json, Metric.class);
            System.out.println("breakpoint");
        } catch (Exception e)   {
            log.error("exception has been caught... app still runs tho! ", e);
        }
    }
}
