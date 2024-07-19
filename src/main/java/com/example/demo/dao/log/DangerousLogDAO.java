package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.DangerousLog;
import com.example.demo.dto.log.PatternLog;

@Mapper
public interface DangerousLogDAO {

    // ID를 기반으로 위험 로그를 조회합니다.
    DangerousLog findBydangerous_number(int dangerous_number);

    // 모든 위험 로그 리스트를 조회합니다.
    List<DangerousLog> findAll();

    // 새로운 위험 로그를 저장합니다.
    void save(DangerousLog dangerousLog);

    // 기존 위험 로그를 업데이트합니다.
    void update(DangerousLog dangerousLog);

    // ID를 기반으로 특정 위험 로그를 삭제합니다.
    void delete(int dangerous_number);
    
    // 검색 조건에 따라 위험 로그를 조회합니다.
    List<DangerousLog> search(@Param("dangerous_number") Integer dangerous_number, 
                              @Param("ip") String ip, 
                              @Param("port") Integer port, 
                              @Param("detection_number") Integer detection_number);
    
 // 다중 조건으로 패턴 로그를 조회합니다.
    List<DangerousLog> findByMultipleCriteria(Map<String, String> params);

}
