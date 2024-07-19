package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.log.DangerousLog;
import com.example.demo.dto.log.PatternLog;

@Mapper
public interface DangerousLogDAO {

    // Select by ID
    DangerousLog findByDangerousNumber(@Param("dangerous_number") int dangerous_number);

    // Select all
    List<DangerousLog> findAll();

    // Insert
    void save(DangerousLog dangerousLog);

    // Update
    void update(DangerousLog dangerousLog);

    // Delete
    void delete(@Param("dangerous_number") int dangerous_number);

    // Search method
    List<DangerousLog> search(@Param("dangerous_number") Integer dangerous_number,
                              @Param("ip") String ip,
                              @Param("port") Integer port,
                              @Param("detection_number") Integer detection_number);

 // 다중 조건으로 위험 로그를 조회합니다.
    List<DangerousLog> findByMultipleCriteria(Map<String, Object> criteria);


}
