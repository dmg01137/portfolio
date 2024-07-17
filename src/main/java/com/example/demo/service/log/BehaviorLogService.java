package com.example.demo.service.log;

import com.example.demo.dao.log.BehaviorLogDAO;
import com.example.demo.dto.log.BehaviorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BehaviorLogService {

    @Autowired
    private BehaviorLogDAO behaviorLogDAO;

    // ID를 기반으로 특정 행동 로그를 조회합니다.
    public BehaviorLog findById(int detection_number) {
        return behaviorLogDAO.findById(detection_number);
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
    public void delete(int detection_number) {
        behaviorLogDAO.delete(detection_number);
    }

    // Top 5 SIP 조회
    public List<String> findTop5SIPs() {
        return behaviorLogDAO.findTop5SIPs();
    }
}
