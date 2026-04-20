package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.*;
import com.example.public_transportation_project1.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private StationLineRepository stationLineRepository;
    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<LocalTime> getArrivalTimesByLine(Long stationId, String lineNumber) {
        return lineRepository.findByNumber(lineNumber)
                .map(line -> {
                    Integer order = stationLineRepository.findStationOrder(line.getId(), stationId);
                    if (order == null) return List.<LocalTime>of();
                    return travelRepository.findByLineIdOrderByDepartureTime(line.getId()).stream()
                            .map(t -> t.getDepartureTime().toLocalTime().plusMinutes(order - 1))
                            .collect(Collectors.toList());
                }).orElse(List.of());
    }

    @Override
    public List<String> getLinesAtStation(Long stationId) {
        return lineRepository.findLinesByStationId(stationId).stream()
                .map(l -> l.getNumber() + " לכיוון " + l.getDestination())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> planRoute(Long sourceId, Long destId) {
        List<StationLine> sources = stationLineRepository.findByStationId(sourceId);
        List<StationLine> dests = stationLineRepository.findByStationId(destId);
        
        return sources.stream()
                .map(StationLine::getLineId)
                .filter(id -> dests.stream().anyMatch(d -> d.getLineId().equals(id)))
                .map(id -> {
                    Integer sOrd = stationLineRepository.findStationOrder(id, sourceId);
                    Integer dOrd = stationLineRepository.findStationOrder(id, destId);
                    if (sOrd != null && dOrd != null && sOrd < dOrd) {
                        return lineRepository.findById(id).map(l -> "קו " + l.getNumber() + " מ" + l.getSource() + " ל" + l.getDestination()).orElse("");
                    }
                    return "";
                })
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getStationsByLineNumber(String lineNumber) {
        return lineRepository.findAllByNumber(lineNumber).stream()
                .flatMap(line -> stationLineRepository.findByLineIdOrderByStationOrder(line.getId()).stream()
                        .map(sl -> stationRepository.findById(sl.getStationId())
                                .map(s -> "קו " + line.getNumber() + " ל" + line.getDestination() + ": " + sl.getStationOrder() + ". " + s.getName())
                                .orElse("תחנה לא ידועה")))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTravelsByLineNumber(String lineNumber) {
        return lineRepository.findAllByNumber(lineNumber).stream()
                .flatMap(line -> travelRepository.findByLineIdOrderByDepartureTime(line.getId()).stream()
                        .map(t -> t.getDepartureTime().toLocalTime() + " | " + line.getSource() + " -> " + line.getDestination()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTravelsByDay(String lineNumber, int month, int day) {
        int year = LocalDate.now().getYear();
        LocalDateTime start = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime end = LocalDateTime.of(year, month, day, 23, 59);

        return lineRepository.findAllByNumber(lineNumber).stream()
                .flatMap(line -> travelRepository.findByLineIdAndDepartureTimeBetweenOrderByDepartureTime(line.getId(), start, end).stream()
                        .map(t -> t.getDepartureTime().toLocalTime() + " | " + line.getSource() + " -> " + line.getDestination()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTravelsByHour(String lineNumber, int hour) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atTime(hour, 0);
        LocalDateTime end = today.atTime(hour, 59);

        return lineRepository.findAllByNumber(lineNumber).stream()
                .flatMap(line -> travelRepository.findByLineIdAndDepartureTimeBetweenOrderByDepartureTime(line.getId(), start, end).stream()
                        .map(t -> t.getDepartureTime().toLocalTime() + " | " + line.getDestination()))
                .collect(Collectors.toList());
    }

    @Override
    public String getLastTravelOfDay(String lineNumber) {
        LocalDate today = LocalDate.now();
        List<String> travels = getTravelsByDay(lineNumber, today.getMonthValue(), today.getDayOfMonth());
        return travels.isEmpty() ? "אין נסיעות מתוכננות להיום לקו זה" : travels.get(travels.size() - 1);
    }
    @Override
public String getNextArrivalDynamic(Long stationId, String lineNumber) {
    java.time.LocalDateTime now = java.time.LocalDateTime.now(); // השעה עכשיו
    
    // שליפת כל זמני ההגעה לתחנה עבור הקו הזה
    List<java.time.LocalTime> arrivalTimes = getArrivalTimesByLine(stationId, lineNumber);
    
    // מציאת זמן ההגעה הקרוב ביותר שעדיין לא עבר
    return arrivalTimes.stream()
            .filter(time -> time.isAfter(now.toLocalTime()))
            .findFirst()
            .map(time -> {
                long minutesLeft = java.time.Duration.between(now.toLocalTime(), time).toMinutes();
                return "קו " + lineNumber + " יגיע לתחנה בעוד " + minutesLeft + " דקות (בשעה " + time + ")";
            })
            .orElse("אין נסיעות קרובות לקו זה היום");
}
}