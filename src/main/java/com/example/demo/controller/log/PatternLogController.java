package com.example.demo.controller.log;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.log.PatternLog;
import com.example.demo.service.log.PatternLogService;

@RestController
@RequestMapping("/api/patternlogs")
public class PatternLogController {

    private final PatternLogService patternLogService;

    // PatternLogService 의존성 주입
    public PatternLogController(PatternLogService patternLogService) {
        this.patternLogService = patternLogService;
    }

    // 모든 패턴 로그 조회
    @GetMapping
    public ResponseEntity<List<PatternLog>> getAllPatternLogs() {
        try {
            List<PatternLog> patternLogs = patternLogService.getAllPatternLogs();
            return ResponseEntity.ok(patternLogs);  // 성공적으로 패턴 로그 리스트 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 발생 시 500 에러 반환
        }
    }

    // 특정 ID의 패턴 로그 조회
    @GetMapping("/{detection_number}")
    public ResponseEntity<PatternLog> getPatternLogById(@PathVariable int detection_number) {
        try {
            PatternLog patternLog = patternLogService.getPatternLogById(detection_number);
            if (patternLog != null) {
                return ResponseEntity.ok(patternLog);  // 패턴 로그가 존재하면 해당 패턴 로그 반환
            } else {
                return ResponseEntity.notFound().build();  // 패턴 로그가 없으면 404 에러 반환
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 발생 시 500 에러 반환
        }
    }

    // 패턴 로그 생성
    @PostMapping
    public ResponseEntity<Void> createPatternLog(@RequestBody PatternLog patternLog) {
        try {
            patternLogService.createPatternLog(patternLog);
            return ResponseEntity.status(HttpStatus.CREATED).build();  // 패턴 로그 생성 성공 시 201 Created 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 발생 시 500 에러 반환
        }
    }

    // 특정 ID의 패턴 로그 업데이트
    @PutMapping("/{detection_number}")
    public ResponseEntity<Void> updatePatternLog(@PathVariable int detection_number, @RequestBody PatternLog patternLog) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(detection_number);
            if (existingPatternLog != null) {
                patternLog.setDetection_number(detection_number);
                patternLogService.updatePatternLog(patternLog);
                return ResponseEntity.ok().build();  // 패턴 로그 업데이트 성공 시 200 OK 반환
            } else {
                return ResponseEntity.notFound().build();  // 해당 ID의 패턴 로그가 없으면 404 에러 반환
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 발생 시 500 에러 반환
        }
    }

    // 특정 ID의 패턴 로그 삭제
    @DeleteMapping("/{detection_number}")
    public ResponseEntity<Void> deletePatternLog(@PathVariable int detection_number) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(detection_number);
            if (existingPatternLog != null) {
                patternLogService.deletePatternLog(detection_number);
                return ResponseEntity.ok().build();  // 패턴 로그 삭제 성공 시 200 OK 반환
            } else {
                return ResponseEntity.notFound().build();  // 해당 ID의 패턴 로그가 없으면 404 에러 반환
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 서버 오류 발생 시 500 에러 반환
        }
    }
}
