package com.example.weather.controller;

import com.example.weather.model.WeatherData;
import com.example.weather.facade.WeatherServiceFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private final WeatherServiceFacade facade;

    public WeatherController(WeatherServiceFacade facade) { this.facade = facade; }

    @GetMapping("/current")
    public ResponseEntity<WeatherData> getCurrent(){
        WeatherData d = facade.getCurrent();
        if (d == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(d);
    }

    @PostMapping("/strategy/{type}")
    public ResponseEntity<String> switchStrategy(@PathVariable String type){
        try {
            facade.switchStrategy(type);
            return ResponseEntity.ok("Switched to " + type);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Unknown strategy: " + type);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error switching strategy: " + ex.getMessage());
        }
    }


    @PostMapping("/strategy/manual")
    public ResponseEntity<String> switchToManualWithData(@RequestBody WeatherData initialData){
        facade.switchStrategy("manual", initialData);
        return ResponseEntity.ok("Switched to manual and applied initial data");
    }

    @PostMapping("/manual")
    public ResponseEntity<String> manual(@RequestBody WeatherData dto){
        facade.manualUpdate(dto);
        return ResponseEntity.ok("Manual update applied");
    }

    @PostMapping("/updateNow")
    public ResponseEntity<String> updateNow(){
        facade.forceUpdateNow();
        return ResponseEntity.ok("Update triggered");
    }
}
