package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Station;
import com.example.public_transportation_project1.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override public Station saveStation(Station station) { return stationRepository.save(station); }
    @Override public Optional<Station> getStationById(Long id) { return stationRepository.findById(id); }
    @Override public List<Station> getAllStations() { return stationRepository.findAll(); }
    @Override public void deleteStation(Long id) { stationRepository.deleteById(id); }
}