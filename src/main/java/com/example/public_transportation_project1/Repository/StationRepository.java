package com.example.public_transportation_project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.public_transportation_project1.Models.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}