package com.example.public_transportation_project1.Repository;

import com.example.public_transportation_project1.Models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Long> {
    
    // מציאת כל הנסיעות של קו מסוים
    @Query("SELECT t FROM Travel t WHERE t.line.id = ?1 ORDER BY t.departureTime")
    List<Travel> findByLineIdOrderByDepartureTime(Long lineId);
    
    // בדיקת זמינות נהג בזמן מסוים
    @Query("SELECT t FROM Travel t WHERE t.driver.id = ?1 AND t.departureTime = ?2")
    List<Travel> findDriverConflicts(Long driverId, java.time.LocalDateTime departureTime);
    
    // בדיקת זמינות אוטובוס בזמן מסוים
    @Query("SELECT t FROM Travel t WHERE t.bus.id = ?1 AND t.departureTime = ?2")
    List<Travel> findBusConflicts(Long busId, java.time.LocalDateTime departureTime);
    // מציאת נסיעות לפי קו בטווח זמנים
    List<Travel> findByLineIdAndDepartureTimeBetweenOrderByDepartureTime(Long lineId, LocalDateTime start, LocalDateTime end);
}
