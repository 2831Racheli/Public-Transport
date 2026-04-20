package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Bus;
import com.example.public_transportation_project1.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override public Bus saveBus(Bus bus) { return busRepository.save(bus); }

    @Override
    public Bus addBus(String licensePlate, int seats) {
        Bus bus = new Bus();
        bus.setLicensePlate(licensePlate);
        bus.setSeats(seats);
        return busRepository.save(bus);
    }

    @Override public Optional<Bus> getBusById(Long id) { return busRepository.findById(id); }

    @Override
    public Optional<Bus> getBusByLicensePlate(String licensePlate) {
        // יעיל יותר: חיפוש ישיר ב-DB במקום stream.filter
        return busRepository.findByLicensePlate(licensePlate);
    }

    @Override public List<Bus> getAllBuses() { return busRepository.findAll(); }

    @Override
    public List<Bus> getBusesBySeats(int minSeats) {
        // יעיל יותר: סינון ברמת ה-SQL
        return busRepository.findBySeatsGreaterThanEqual(minSeats);
    }

    @Override public void deleteBus(Long id) { busRepository.deleteById(id); }

    @Override
    public Bus updateBus(Long id, Bus bus) {
        if (busRepository.existsById(id)) {
            bus.setId(id);
            return busRepository.save(bus);
        }
        return null;
    }
}