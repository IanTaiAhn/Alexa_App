package com.weber.cs3230;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlexaServiceApplication {

    public static void main(String[] args) {
        MetricRecorder metricRecorder = new MetricRecorder();
        metricRecorder.saveMetric("Testing Metrics... idk if its working");
        // Again, just doing what I think is wanted for the assignment. I dont know if I actually am saving the metric,
        // and if HTTPCommunicator is returning a metric, with a populated metricID.
        SpringApplication.run(AlexaServiceApplication.class, args);
    }

}