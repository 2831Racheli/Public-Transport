package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Bus;
import java.util.List;
import java.util.Optional;

public interface BusService {
    Bus saveBus(Bus bus); // שמירת אוטובוס
    Bus addBus(String licensePlate, int seats); // יצירת אוטובוס חדש עם פרטים
    Optional<Bus> getBusById(Long id); // מציאת אוטובוס לפי קוד
    Optional<Bus> getBusByLicensePlate(String licensePlate); // חיפוש לפי לוחית רישוי
    List<Bus> getAllBuses(); // רשימת כל האוטובוסים
    List<Bus> getBusesBySeats(int minSeats); // חיפוש אוטובוסים גדולים מספיק
    void deleteBus(Long id); // מחיקת אוטובוס
    Bus updateBus(Long id, Bus bus); // עדכון פרטי אוטובוס
}