package com.example.weather.decorator;

import com.example.weather.model.WeatherData;
import com.example.weather.observer.Observer;

public class ThrottlingObserver implements Observer {
    private final Observer delegate;
    private final long minMillis;
    private volatile long lastNotified = 0;
    public ThrottlingObserver(Observer delegate, long minMillis){
        this.delegate = delegate; this.minMillis = minMillis;
    }
    @Override
    public void update(WeatherData data) {
        long now = System.currentTimeMillis();
        if (now - lastNotified >= minMillis) {
            delegate.update(data);
            lastNotified = now;
        } else {
            System.out.println("[Throttle] Skipped update for " + delegate.getClass().getSimpleName());
        }
    }
}