package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverService {
    Driver saveDriver(Driver driver); // שמירת נהג
    Optional<Driver> getDriverById(Long id); // מציאת נהג לפי קוד
    List<Driver> getAllDrivers(); // רשימת כל הנהגים
    void deleteDriver(Long id); // מחיקת נהג
    Driver updateDriver(Long id, Driver driver); // עדכון פרטי נהג
}