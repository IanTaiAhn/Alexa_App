package com.weber.cs3230;

import com.weber.cs3230.dto.MetricRecorder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorThread {
    private static ExecutorService metricsExecutor;
    public ExecutorThread() {
        metricsExecutor = Executors.newCachedThreadPool();
//        Thread.sleep(2000);
    }

    public void submitMetricAsync()    {
            metricsExecutor.submit(() -> submitMetric());
        }
    private void submitMetric() {
        MetricRecorder metricRecorder = new MetricRecorder();
        metricRecorder.saveMetric("Jumprun");
    }
}
