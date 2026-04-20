package com.example.public_transportation_project1.Models;

import com.fasterxml.jackson.annotation.JsonFormat; // יבוא חדש
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
    
    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
    
    @ManyToOne
    @JoinColumn(name = "line_id", nullable = false)
    private Line line;
    
    // כאן מוסיפים את האנוטציה שתפתור את שגיאת ה-400
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;
}