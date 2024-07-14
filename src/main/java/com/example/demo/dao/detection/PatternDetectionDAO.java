package com.example.demo.dao.detection;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.detection.PatternDetection;

public interface PatternDetectionDAO {
    List<PatternDetection> getAllPatternDetections();

    List<PatternDetection> getPatternDetectionByName(String name);

    PatternDetection getPatternDetectionById(Long detection_number); // 패턴 번호를 기반으로 패턴 객체를 조회하는 메서드

    void addPatternDetection(PatternDetection patternDetection);

    void updatePatternDetection(PatternDetection patternDetection); // 패턴 객체를 업데이트하는 메서드

    void deletePatternDetection(PatternDetection patternDetection);

    List<PatternDetection> searchPatternDetections(String searchInputName);

    List<PatternDetection> getAllPatternDetectionsPaged(int page, int size);

    List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end);

	
}
