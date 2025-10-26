package com.example.weather;


public class ConsoleDisplay implements Observer {
    @Override
    public void update(WeatherData data) {
        System.out.println("[Console] " + data);
    }
}