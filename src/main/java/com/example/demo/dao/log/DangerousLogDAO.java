package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.DangerousLog;

@Mapper
public interface DangerousLogDAO {

    // Select by dangerous number
    DangerousLog findByDangerousNumber(@Param("dangerous_number") int dangerous_number);

    // Select all logs from a specific table
    List<DangerousLog> findAll(@Param("tableName") String tableName);

 

    // Search logs by specific criteria
    List<DangerousLog> search(@Param("dangerous_number") Integer dangerous_number,
                              @Param("ip") String ip,
                              @Param("port") Integer port,
                              @Param("policy_name") String detection_number);

    // Search logs by multiple criteria with pagination
    List<DangerousLog> findByMultipleCriteria(@Param("tableName") String tableName, @Param("criteria") Map<String, Object> criteria);


}
