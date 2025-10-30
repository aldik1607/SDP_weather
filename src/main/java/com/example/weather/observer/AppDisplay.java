package com.example.weather.observer;

import com.example.weather.model.WeatherData;

public class AppDisplay implements Observer {
    private final String appName;


    public AppDisplay(String appName) {
        this.appName = appName;
    }


    @Override
    public void update(WeatherData data) {
        System.out.println(String.format("[App %s] UI update -> %s", appName, data));
    }
}