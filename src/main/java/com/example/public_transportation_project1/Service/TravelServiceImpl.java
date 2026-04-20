package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Travel;
import com.example.public_transportation_project1.Repository.LineRepository;
import com.example.public_transportation_project1.Repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime; // שינוי ל-LocalDateTime כדי להתאים למודל
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private LineRepository lineRepository;
    @Override
    public Travel saveTravel(Travel travel) {
        return travelRepository.save(travel);
    }

    @Override
    public Optional<Travel> getTravelById(Long id) {
        return travelRepository.findById(id);
    }

    @Override
    public List<Travel> getAllTravels() {
        return travelRepository.findAll();
    }
    
    @Override
    public void deleteTravel(Long id) {
        travelRepository.deleteById(id);
    }

  @Override
public String getEstimatedArrivalTime(Long travelId, int stationOrder) {
    return travelRepository.findById(travelId).map(travel -> {
        // תיקון: תחנה מס' 1 היא זמן היציאה המקורי (1-1=0 דקות תוספת)
        LocalDateTime arrival = travel.getDepartureTime().plusMinutes(stationOrder - 1);
        return "האוטובוס צפוי להגיע לתחנה בשעה: " + arrival.toLocalTime();
    }).orElse("נסיעה לא נמצאה במערכת");
}

    @Override
    public Travel updateTravel(Long id, Travel travel) {
        if (travelRepository.existsById(id)) {
            travel.setId(id);
            return travelRepository.save(travel);
        }
        return null;
    }
    
    @Override
    public boolean isDriverAvailable(Long driverId, LocalDateTime departureTime) {
        // שימוש בפונקציה קיימת ברפוזיטורי לבדיקת התנגשויות נהג
        List<Travel> conflicts = travelRepository.findDriverConflicts(driverId, departureTime);
        return conflicts.isEmpty(); // true אם אין התנגשויות
    }
    
    @Override
    public boolean isBusAvailable(Long busId, LocalDateTime departureTime) {
        // שימוש בפונקציה קיימת ברפוזיטורי לבדיקת התנגשויות אוטובוס
        List<Travel> conflicts = travelRepository.findBusConflicts(busId, departureTime);
        return conflicts.isEmpty(); // true אם אין התנגשויות
    }
    
    @Override
    public Travel saveTravelWithValidation(Travel travel) {
        // שלב א': בדיקת זמינות נהג
        if (!isDriverAvailable(travel.getDriver().getId(), travel.getDepartureTime())) {
            throw new IllegalArgumentException("הנהג לא זמין בזמן המבוקש - יש לו נסיעה אחרת באותה שעה");
        }
        
        // שלב ב': בדיקת זמינות אוטובוס
        if (!isBusAvailable(travel.getBus().getId(), travel.getDepartureTime())) {
            throw new IllegalArgumentException("האוטובוס לא זמין בזמן המבוקש - הוא משובץ לנסיעה אחרת באותה שעה");
        }
        
        // שלב ג': אם כל הבדיקות עברו - שמור את הנסיעה
        return travelRepository.save(travel);
    }
    @Override
public List<Travel> getTravelsByDay(String lineNumber, int year, int month, int day) {
    // בניית טווח הזמן של היום המבוקש
    java.time.LocalDateTime startOfDay = java.time.LocalDateTime.of(year, month, day, 0, 0, 0);
    java.time.LocalDateTime endOfDay = java.time.LocalDateTime.of(year, month, day, 23, 59, 59);

    return lineRepository.findAllByNumber(lineNumber).stream()
            .flatMap(line -> travelRepository.findByLineIdAndDepartureTimeBetweenOrderByDepartureTime(
                    line.getId(), startOfDay, endOfDay).stream())
            .collect(Collectors.toList());
}

@Override
public List<Travel> getTravelsByHour(String lineNumber, int year, int month, int day, int hour) {
    // בניית טווח השעה המבוקשת
    java.time.LocalDateTime startOfHour = java.time.LocalDateTime.of(year, month, day, hour, 0, 0);
    java.time.LocalDateTime endOfHour = java.time.LocalDateTime.of(year, month, day, hour, 59, 59);

    return lineRepository.findAllByNumber(lineNumber).stream()
            .flatMap(line -> travelRepository.findByLineIdAndDepartureTimeBetweenOrderByDepartureTime(
                    line.getId(), startOfHour, endOfHour).stream())
            .collect(Collectors.toList());
}

@Override
public java.util.Optional<Travel> getLastTravelOfDay(String lineNumber, int year, int month, int day) {
    // שימוש בפונקציה שכבר כתבנו כדי למצוא את כל נסיעות היום ואז לקחת את האחרונה
    List<Travel> dayTravels = getTravelsByDay(lineNumber, year, month, day);
    return dayTravels.stream()
            .max(java.util.Comparator.comparing(Travel::getDepartureTime));
}
public List<String> getAllTravelsFormatted() {
    return travelRepository.findAll().stream()
        .map(t -> t.getDepartureTime().toLocalTime() + " | קו " + t.getLine().getNumber() + " ל" + t.getLine().getDestination())
        .collect(Collectors.toList());
    }
}