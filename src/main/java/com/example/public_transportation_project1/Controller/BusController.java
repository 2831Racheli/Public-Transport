package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.Bus;
import com.example.public_transportation_project1.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buses") // הכתובת הבסיסית לכל הפקודות כאן
public class BusController {

    @Autowired
    private BusService busService; // הזרקת השירות לניהול אוטובוסים

    @GetMapping("/all") // מחזיר את כל האוטובוסים הקיימים
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @GetMapping("/{id}") // חיפוש אוטובוס לפי קוד זיהוי פנימי
    public Optional<Bus> getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @GetMapping("/license/{plate}") // חיפוש לפי לוחית רישוי
    public Optional<Bus> getBusByLicensePlate(@PathVariable String plate) {
        return busService.getBusByLicensePlate(plate);
    }

    @GetMapping("/min-seats/{seats}") // חיפוש אוטובוסים עם מינימום מקומות
    public List<Bus> getBusesBySeats(@PathVariable int seats) {
        return busService.getBusesBySeats(seats);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add") // הוספת אוטובוס חדש למערכת
    public Bus addBus(@RequestBody Bus bus) {
        return busService.saveBus(bus);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}") // עדכון פרטי אוטובוס קיים
    public Bus updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        return busService.updateBus(id, bus);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") // מחיקת אוטובוס מהמערכת
    public void deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
    }
}