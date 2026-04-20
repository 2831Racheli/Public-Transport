package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Travel;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface TravelService {
    Travel saveTravel(Travel travel); // שמירת נסיעה ספציפית
    Optional<Travel> getTravelById(Long id); // מציאת נסיעה
    List<Travel> getAllTravels(); // לוח זמנים כללי
    void deleteTravel(Long id); // ביטול נסיעה
    Travel updateTravel(Long id, Travel travel); // עדכון נסיעה
    
    // לוגיקת "קל-קו": חישוב זמן הגעה לתחנה מסוימת במסלול
    String getEstimatedArrivalTime(Long travelId, int stationOrder);
    
    // פונקציות ולידציה ובטיחות
    // בדיקת זמינות נהג בזמן מסוים
    boolean isDriverAvailable(Long driverId, LocalDateTime departureTime);
    
    // בדיקת זמינות אוטובוס בזמן מסוים
    boolean isBusAvailable(Long busId, LocalDateTime departureTime);
    
    // שמירת נסיעה עם ולידציות
    Travel saveTravelWithValidation(Travel travel);
   // הוסיפי אלו ל-PassengerService.java
   List<Travel> getTravelsByDay(String lineNumber, int year, int month, int day);
   List<Travel> getTravelsByHour(String lineNumber, int year, int month, int day, int hour);
   java.util.Optional<Travel> getLastTravelOfDay(String lineNumber, int year, int month, int day);
   List<String> getAllTravelsFormatted();
}