package com.example.demo.dao.log;

import com.example.demo.dto.log.BehaviorLog;

import java.util.List;

public interface BehaviorLogDAO {

    BehaviorLog findById(int detection_number);

    List<BehaviorLog> findAll();

    void save(BehaviorLog behaviorLog);

    void update(BehaviorLog behaviorLog);

    void delete(int detection_number);

    List<String> findTop5SIPs();
}
