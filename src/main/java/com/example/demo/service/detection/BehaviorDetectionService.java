package com.example.demo.service.detection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    public List<BehaviorDetection> searchBehaviorDetections(BehaviorDetection behaviorDetection) {
        return behaviorDetectionDAO.searchBehaviorDetections(behaviorDetection);
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

    public void deleteBehaviorDetection(BehaviorDetection behaviorDetection) {
        behaviorDetectionDAO.deleteBehaviorDetection(behaviorDetection);
    }

    public List<BehaviorDetection> getAllBehaviorDetectionsPaged(Map<String, Integer> params) {
        return behaviorDetectionDAO.getAllBehaviorDetectionsPaged(params);
    }

    public List<BehaviorDetection> getBehaviorDetectionsByTimeRange(Map<String, LocalDateTime> params) {
        return behaviorDetectionDAO.getBehaviorDetectionsByTimeRange(params);
    }
}
