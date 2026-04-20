package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.StationLine;
import java.util.List;

public interface StationLineService {
    // הוספת תחנה למסלול של קו מסוים (לוגיקת מנהל)
    StationLine addStationToLine(Long lineId, Long stationId); 
    // הצגת כל המסלול (התחנות) של קו מסוים לפי הסדר
    List<StationLine> getLineRoute(Long lineId);
    // הסרת תחנה ממסלול קו (לוגיקת מנהל)
    // מוחקת את התחנה ומעדכנת את סדר התחנות הבאות כדי למנוע "חורים"
    String removeStationFromLine(Long lineId, Long stationId);
}