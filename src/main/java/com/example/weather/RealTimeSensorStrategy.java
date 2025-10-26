package com.example.weather;

import java.util.Random;

public class RealTimeSensorStrategy implements UpdateStrategy {
    private final Random rnd = new Random();
    private double baseTemp;
    private double baseHumidity;
    private double basePressure;


    public RealTimeSensorStrategy(double baseTemp, double baseHumidity, double basePressure) {
        this.baseTemp = baseTemp;
        this.baseHumidity = baseHumidity;
        this.basePressure = basePressure;
    }


    @Override
    public WeatherData fetch() {
        double t = baseTemp + rnd.nextGaussian() * 0.5;
        double h = clamp(baseHumidity + rnd.nextGaussian() * 1.5, 0, 100);
        double p = basePressure + rnd.nextGaussian() * 0.8;
        baseTemp += rnd.nextGaussian() * 0.02;
        baseHumidity = clamp(baseHumidity + rnd.nextGaussian() * 0.05, 0, 100);
        basePressure += rnd.nextGaussian() * 0.01;
        return new WeatherData(t, h, p);
    }


    private double clamp(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}