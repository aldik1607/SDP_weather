package com.example.weather;


public final class WeatherData {
    private final double temperatureC;
    private final double humidity;
    private final double pressure;


    public WeatherData(double temperatureC, double humidity, double pressure) {
        this.temperatureC = temperatureC;
        this.humidity = humidity;
        this.pressure = pressure;
    }


    public double getTemperatureC() {
        return temperatureC;
    }


    public double getHumidity() {
        return humidity;
    }


    public double getPressure() {
        return pressure;
    }


    @Override
    public String toString() {
        return String.format("Temp: %.1f°C, Humidity: %.1f%%, Pressure: %.1f hPa",
                temperatureC, humidity, pressure);
    }
}