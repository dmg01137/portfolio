package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.BehaviorLog;

@Mapper
public interface BehaviorLogDAO {

    BehaviorLog findById(@Param("id") int id);

    List<BehaviorLog> findAll();

    void save(BehaviorLog behaviorLog);

    void update(BehaviorLog behaviorLog);

    void delete(@Param("id") int id);

    // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs();

    // 다중 검색 기능을 위한 행동 로그 조회
    List<BehaviorLog> findByMultipleCriteria(@Param("criteria") Map<String, Object> criteria);
}
