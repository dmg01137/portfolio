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

    // 특정 매개변수로 행동 로그 검색
    List<BehaviorLog> search(@Param("id") Integer id, @Param("time") String time, @Param("s_ip") String sIp,
            @Param("s_port") Integer sPort, @Param("d_ip") String dIp, @Param("d_port") Integer dPort,
            @Param("action_type") String actionType, @Param("len") Integer len, @Param("base_cnt") Integer baseCnt,
            @Param("base_time") String baseTime, @Param("pattern1") String pattern1, @Param("pattern2") String pattern2,
            @Param("pattern3") String pattern3, @Param("packet") String packet);

 // 다중 조건으로 위험 로그를 조회합니다.
    List<BehaviorLog> findByMultipleCriteria(Map<String, Object> criteria);
}
    
 
