package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.StationLine;
import com.example.public_transportation_project1.Service.StationLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/line-stations")
public class StationLineController {
    //מסלולי קווים
    @Autowired
    private StationLineService stationLineService; //

    // הוספת תחנה למסלול של קו מסוים - הסדר נקבע אוטומטית ב-Service
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-station")
    public StationLine addStationToLine(@RequestParam Long lineId, @RequestParam Long stationId) {
        return stationLineService.addStationToLine(lineId, stationId);
    }

    // קבלת המסלול המלא של קו מסוים לפי הסדר
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/route/{lineId}")
    public List<StationLine> getLineRoute(@PathVariable Long lineId) {
        return stationLineService.getLineRoute(lineId);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/remove-station")
    public String removeStationFromLine(@RequestParam Long lineId, @RequestParam Long stationId) {
        return stationLineService.removeStationFromLine(lineId, stationId);
    }
}