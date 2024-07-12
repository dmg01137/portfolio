package com.example.demo.dao.log;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.DangerousLog;

@Mapper
public interface DangerousLogDAO {

    // ID를 기반으로 위험 로그를 조회합니다.
    DangerousLog findById(int id);

    // 모든 위험 로그 리스트를 조회합니다.
    List<DangerousLog> findAll();

    // 새로운 위험 로그를 저장합니다.
    void save(DangerousLog dangerousLog);

    // 기존 위험 로그를 업데이트합니다.
    void update(DangerousLog dangerousLog);

    // ID를 기반으로 특정 위험 로그를 삭제합니다.
    void delete(int id);
    
    // 검색 조건에 따라 위험 로그를 조회합니다.
    List<DangerousLog> search(@Param("id") Integer id, 
                              @Param("ip") String ip, 
                              @Param("port") Integer port, 
                              @Param("policyNumber") String policyNumber);
}
