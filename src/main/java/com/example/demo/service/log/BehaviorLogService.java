package com.example.demo.service.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    // ID를 기반으로 특정 행동 로그를 조회합니다.
    public BehaviorLog findById(int id, String tableName) {
        return behaviorLogDAO.findById(id, tableName);
    }

 // 모든 행동 로그 리스트를 조회합니다.
    public List<BehaviorLog> findAll(String tableName) {
        String tableName1;
        if (tableName == null) {
            tableName1 = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        } else {
            tableName1 = tableName;
        }
        return behaviorLogDAO.findAll(tableName1);
    }


  

    // Top 5 s_ip 값 조회
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        // 테이블 이름 설정
        String tableName = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        return behaviorLogDAO.findTopSIPs(tableName);
    }

    // 특정 매개변수로 행동 로그 검색
    public List<BehaviorLog> search(Integer id, LocalDateTime time, String s_ip, String d_ip, Integer s_port,
			Integer d_port, Integer action_type, Integer len, Integer base_cnt, Integer base_time, String pattern1,
			String pattern2, String pattern3, byte[] packet, String policy_name) {
        // 테이블 이름 설정
        String tableName = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        return behaviorLogDAO.search(tableName, buildSearchParams(id, time, s_ip, d_ip, s_port, d_port, action_type, len, base_cnt, base_time, pattern1, pattern2, pattern3, packet,policy_name));
    }

 

	// 다중 검색 조건을 처리하며 페이지네이션 기능이 포함된 검색
    @Transactional(readOnly = true)
    public List<BehaviorLog> findByMultipleCriteria(Map<String, Object> criteria) {
        // 테이블 이름 설정
        String tableName = "behavior_log_" + LocalDate.now().toString().replace("-", "");
        return behaviorLogDAO.findByMultipleCriteria(tableName, criteria);
    }

 // 검색 파라미터에서 빈 값을 제외한 맵을 구성합니다.
    private Map<String, Object> buildSearchParams(Integer id, LocalDateTime time, String s_ip, String d_ip,
                                                  Integer s_port, Integer d_port, Integer action_type, Integer len,
                                                  Integer base_cnt, Integer base_time, String pattern1, String pattern2,
                                                  String pattern3, byte[] packet, String policy_name) {
        // 생성된 검색 맵을 초기화합니다.
        Map<String, Object> searchParams = new HashMap<>();
        if (id != null) {
            searchParams.put("id", id);
        }
        if (time != null) {
            searchParams.put("time", time);
        }
        if (s_ip != null && !s_ip.isEmpty()) {
            searchParams.put("s_ip", s_ip);
        }
        if (d_ip != null && !d_ip.isEmpty()) {
            searchParams.put("d_ip", d_ip);
        }
        if (s_port != null) {
            searchParams.put("s_port", s_port);
        }
        if (d_port != null) {
            searchParams.put("d_port", d_port);
        }
        if (action_type != null) {
            searchParams.put("action_type", action_type);
        }
        if (len != null) {
            searchParams.put("len", len);
        }
        if (base_cnt != null) {
            searchParams.put("base_cnt", base_cnt);
        }
        if (base_time != null) {
            searchParams.put("base_time", base_time);
        }
        if (pattern1 != null && !pattern1.isEmpty()) {
            searchParams.put("pattern1", pattern1);
        }
        if (pattern2 != null && !pattern2.isEmpty()) {
            searchParams.put("pattern2", pattern2);
        }
        if (pattern3 != null && !pattern3.isEmpty()) {
            searchParams.put("pattern3", pattern3);
        }
        if (packet != null && packet.length > 0) {
            searchParams.put("packet", packet);
        }
        if (policy_name != null && !policy_name.isEmpty()) {
            searchParams.put("policy_name", policy_name);
        }
        return searchParams;
    }
}