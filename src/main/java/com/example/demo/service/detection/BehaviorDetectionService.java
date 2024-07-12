package com.example.demo.service.detection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.detection.BehaviorDetectionDAO;
import com.example.demo.dto.detection.BehaviorDetection;

@Service
public class BehaviorDetectionService {

    private final BehaviorDetectionDAO behaviorDetectionDAO;

    @Autowired
    public BehaviorDetectionService(BehaviorDetectionDAO behaviorDetectionDAO) {
        this.behaviorDetectionDAO = behaviorDetectionDAO;
    }

    public List<BehaviorDetection> getAllBehaviorDetections() {
        return behaviorDetectionDAO.getAllBehaviorDetections();
    }

    public List<BehaviorDetection> searchBehaviorDetections(BehaviorDetection searchCriteria) {
        // 검색 기준을 통해 데이터 조회
        return behaviorDetectionDAO.searchBehaviorDetections(searchCriteria);
    }

    public List<BehaviorDetection> getBehaviorDetectionsByName(String name) {
        return behaviorDetectionDAO.getBehaviorDetectionsByName(name);
    }

    public void addBehaviorDetection(BehaviorDetection behaviorDetection) {
        behaviorDetectionDAO.addBehaviorDetection(behaviorDetection);
    }

    public void updateBehaviorDetection(BehaviorDetection behaviorDetection) {
        behaviorDetectionDAO.updateBehaviorDetection(behaviorDetection);
    }

    public void deleteBehaviorDetection(Long detectionNumber) {
        behaviorDetectionDAO.deleteBehaviorDetection(detectionNumber);
    }

    public List<BehaviorDetection> getAllBehaviorDetectionsPaged(int page, int size) {
        return behaviorDetectionDAO.getAllBehaviorDetectionsPaged(page, size);
    }

    public List<BehaviorDetection> getBehaviorDetectionsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return behaviorDetectionDAO.getBehaviorDetectionsByTimeRange(start, end);
    }
}
