package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.PatternLog;

public interface PatternLogDAO {

    // ID를 기반으로 패턴 로그를 조회합니다.
    PatternLog findById(int id);
    
   
    // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs(@Param("tableName") String tableName);

    // Top 3 패턴 조합 조회
    List<Map<String, Object>> findTopPatternCombinations(@Param("tableName") String tableName);

    // 모든 패턴 로그 리스트를 조회합니다.
   

    List<PatternLog> findAll(@Param("tableName") String tableName);
    
    // 모든 패턴 로그 리스트를 최신 순으로 조회합니다.
    List<PatternLog> findAllOrderedByTimeDesc(@Param("tableName") String tableName);

  

    // Search logs by specific criteria
    List<PatternLog> search(@Param("id") Integer id,
            @Param("time") String time,
            @Param("sIp") String sIp,
            @Param("dIp") String dIp,
            @Param("sPort") Integer sPort,
            @Param("dPort") Integer dPort,
            @Param("len") Integer len,
            @Param("pattern1") String pattern1,
            @Param("pattern2") String pattern2,
            @Param("pattern3") String pattern3,
            @Param("packet") byte[] packet,
            @Param("actionType") Integer actionType,
    		@Param("policy_name") String policy_name);


    // Search logs by multiple criteria with pagination
    List<PatternLog> findByMultipleCriteria(@Param("tableName") String tableName, @Param("criteria") Map<String, Object> criteria);
}
