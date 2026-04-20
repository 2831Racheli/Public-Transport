package com.example.public_transportation_project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.public_transportation_project1.Models.Line;
import java.util.List;
import java.util.Optional;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    
    // מציאת קו בודד לפי מספר (עבור ניהול פנימי)
    Optional<Line> findByNumber(String lineNumber);

    // מציאת כל הכיוונים של הקו (עבור הנוסע - למשל 402 לשני הכיוונים)
    List<Line> findAllByNumber(String number);

    // שליפת כל הקווים שעוברים בתחנה בשאילתה אחת (מונע בעיית N+1)
    @Query("SELECT l FROM Line l JOIN StationLine sl ON l.id = sl.lineId WHERE sl.stationId = :stationId")
    List<Line> findLinesByStationId(@Param("stationId") Long stationId);
}