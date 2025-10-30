package com.example.weather.observer;


import com.example.weather.model.WeatherData;

public class ConsoleDisplay implements Observer {
    @Override
    public void update(WeatherData data) {
        System.out.println("[Console] " + data);
    }
}