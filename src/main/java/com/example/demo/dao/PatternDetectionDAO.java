package com.example.demo.dao;

import com.example.demo.dto.PatternDetection;

import java.time.LocalDateTime;
import java.util.List;

public interface PatternDetectionDAO {
    List<PatternDetection> getAllPatternDetections();

    PatternDetection getPatternDetectionById(Long detectionNumber);

    void addPatternDetection(PatternDetection patternDetection);

    void updatePatternDetection(PatternDetection patternDetection);

    void deletePatternDetection(Long detectionNumber);

    // 이름으로 패턴 탐지 정보를 가져오는 메서드 추가
    List<PatternDetection> getPatternDetectionsByName(String name);

    // 페이징 처리를 위한 메서드 추가 예시
    List<PatternDetection> getAllPatternDetectionsPaged(int page, int size);

    // 특정 시간 범위 내의 패턴 탐지 정보를 가져오는 메서드 추가
    List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end);
}
