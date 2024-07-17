package com.example.demo.dao.log;

import com.example.demo.dto.log.BehaviorLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BehaviorLogDAO {

    BehaviorLog findById(@Param("detection_number") int detection_number);

    List<BehaviorLog> findAll();

    void save(BehaviorLog behaviorLog);

    void update(BehaviorLog behaviorLog);

    void delete(@Param("detection_number") int detection_number);

    // Top 5 s_ip 값 조회
    List<Map<String, Object>> findTopSIPs();

    // Top 5 SIP 조회 (s_ip 값만)
    List<String> findTop5SIPs();
}
