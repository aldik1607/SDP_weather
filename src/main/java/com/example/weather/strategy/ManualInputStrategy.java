package com.example.weather.strategy;

import com.example.weather.model.WeatherData;

public class ManualInputStrategy implements UpdateStrategy {
    private volatile WeatherData manualData;


    public void setManualData(WeatherData manualData) {
        this.manualData = manualData;
    }


    @Override
    public WeatherData fetch() {
        if (manualData == null) {
            throw new IllegalStateException("Manual data not set. Call setManualData(...) first.");
        }
        return manualData;
    }
}