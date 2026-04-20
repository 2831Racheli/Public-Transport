package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/arrival-times")
    public List<LocalTime> getArrivalTimes(@RequestParam Long stationId, @RequestParam String lineNumber) {
        return passengerService.getArrivalTimesByLine(stationId, lineNumber);
    }

    @GetMapping("/lines-at-station/{stationId}")
    public List<String> getLinesAtStation(@PathVariable Long stationId) {
        return passengerService.getLinesAtStation(stationId);
    }

    @GetMapping("/plan-route")
    public List<String> planRoute(@RequestParam Long sourceId, @RequestParam Long destId) {
        return passengerService.planRoute(sourceId, destId);
    }

    @GetMapping("/line-stations/{lineNumber}")
    public List<String> getStationsByLineNumber(@PathVariable String lineNumber) {
        return passengerService.getStationsByLineNumber(lineNumber);
    }

    @GetMapping("/line-travels/{lineNumber}")
    public List<String> getTravelsByLineNumber(@PathVariable String lineNumber) {
        return passengerService.getTravelsByLineNumber(lineNumber);
    }

    @GetMapping("/travels-day/{number}")
    public List<String> getDayTravels(@PathVariable String number, @RequestParam int m, @RequestParam int d) {
        return passengerService.getTravelsByDay(number, m, d);
    }

    @GetMapping("/travels-hour/{number}/{hour}")
    public List<String> getHourTravels(@PathVariable String number, @PathVariable int hour) {
        return passengerService.getTravelsByHour(number, hour);
    }

    @GetMapping("/travels-last/{number}")
    public String getLastTravel(@PathVariable String number) {
        return passengerService.getLastTravelOfDay(number);
    }
    @GetMapping("/next-arrival-live")
    public String getNextArrivalLive(@RequestParam Long stationId, @RequestParam String lineNumber) {
         return passengerService.getNextArrivalDynamic(stationId, lineNumber);
     }
}