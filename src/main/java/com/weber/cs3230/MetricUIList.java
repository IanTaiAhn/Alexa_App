package com.weber.cs3230;

import java.util.ArrayList;
import java.util.List;

public class MetricUIList {
    private List<MetricUI> metrics = new ArrayList<>();

    public List<MetricUI> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<MetricUI> metrics) {
        this.metrics = metrics;
    }
}
