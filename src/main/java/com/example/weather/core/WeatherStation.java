package com.example.weather.core;

import com.example.weather.observer.Observer;
import com.example.weather.model.WeatherData;
import com.example.weather.strategy.UpdateStrategy;
import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    private final List<Observer> observers = new ArrayList<>();
    private UpdateStrategy strategy;
    private WeatherData lastData;


    public WeatherStation(UpdateStrategy initialStrategy) {
        this.strategy = initialStrategy;
    }


    public synchronized void registerObserver(Observer o) {
        observers.add(o);
    }


    public synchronized void unregisterObserver(Observer o) {
        observers.remove(o);
    }


    public synchronized void setStrategy(UpdateStrategy strategy) {
        if (this.strategy != null) {
            this.strategy.shutdown();
        }
        this.strategy = strategy;
        System.out.println("[WeatherStation] Strategy set to " + strategy.getClass().getSimpleName());
    }


    public synchronized void updateNow() {
        if (strategy == null) throw new IllegalStateException("No update strategy set.");
        lastData = strategy.fetch();
        System.out.println("[WeatherStation] Fetched: " + lastData);
        if (lastData == null) {
            System.out.println("[WeatherStation] No data fetched; observers not notified.");
            return;
        }
        notifyObservers();
    }


    public void notifyObservers() {
        List<Observer> snapshot;
        synchronized (this) {
            snapshot = new ArrayList<>(observers);
        }
        for (Observer o : snapshot) {
            try {
                o.update(lastData);
            } catch (Exception ex) {
                System.err.println("Error notifying observer: " + ex.getMessage());
            }
        }
    }



    public synchronized WeatherData getLastData() {
        return lastData;
    }
}