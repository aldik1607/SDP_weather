package com.example.weather;

public interface UpdateStrategy {
    WeatherData fetch();
    default void shutdown() {}
}