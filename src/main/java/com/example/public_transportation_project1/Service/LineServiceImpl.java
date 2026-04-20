package com.example.public_transportation_project1.Service;

import com.example.public_transportation_project1.Models.Line;
import com.example.public_transportation_project1.Repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineRepository lineRepository;

    @Override public Line saveLine(Line line) { return lineRepository.save(line); }

    @Override public Optional<Line> getLineById(Long id) { return lineRepository.findById(id); }

    @Override
    public List<Line> getAllLines() { return lineRepository.findAll(); }

    @Override
    public void deleteLine(Long id) { lineRepository.deleteById(id); }

    @Override
    public Line updateLine(Long id, Line line) {
        if (lineRepository.existsById(id)) {
            line.setId(id);
            return lineRepository.save(line);
        }
        return null;
    }

    // מימוש החיפוש לפי מספר שדרוש ב-Controller
    @Override
    public Optional<Line> getLineByNumber(String number) {
        return lineRepository.findByNumber(number);
    }
}