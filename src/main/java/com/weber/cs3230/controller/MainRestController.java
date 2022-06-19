package com.weber.cs3230.controller;

import com.weber.cs3230.AlexaIntentHandler;
import com.weber.cs3230.MetricRecorder;
import com.weber.cs3230.dto.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @Autowired
    public MainRestController() {
    }
    // replaces getAnswerForIntent
    @RequestMapping("/health")
    public String healthCheck() {
        // do metrics recroder in here
        MetricRecorder metricRecorder = new MetricRecorder();
        metricRecorder.saveMetric("Jumprun");
        // Here I dont know if I'm actually returning a metric with a populated metric id.
        // I'm terrible with debuggers, and I wasn't able to determine if this was the case.
        return "up and running";
    }
}