package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.StationLine;
import com.example.public_transportation_project1.Repository.StationLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationLineServiceImpl implements StationLineService {

    @Autowired
    private StationLineRepository stationLineRepository;

    @Override
    public StationLine addStationToLine(Long lineId, Long stationId) {
        // שלב 0: בדיקת ולידציה - האם התחנה כבר קיימת בקו
        List<StationLine> existingStations = stationLineRepository.findByLineIdOrderByStationOrder(lineId);
        boolean stationAlreadyExists = existingStations.stream()
                .anyMatch(sl -> sl.getStationId().equals(stationId));
                
        if (stationAlreadyExists) {
            throw new IllegalArgumentException("התחנה כבר קיימת במסלול הקו");
        }
        
        // שלב א': בדיקה כמה תחנות כבר יש בקו הזה כדי לקבוע מה המספר הבא
        // שימוש בפונקציה קיימת במקום findAll()
        int currentCount = existingStations.size();

        // שלב ב': יצירת הקשר החדש עם סדר אוטומטי (למשל: אם היו 3 תחנות, זו תהיה ה-4)
        StationLine sl = new StationLine();
        sl.setLineId(lineId);
        sl.setStationId(stationId);
        sl.setStationOrder(currentCount + 1); 
        
        return stationLineRepository.save(sl);
    }
    @Override
    public List<StationLine> getLineRoute(Long lineId) {
    // שימוש ברפוזיטורי במקום לסנן את כל הטבלה בזיכרון
       return stationLineRepository.findByLineIdOrderByStationOrder(lineId);
   }
   
    @Override
    public String removeStationFromLine(Long lineId, Long stationId) {
        // שלב א': בדיקה שהתחנה אכן קיימת בקו - שימוש בפונקציה קיימת
        List<StationLine> lineStations = stationLineRepository.findByLineIdOrderByStationOrder(lineId);
        StationLine stationToRemove = lineStations.stream()
                .filter(sl -> sl.getStationId().equals(stationId))
                .findFirst()
                .orElse(null);
                
        if (stationToRemove == null) {
            return "התחנה לא נמצאה במסלול הקו";
        }
        
        // שלב ב': שמירת סדר התחנה שנמחקה לעדכון התחנות הבאות
        Integer removedOrder = stationToRemove.getStationOrder();
        
        // שלב ג': מחיקת התחנה מהקו
        stationLineRepository.delete(stationToRemove);
        
        // שלב ד': עדכון סדר כל התחנות שאחרי התחנה שנמחקה
        // משתמשים שוב בפונקציה קיימת ומסננים רק את התחנות הבאות
        List<StationLine> stationsToUpdate = lineStations.stream()
                .filter(sl -> sl.getStationOrder() > removedOrder)
                .collect(Collectors.toList());
                
        for (StationLine station : stationsToUpdate) {
            station.setStationOrder(station.getStationOrder() - 1);
            stationLineRepository.save(station);
        }
        
        return "התחנה הוסרה בהצלחה מהמסלול וסדר התחנות עודכן";
    }

}