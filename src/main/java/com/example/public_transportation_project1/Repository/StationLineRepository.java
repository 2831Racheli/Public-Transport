package com.example.public_transportation_project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.public_transportation_project1.Models.StationLine;
import com.example.public_transportation_project1.Models.StationLineId;
import java.util.List;

@Repository
public interface StationLineRepository extends JpaRepository<StationLine, StationLineId> {
    
    // תיקון: גישה ישירה ל-lineId ושימוש בשם המתודה הנכון
    @Query("SELECT sl FROM StationLine sl WHERE sl.lineId = ?1 ORDER BY sl.stationOrder")
    List<StationLine> findByLineIdOrderByStationOrder(Long lineId);
    
    // תיקון: השוואה ל-stationId
    @Query("SELECT sl FROM StationLine sl WHERE sl.stationId = ?1")
    List<StationLine> findByStationId(Long stationId);
    
    // מציאת סדר התחנה במסלול הקו
    @Query("SELECT sl.stationOrder FROM StationLine sl WHERE sl.lineId = ?1 AND sl.stationId = ?2")
    Integer findStationOrder(Long lineId, Long stationId);
}