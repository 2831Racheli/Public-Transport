package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Line;
import java.util.List;
import java.util.Optional;

public interface LineService {
    Line saveLine(Line line); // שמירת קו חדש
    Optional<Line> getLineById(Long id); // מציאת קו לפי קוד
    List<Line> getAllLines(); // רשימת כל הקווים
    void deleteLine(Long id); // מחיקת קו
    Line updateLine(Long id, Line line); // עדכון פרטי קו
    // הוסיפי את החתימה הזו לממשק
    Optional<Line> getLineByNumber(String number);
}