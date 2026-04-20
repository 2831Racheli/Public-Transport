package com.example.public_transportation_project1.Controller;

import com.example.public_transportation_project1.Models.Line;
import com.example.public_transportation_project1.Service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lines") // כתובת הבסיס: http://localhost:8081/api/lines
public class LineController {

    @Autowired
    private LineService lineService; // הזרקת השירות לניהול קווים

    @GetMapping("/all") // שליפת כל הקווים הקיימים במערכת
    public List<Line> getAllLines() {
        return lineService.getAllLines();
    }

    @GetMapping("/{id}") // מציאת קו ספציפי לפי ה-ID שלו
    public Optional<Line> getLineById(@PathVariable Long id) {
        return lineService.getLineById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add") // יצירת קו חדש (למשל קו 400)
    public Line addLine(@RequestBody Line line) {
        return lineService.saveLine(line);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}") // עדכון פרטי קו קיים
    public Line updateLine(@PathVariable Long id, @RequestBody Line line) {
        return lineService.updateLine(id, line);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}") // מחיקת קו מהמערכת
    public void deleteLine(@PathVariable Long id) {
        lineService.deleteLine(id);
    }
    @GetMapping("/number/{num}")
     public Optional<Line> getLineByNumber(@PathVariable String num) {
         return lineService.getLineByNumber(num);
     }
}