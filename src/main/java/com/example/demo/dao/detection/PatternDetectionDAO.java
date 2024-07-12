package com.example.demo.dao.detection;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.detection.PatternDetection;

public interface PatternDetectionDAO {
    List<PatternDetection> getAllPatternDetections();

    List<PatternDetection> getPatternDetectionByName(String name);

    void addPatternDetection(PatternDetection patternDetection);
    

    void updatePatternDetection(PatternDetection patternDetection);

    void deletePatternDetection(Long detection_number);

    List<PatternDetection> searchPatternDetections(String searchInputName);

    List<PatternDetection> getAllPatternDetectionsPaged(int page, int size);

    List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end);
}
