package com.example.weather;

public class PhoneDisplay implements Observer {
    private final String id;


    public PhoneDisplay(String id) {
        this.id = id;
    }


    @Override
    public void update(WeatherData data) {
        System.out.println(String.format("[Phone %s] Push -> %s", id, data));
    }
}