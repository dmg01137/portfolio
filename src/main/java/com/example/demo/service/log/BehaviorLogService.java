package com.example.demo.service.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    public List<BehaviorLog> findAll(String tableName) {
        if (tableName == null) {
            tableName = "behavior_log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        return behaviorLogDAO.findAll(tableName);
    }

    // Top 5 s_ip 값 조회
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        return behaviorLogDAO.findTopSIPs(" behavior_log_" + LocalDate.now().toString().replace("-", ""));
    }

    public List<BehaviorLog> search(LocalDate date, Integer id, LocalDateTime time, String s_ip, String d_ip,
                                    Integer s_port, Integer d_port, Integer action_type, Integer len, Integer base_cnt,
                                    Integer base_time, String pattern1, String pattern2, String pattern3,
                                    String packet, String tableName) {
        return behaviorLogDAO.search(date, id, time, s_ip, s_port,d_ip,  d_port, action_type, len, base_cnt,
                base_time, pattern1, pattern2, pattern3, packet, tableName);
    }

    @Transactional(readOnly = true)
    public List<BehaviorLog> findByMultipleCriteria(Map<String, String> criteria, String tableName) {
        Map<String, Object> searchParams = convertCriteriaToMap(criteria);
        return behaviorLogDAO.findByMultipleCriteria(tableName, searchParams);
    }

    private Map<String, Object> convertCriteriaToMap(Map<String, String> criteria) {
        Map<String, Object> searchParams = new HashMap<>();
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                searchParams.put(entry.getKey(), entry.getValue());
            }
        }
        return searchParams;
    }
}