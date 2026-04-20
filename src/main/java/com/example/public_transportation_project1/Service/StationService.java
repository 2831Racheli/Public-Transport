package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Station;
import java.util.List;
import java.util.Optional;

public interface StationService {
    Station saveStation(Station station); // הוספת תחנה חדשה למפה
    Optional<Station> getStationById(Long id);
    List<Station> getAllStations(); // רשימת כל התחנות הקיימות
    void deleteStation(Long id);
}