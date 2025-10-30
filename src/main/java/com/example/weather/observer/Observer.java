package com.example.weather.observer;

import com.example.weather.model.WeatherData;

public interface Observer {
    void update(WeatherData data);
}