package com.example.demo.dao.log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.BehaviorLog;

@Mapper
public interface BehaviorLogDAO {

    BehaviorLog findById(@Param("id") int id, @Param("tableName") String tableName);

    List<BehaviorLog> findAll(@Param("tableName") String tableName);
 // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs(@Param("tableName") String tableName);

   

    // Search logs by specific criteria
    List<BehaviorLog> search(  @Param("id") Integer id,
    	    @Param("time") LocalDateTime time,
    	    @Param("s_ip")String s_ip,
    	    @Param("d_ip") String d_ip,
    	    @Param("s_port") Integer s_port,
    	    @Param("d_port")  Integer d_port,
    	    @Param("action_type") Integer action_type,
    	    @Param("len") Integer len,
    	    @Param("base_cnt")Integer base_cnt,
    	    @Param("base_time")  Integer base_time,
    	    @Param("pattern1") String pattern1,
    	    @Param("pattern2") String pattern2,
    	    @Param("pattern3") String pattern3,
    	    @Param("packet")  byte[] packet,
    	    @Param("policy_name") String policy_name);
    
    // 다중 조건으로 패턴 로그 조회

    List<BehaviorLog> findByMultipleCriteria(@Param("tableName") String tableName, @Param("criteria") Map<String, Object> criteria);
}
