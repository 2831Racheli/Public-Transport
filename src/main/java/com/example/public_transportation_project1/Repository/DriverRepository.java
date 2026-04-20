
package com.example.public_transportation_project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// זה האימפורט היחיד שצריך עבור ה-Driver שלך
import com.example.public_transportation_project1.Models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    
}