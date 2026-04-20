package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Driver;
import com.example.public_transportation_project1.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Driver updateDriver(Long id, Driver driver) {
        if (driverRepository.existsById(id)) {
            driver.setId(id);
            return driverRepository.save(driver);
        }
        return null;
    }
}
