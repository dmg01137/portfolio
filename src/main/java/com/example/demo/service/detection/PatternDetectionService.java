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
        // 모든 검색 조건이 null 또는 빈 값인 경우 모든 패턴 탐지를 조회
        if (isEmptyCriteria(searchCriteria)) {
            return patternDetectionDAO.getAllPatternDetections();
        }

        // 검색 조건에 따라 적절한 DAO 메서드 호출
        return patternDetectionDAO.searchPatternDetections(searchCriteria);
    }// 모든 검색 조건이 null 또는 빈 값인지 확인하는 유틸리티 메서드
    private boolean isEmptyCriteria(SearchCriteria searchCriteria) {
        return searchCriteria.getDetection_number() == null &&
               searchCriteria.getS_to_ip() == null &&
               searchCriteria.getS_ip() == null &&
               searchCriteria.getD_ip() == null &&
               searchCriteria.getS_port() == null &&
               searchCriteria.getD_port() == null &&
               searchCriteria.getCreate_dt() == null &&
               searchCriteria.getModify_dt() == null &&
               searchCriteria.getAction_type() == null &&
               searchCriteria.getPolicy_name() == null &&
               searchCriteria.getPolicy_info() == null &&
               searchCriteria.getPattern_1() == null &&
               searchCriteria.getPattern_2() == null &&
               searchCriteria.getPattern_3() == null &&
               searchCriteria.getDangerous() == null;
    }

    public List<PatternDetection> getPatternDetectionsByName(String name) {
        return patternDetectionDAO.getPatternDetectionByName(name);
    }

    //추가 
    public void addPatternDetection(PatternDetection patternDetection) {
        patternDetection.setCreate_dt(LocalDateTime.now()); // 현재 시간으로 생성일시 설정
        patternDetection.setModify_dt(LocalDateTime.now()); // 현재 시간으로 수정일시 설정
        patternDetectionDAO.addPatternDetection(patternDetection); // 패턴 추가 DAO 호출
    }

    public void updatePatternDetection(PatternDetection patternDetection) {
        patternDetection.setModify_dt(LocalDateTime.now()); // 패턴 수정 시 수정일시 갱신
        patternDetectionDAO.updatePatternDetection(patternDetection); // 패턴 업데이트 DAO 호출
    }

    public void deletePatternDetection(PatternDetection patternDetection) {
        patternDetectionDAO.deletePatternDetection(patternDetection);
    }

    public List<PatternDetection> getAllPatternDetectionsPaged(int page, int size) {
        return patternDetectionDAO.getAllPatternDetectionsPaged(page, size);
    }

    public List<PatternDetection> getPatternDetectionsByTimeRange(LocalDateTime start, LocalDateTime end) {
        return patternDetectionDAO.getPatternDetectionsByTimeRange(start, end);
    }
    
  
}
