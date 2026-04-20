package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.Travel;
import com.example.public_transportation_project1.Service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travels")
public class TravelController {

    @Autowired
    private TravelService travelService; //

    // @GetMapping("/all") // מציג את כל הנסיעות המתוכננות (לוח זמנים)
    // public List<Travel> getAllTravels() {
    //     return travelService.getAllTravels();
    // }
    @GetMapping("/all-formatted")
    public List<String> getAllTravels() {
       return travelService.getAllTravelsFormatted();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add") // יצירת נסיעה חדשה (קו + נהג + אוטובוס + שעה)
    public Travel addTravel(@RequestBody Travel travel) {
    // חשוב להשתמש בפונקציה עם הולידציות שבנית
    return travelService.saveTravelWithValidation(travel);
    }
    // פונקציית "קל-קו": קבלת זמן הגעה משוער לתחנה מסוימת
    // דוגמת שימוש: /api/travels/arrival-time?travelId=1&stationOrder=5
    @GetMapping("/arrival-time")
    public String getArrivalTime(@RequestParam Long travelId, @RequestParam int stationOrder) {
        return travelService.getEstimatedArrivalTime(travelId, stationOrder);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") // ביטול נסיעה
    public void deleteTravel(@PathVariable Long id) {
        travelService.deleteTravel(id);
    }
}