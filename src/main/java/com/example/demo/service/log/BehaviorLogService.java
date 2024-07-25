package com.example.demo.service.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private BehaviorLogDAO behaviorLogDAO;
    LocalDate currentDate = LocalDate.now();
    String tableName = currentDate.toString().replace("-", "");

    @Autowired
    public BehaviorLogService(BehaviorLogDAO behaviorLogDAO) {
        this.behaviorLogDAO = behaviorLogDAO;
    }
    
    // 패킷 정보 가져오기
    public BehaviorLog findPacketInfoById(int id) {
        // ID를 기준으로 BehaviorLog를 조회합니다.
        return behaviorLogDAO.findById(id);
    }


    // 특정 테이블에서 모든 로그 조회
    @Transactional(readOnly = true)
    public List<BehaviorLog> findAll(String tableName) {
        if (tableName == null) {
            tableName = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        }
        return behaviorLogDAO.findAll(tableName);
    }

    // Top 5 s_ip 값 조회
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        return behaviorLogDAO.findTopSIPs("behavior_log_" + LocalDate.now().toString().replace("-", ""));
    }

    // 특정 기준으로 로그 검색
    @Transactional(readOnly = true)
    public List<BehaviorLog> search(Integer id, LocalDateTime time, String s_ip, String d_ip, Integer s_port,
                                    Integer d_port, Integer action_type, Integer len, Integer base_cnt,
                                    Integer base_time, String pattern1, String pattern2, String pattern3,
                                    byte[] packet, String policy_name) {
        // TODO: 구현 필요
        return ((BehaviorLogService) behaviorLogDAO).search(id, time, s_ip, d_ip, s_port, d_port, action_type, len,
                base_cnt, base_time, pattern1, pattern2, pattern3, packet, policy_name);
    }

    // 여러 기준으로 로그 검색
    @Transactional(readOnly = true)
    public List<BehaviorLog> findByMultipleCriteria(String tableName, Map<String, Object> criteria) {
        if (tableName == null) {
            tableName = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        }
        if (criteria.containsKey("date")) {
            String date = criteria.get("date").toString();
            tableName = "behavior_log_" + date.replace("-", "");
        }
        return behaviorLogDAO.findByMultipleCriteria(tableName, criteria);
    }

  
}
