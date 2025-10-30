package com.example.weather.strategy;

import com.example.weather.model.WeatherData;

import java.time.LocalDateTime;
import java.util.Random;

public class ScheduledBatchStrategy implements UpdateStrategy {
    private final Random rnd = new Random();

    @Override
    public WeatherData fetch() {
        double t = 5 + rnd.nextDouble() * 30; // 5..35°C
        double h = 15 + rnd.nextDouble() * 70; // 15..85%
        double p = 970 + rnd.nextDouble() * 60; // 970..1030 hPa
        System.out.println("[ScheduledBatch] snapshot at " + LocalDateTime.now());
        return new WeatherData(t, h, p);
    }
}







