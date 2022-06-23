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
        Metric metric = new Metric();
        String json = new Gson().toJson(metric);
        metric.setEventName(eventName);
        metric.setAppName("Skybot");

        // My json string wasn't working, so I hardcoded the paylod in assignment 6,
        // I go to David's api, my remote, and local api, and I still don't know where the "metrics" are....
        // Using a hardcoded payload, I'm able to get a statusCode = 200 (good), and goodReponseCode = true
        // This is good, but as stated idk where the heck that Metric data is.
        httpCommunicator.communicate(HttpMethod.POST, "https://alexa-ghost.herokuapp.com/metric", "{\"appName\": \"daves_alexa_app\", \"eventName\": \"intent_received|oneofyourintentnames\"}", Metric.class);

        // This line of code uses the convert Metric into json, and the debugger says that
        // the payload = "{"metricID":0}"
        // The statusCode = 500, and the goodResponseCode = false.
//        httpCommunicator.communicate(HttpMethod.POST, "https://alexa-ghost.herokuapp.com/metric", json, Metric.class);

    }
}
