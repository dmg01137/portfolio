package com.example.demo.dao;

import com.example.demo.dto.PatternDetection;

import java.time.LocalDateTime;
import java.util.List;

public interface PatternDetectionDAO {
    List<PatternDetection> getAllPatternDetections();

    List<PatternDetection> getPatternDetectionByName(String name);

    void addPatternDetection(PatternDetection patternDetection);

    void updatePatternDetection(PatternDetection patternDetection);

    void deletePatternDetection(Long detectionNumber);

    List<PatternDetection> searchPatternDetections(String searchInputName);

    List<PatternDetection> getAllPatternDetectionsPaged(int page, int size);

    List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end);
}
