package com.example.weather.decorator;

import com.example.weather.model.WeatherData;
import com.example.weather.observer.Observer;

public class LoggingObserver implements Observer {
    private final Observer delegate;
    public LoggingObserver(Observer delegate){ this.delegate = delegate; }
    @Override
    public void update(WeatherData data) {
        System.out.println("[LOG] notifying " + delegate.getClass().getSimpleName() + " -> " + data);
        delegate.update(data);
    }
}