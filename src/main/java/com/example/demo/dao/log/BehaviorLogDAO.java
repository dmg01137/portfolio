package com.example.demo.dao.log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.dto.log.PatternLog;

@Mapper
public interface BehaviorLogDAO {

    BehaviorLog findById(@Param("id") int id, @Param("tableName") String tableName);

    List<BehaviorLog> findAll(@Param("tableName") String tableName);
 // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs(@Param("tableName") String tableName);

   

    // 다중 조건으로 패턴 로그 조회
    List<BehaviorLog> findByMultipleCriteria(@Param("tableName") String tableName, @Param("criteria") Map<String, Object> criteria);
}
