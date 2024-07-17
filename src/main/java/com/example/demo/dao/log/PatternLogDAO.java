package com.example.demo.dao.log;

import com.example.demo.dto.log.PatternLog;

import java.util.List;
import java.util.Map;

public interface PatternLogDAO {

    // ID를 기반으로 패턴 로그를 조회합니다.
    PatternLog findById(int detection_number);

   

    // 새로운 패턴 로그를 저장합니다.
    void save(PatternLog patternLog);

    // 기존 패턴 로그를 업데이트합니다.
    void update(PatternLog patternLog);

    // ID를 기반으로 특정 패턴 로그를 삭제합니다.
    void delete(int detection_number);

    // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs();

    // Top 3 패턴 조합 조회
    List<Map<String, Object>> findTopPatternCombinations();

	List<PatternLog> findAll();
	 // 모든 패턴 로그 리스트를 최신 순으로 조회합니다.
    List<PatternLog> findAllOrderedByTimeDesc(); 
    
}
