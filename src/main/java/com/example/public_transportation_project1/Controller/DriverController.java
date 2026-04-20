package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.Driver;
import com.example.public_transportation_project1.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService; //

    @GetMapping("/all") // רשימת כל הנהגים
    public List<Driver> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    @GetMapping("/{id}") // חיפוש נהג ספציפי
    public Optional<Driver> getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add") // הוספת נהג חדש
    public Driver addDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}") // עדכון פרטי נהג
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        return driverService.updateDriver(id, driver);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") // הסרת נהג מהמערכת
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}