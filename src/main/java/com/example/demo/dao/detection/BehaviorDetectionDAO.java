package com.example.demo.dao.detection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.detection.BehaviorDetection;

public interface BehaviorDetectionDAO {
    
    List<BehaviorDetection> getAllBehaviorDetections();

    List<BehaviorDetection> getBehaviorDetectionsByName(String name);

    void addBehaviorDetection(BehaviorDetection behaviorDetection);

    void updateBehaviorDetection(BehaviorDetection behaviorDetection);

    void deleteBehaviorDetection(BehaviorDetection behaviorDetection);

    // 검색 조건에 따른 BehaviorDetection을 조회하는 메소드
    List<BehaviorDetection> searchBehaviorDetections(BehaviorDetection behaviorDetection);

    List<BehaviorDetection> getAllBehaviorDetectionsPaged(Map<String, Integer> params);

    List<BehaviorDetection> getBehaviorDetectionsByTimeRange(Map<String, LocalDateTime> params);
}
