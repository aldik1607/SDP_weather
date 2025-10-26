package com.example.weather;

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
        notifyObservers();
    }


    public synchronized void notifyObservers() {
        for (Observer o : new ArrayList<>(observers)) {
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