package com.example.weather.strategy;

import com.example.weather.model.WeatherData;

public interface UpdateStrategy {
    WeatherData fetch();
    default void shutdown() {}
}