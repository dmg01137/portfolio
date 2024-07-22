package com.example.demo.service.log;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.log.BehaviorLogDAO;
import com.example.demo.dto.log.BehaviorLog;

@Service
@Transactional
public class BehaviorLogService {

    @Autowired
    private BehaviorLogDAO behaviorLogDAO;

    // ID를 기반으로 특정 행동 로그를 조회합니다.
    public BehaviorLog findById(int id) {
        return behaviorLogDAO.findById(id);
    }

    // 모든 행동 로그 리스트를 조회합니다.
    public List<BehaviorLog> findAll() {
        return behaviorLogDAO.findAll();
    }

    // 새로운 행동 로그를 저장합니다.
    public void save(BehaviorLog behaviorLog) {
        behaviorLogDAO.save(behaviorLog);
    }

    // 기존 행동 로그를 업데이트합니다.
    public void update(BehaviorLog behaviorLog) {
        behaviorLogDAO.update(behaviorLog);
    }

    // ID를 기반으로 특정 행동 로그를 삭제합니다.
    public void delete(int id) {
        behaviorLogDAO.delete(id);
    }

    // Top 5 s_ip 값 조회
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        return behaviorLogDAO.findTopSIPs();
    }


 // 다중 검색 조건을 처리하며 페이지네이션 기능이 포함된 검색
    @Transactional(readOnly = true)
    public List<BehaviorLog> findByMultipleCriteria(Map<String, Object> criteria) {
        return behaviorLogDAO.findByMultipleCriteria(criteria);
    }

}