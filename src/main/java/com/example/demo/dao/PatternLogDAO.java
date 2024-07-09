package com.example.demo.dao;

import java.util.List;
import com.example.demo.dto.PatternLog;

public interface PatternLogDAO {

    // ID를 기반으로 패턴 로그를 조회합니다.
    PatternLog findById(int id);

    // 모든 패턴 로그 리스트를 조회합니다.
    List<PatternLog> findAll();

    // 새로운 패턴 로그를 저장합니다.
    void save(PatternLog patternLog);

    // 기존 패턴 로그를 업데이트합니다.
    void update(PatternLog patternLog);

    // ID를 기반으로 특정 패턴 로그를 삭제합니다.
    void delete(int id);
}
