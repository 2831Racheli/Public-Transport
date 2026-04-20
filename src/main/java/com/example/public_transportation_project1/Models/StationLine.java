package com.example.public_transportation_project1.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(StationLineId.class) // מציין שהמפתח מורכב מהמחלקה שיצרנו קודם 
public class StationLine {

    @Id
    private Long lineId;    // קוד קו (FK) 

    @Id
    private Long stationId; // קוד תחנה (FK) 

    private Integer stationOrder; // מספר התחנה במסלול 
}