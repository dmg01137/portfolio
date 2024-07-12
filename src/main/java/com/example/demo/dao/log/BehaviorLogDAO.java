package com.example.demo.dao.log;

import java.util.List;

import com.example.demo.dto.log.BehaviorLog;

public interface BehaviorLogDAO {

    // ID를 기반으로 특정 행동 로그를 조회합니다.
    BehaviorLog findById(int id);

    // 모든 행동 로그 리스트를 조회합니다.
    List<BehaviorLog> findAll();

    // 새로운 행동 로그를 저장합니다.
    void save(BehaviorLog behaviorLog);

    // 기존 행동 로그를 업데이트합니다.
    void update(BehaviorLog behaviorLog);

    // ID를 기반으로 특정 행동 로그를 삭제합니다.
    void delete(int id);
}
