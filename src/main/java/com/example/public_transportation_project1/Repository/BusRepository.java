package com.example.public_transportation_project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation_project1.Models.Bus;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{
    // חיפוש ישיר במסד הנתונים לפי לוחית רישוי
    Optional<Bus> findByLicensePlate(String licensePlate);

    // חיפוש ישיר של אוטובוסים עם לפחות X מושבים
    List<Bus> findBySeatsGreaterThanEqual(int seats);
}