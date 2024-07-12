package com.example.demo.dao.detection;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.dto.detection.BehaviorDetection;

public interface BehaviorDetectionDAO {
    
    // 모든 BehaviorDetection을 조회
    List<BehaviorDetection> getAllBehaviorDetections();

    // 이름으로 BehaviorDetection을 조회
    List<BehaviorDetection> getBehaviorDetectionsByName(String name);

    // 새로운 BehaviorDetection 추가
    void addBehaviorDetection(BehaviorDetection behaviorDetection);

    // BehaviorDetection 수정
    void updateBehaviorDetection(BehaviorDetection behaviorDetection);

    // BehaviorDetection 삭제
    void deleteBehaviorDetection(Long detection_number);

    // 검색 조건에 맞는 BehaviorDetection을 조회
    List<BehaviorDetection> searchBehaviorDetections(BehaviorDetection searchCriteria);

    // 페이지에 따른 BehaviorDetection을 조회
    List<BehaviorDetection> getAllBehaviorDetectionsPaged(int page, int size);

    // 시간 범위에 따른 BehaviorDetection을 조회
    List<BehaviorDetection> getBehaviorDetectionsByTimeRange(LocalDateTime start, LocalDateTime end);
}
