package com.example.demo.dao.log;

import com.example.demo.dto.log.BehaviorLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BehaviorLogDAO {

    BehaviorLog findById(@Param("detection_number") int detection_number);

    List<BehaviorLog> findAll();

    void save(BehaviorLog behaviorLog);

    void update(BehaviorLog behaviorLog);

    void delete(@Param("detection_number") int detection_number);

    List<String> findTop5SIPs();
}
