package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.Station;
import com.example.public_transportation_project1.Service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stations") // כתובת הבסיס: http://localhost:8081/api/stations
public class StationController {

    @Autowired
    private StationService stationService; // הזרקת השירות לניהול תחנות

    @GetMapping("/all") // רשימה של כל התחנות הקיימות (למשל: בני ברק, ירושלים)
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/{id}") // חיפוש תחנה לפי קוד
    public Optional<Station> getStationById(@PathVariable Long id) {
        return stationService.getStationById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add") // הוספת תחנה פיזית חדשה למערכת
    public Station addStation(@RequestBody Station station) {
        return stationService.saveStation(station);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") // מחיקת תחנה מהמערכת
    public void deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
    }
}