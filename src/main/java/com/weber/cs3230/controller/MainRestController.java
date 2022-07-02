package com.weber.cs3230.controller;

import com.weber.cs3230.MetricRecorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MainRestController() {
    }
    // replaces getAnswerForIntent
    @RequestMapping("/health")
    public String healthCheck() {
        MetricRecorder metricRecorder = new MetricRecorder();
        metricRecorder.saveMetric("Jumprun");
        log.info("Health Check Success!");
        return "up and running";
    }
}