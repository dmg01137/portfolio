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

    List<BehaviorLog> search(@Param("date") LocalDateTime date, @Param("id") Integer id,
                             @Param("time") LocalDateTime time, @Param("s_ip") String sIp,
                             @Param("d_ip") String dIp, @Param("s_port") Integer sPort,
                             @Param("d_port") Integer dPort, @Param("action_type") Integer actionType,
                             @Param("len") Integer len, @Param("base_cnt") Integer baseCnt,
                             @Param("base_time") LocalDateTime baseTime, @Param("pattern1") String pattern1,
                             @Param("pattern2") String pattern2, @Param("pattern3") String pattern3,
                             @Param("packet") String packet, @Param("policy_name") String policyName,
                             @Param("tableName") String tableName);

    List<BehaviorLog> findByMultipleCriteria(@Param("tableName") String tableName, @Param("criteria") Map<String, Object> criteria);

	List<BehaviorLog> search(LocalDateTime date, Integer id, LocalDateTime time, String s_ip, Integer s_port,
			String d_ip, Integer d_port, Integer action_type, Integer len, Integer base_cnt, Integer base_time,
			String pattern1, String pattern2, String pattern3, String packet, String tableName);

	
}
