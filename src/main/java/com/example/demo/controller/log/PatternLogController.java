package com.example.demo.controller.log;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.log.PatternLog;
import com.example.demo.service.log.PatternLogService;

@RestController
@RequestMapping("/api/patternlogs")
public class PatternLogController {

    private final PatternLogService patternLogService;

    public PatternLogController(PatternLogService patternLogService) {
        this.patternLogService = patternLogService;
    }

    @GetMapping
    public ResponseEntity<List<PatternLog>> getAllPatternLogs() {
        try {
            List<PatternLog> patternLogs = patternLogService.getAllPatternLogs("default_table_name"); // 기본 테이블 이름을 전달
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{detection_number}")
    public ResponseEntity<PatternLog> getPatternLogById(@PathVariable int detection_number) {
        try {
            PatternLog patternLog = patternLogService.getPatternLogById(detection_number, "default_table_name"); // 기본 테이블 이름을 전달
            if (patternLog != null) {
                return ResponseEntity.ok(patternLog);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createPatternLog(@RequestBody PatternLog patternLog) {
        try {
            patternLogService.createPatternLog(patternLog, "default_table_name"); // 기본 테이블 이름을 전달
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{detection_number}")
    public ResponseEntity<Void> updatePatternLog(@PathVariable int detection_number, @RequestBody PatternLog patternLog) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(detection_number, "default_table_name"); // 기본 테이블 이름을 전달
            if (existingPatternLog != null) {
                patternLog.setDetectionNumber(detection_number);
                patternLogService.updatePatternLog(patternLog, "default_table_name"); // 기본 테이블 이름을 전달
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{detection_number}")
    public ResponseEntity<Void> deletePatternLog(@PathVariable int detection_number) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(detection_number, "default_table_name"); // 기본 테이블 이름을 전달
            if (existingPatternLog != null) {
                patternLogService.deletePatternLog(detection_number, "default_table_name"); // 기본 테이블 이름을 전달
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
