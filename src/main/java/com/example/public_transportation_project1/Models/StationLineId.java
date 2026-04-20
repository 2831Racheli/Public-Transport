package com.example.public_transportation_project1.Models;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Serializable הכרחי עבור מפתחות מורכבים ב-JPA
public class StationLineId implements Serializable {
    private Long lineId;    // תואם לשדה בטבלת העזר 
    private Long stationId; // תואם לשדה בטבלת העזר 
}