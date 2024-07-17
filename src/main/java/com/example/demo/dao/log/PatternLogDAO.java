package com.example.demo.dao.log;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.log.PatternLog;

public interface PatternLogDAO {

    // ID를 기반으로 패턴 로그를 조회합니다.
    PatternLog findById(Map<String, Object> params);

    // 모든 패턴 로그 리스트를 조회합니다.
    List<PatternLog> findAll(Map<String, String> params);

    // 새로운 패턴 로그를 저장합니다.
    void save(PatternLog patternLog, String tableName);

    // 기존 패턴 로그를 업데이트합니다.
    void update(PatternLog patternLog, String tableName);

    // ID를 기반으로 특정 패턴 로그를 삭제합니다.
    void delete(int detection_number, String tableName);
}
