package com.example.public_transportation_project1.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(unique = true, nullable = false) // הגנה: טלפון נהג חייב להיות ייחודי
    private String phone; 
    
    private Integer rating;
}