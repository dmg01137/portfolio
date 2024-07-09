package com.example.demo.service;

import com.example.demo.dao.PatternDetectionDAO;
import com.example.demo.dto.PatternDetection;
import com.example.demo.controller.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        // Implement search logic based on searchCriteria
        // Example: Searching by name
        if (searchCriteria.getSearchInputName() != null && !searchCriteria.getSearchInputName().isEmpty()) {
            return patternDetectionDAO.getPatternDetectionsByName(searchCriteria.getSearchInputName());
        }
        // Add more conditions based on other fields in SearchCriteria

        // Default return if no specific criteria match
        return patternDetectionDAO.getAllPatternDetections();
    }

    public PatternDetection getPatternDetectionById(Long detectionNumber) {
        return patternDetectionDAO.getPatternDetectionById(detectionNumber);
    }

    public void addPatternDetection(PatternDetection patternDetection) {
        patternDetectionDAO.addPatternDetection(patternDetection);
    }

    public void updatePatternDetection(PatternDetection patternDetection) {
        patternDetectionDAO.updatePatternDetection(patternDetection);
    }

    public void deletePatternDetection(Long detectionNumber) {
        patternDetectionDAO.deletePatternDetection(detectionNumber);
    }

    public List<PatternDetection> getAllPatternDetectionsPaged(int page, int size) {
        return patternDetectionDAO.getAllPatternDetectionsPaged(page, size);
    }

    public List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return patternDetectionDAO.getPatternDetectionsByTimeRange(start, end);
    }
}
