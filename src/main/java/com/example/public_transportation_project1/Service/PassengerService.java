package com.example.public_transportation_project1.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * ממשק שירות עבור נוסעים - פונקציות "קל-קו"
 */
public interface PassengerService {
    
    // קבלת זמני הגעה של קו לתחנה (מחושב לפי סדר תחנות)
    List<LocalTime> getArrivalTimesByLine(Long stationId, String lineNumber);
    
    // קווים שעוברים בתחנה מסוימת
    List<String> getLinesAtStation(Long stationId);
    
    // תכנון מסלול (חיבור בין שתי תחנות)
    List<String> planRoute(Long sourceStationId, Long destStationId);

    // קבלת כל התחנות של קו מסוים עם השמות שלהן
    List<String> getStationsByLineNumber(String lineNumber);

    // פונקציות נסיעה ממוקדות בפורמט טקסט קריא:
    
    // 1. כל הנסיעות של קו מסוים (ללא סינון תאריך)
    List<String> getTravelsByLineNumber(String lineNumber);

    // 2. נסיעות של קו ביום ספציפי (השנה מתעדכנת אוטומטית)
    List<String> getTravelsByDay(String lineNumber, int month, int day);

    // 3. נסיעות של קו היום בשעה מסוימת
    List<String> getTravelsByHour(String lineNumber, int hour);

    // 4. הנסיעה האחרונה של הקו המתוכננת להיום
    String getLastTravelOfDay(String lineNumber);
    String getNextArrivalDynamic(Long stationId, String lineNumber);
}