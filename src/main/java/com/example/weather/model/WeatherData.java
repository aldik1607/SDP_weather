package com.example.weather.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class WeatherData {
    private final double temperatureC;
    private final double humidity;
    private final double pressure;

    @JsonCreator
    public WeatherData(
            @JsonProperty("temperatureC") double temperatureC,
            @JsonProperty("humidity") double humidity,
            @JsonProperty("pressure") double pressure) {
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperatureC() { return temperatureC; }
    public double getHumidity() { return humidity; }
    public double getPressure() { return pressure; }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US,
                "Temp: %.1f°C, Humidity: %.1f%%, Pressure: %.1f hPa",
                temperatureC, humidity, pressure);
    }
}
