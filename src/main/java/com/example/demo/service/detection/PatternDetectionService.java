package com.example.demo.service.detection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.log.SearchCriteria;
import com.example.demo.dao.detection.PatternDetectionDAO;
import com.example.demo.dto.detection.PatternDetection;

@Service
public class PatternDetectionService {

    private final PatternDetectionDAO patternDetectionDAO;

    @Autowired
    public PatternDetectionService(PatternDetectionDAO patternDetectionDAO) {
        this.patternDetectionDAO = patternDetectionDAO;
    }

    public List<PatternDetection> getAllPatternDetections() {
        return patternDetectionDAO.getAllPatternDetections();
    }

    public List<PatternDetection> searchPatternDetections(SearchCriteria searchCriteria) {
        if (searchCriteria.getSearchInputName() != null && !searchCriteria.getSearchInputName().isEmpty()) {
            return patternDetectionDAO.getPatternDetectionByName(searchCriteria.getSearchInputName());
        }
        return patternDetectionDAO.getAllPatternDetections();
    }

    public List<PatternDetection> getPatternDetectionsByName(String name) {
        return patternDetectionDAO.getPatternDetectionByName(name);
    }

    public void addPatternDetection(PatternDetection patternDetection) {
        patternDetectionDAO.addPatternDetection(patternDetection);
    }

    public void updatePatternDetection(PatternDetection patternDetection) {
        patternDetectionDAO.updatePatternDetection(patternDetection);
    }

    public void deletePatternDetection(Long detection_number) {
        patternDetectionDAO.deletePatternDetection(detection_number);
    }

    public List<PatternDetection> getAllPatternDetectionsPaged(int page, int size) {
        return patternDetectionDAO.getAllPatternDetectionsPaged(page, size);
    }

    public List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return patternDetectionDAO.getPatternDetectionsByTimeRange(start, end);
    }
}
