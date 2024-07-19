package com.example.demo.controller.log;

import com.example.demo.dto.log.PatternLog;
import com.example.demo.service.log.PatternLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patternlogs")
public class PatternLogController {

    private final PatternLogService patternLogService;

    @Autowired
    public PatternLogController(PatternLogService patternLogService) {
        this.patternLogService = patternLogService;
    }

    // 모든 패턴 로그 조회
    @GetMapping
    public ResponseEntity<List<PatternLog>> getAllPatternLogs() {
        try {
            List<PatternLog> patternLogs = patternLogService.getAllPatternLogs();
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 ID의 패턴 로그 조회
    @GetMapping("/{id}")
    public ResponseEntity<PatternLog> getPatternLogById(@PathVariable int id) {
        try {
            PatternLog patternLog = patternLogService.getPatternLogById(id);
            if (patternLog != null) {
                return ResponseEntity.ok(patternLog);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 패턴 로그 생성
    @PostMapping
    public ResponseEntity<Void> createPatternLog(@RequestBody PatternLog patternLog) {
        try {
            patternLogService.createPatternLog(patternLog);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 패턴 로그 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePatternLog(@PathVariable int id, @RequestBody PatternLog patternLog) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(id);
            if (existingPatternLog != null) {
                patternLog.setId(id);
                patternLogService.updatePatternLog(patternLog);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 패턴 로그 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatternLog(@PathVariable int id) {
        try {
            PatternLog existingPatternLog = patternLogService.getPatternLogById(id);
            if (existingPatternLog != null) {
                patternLogService.deletePatternLog(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Top 5 s_ip 값 조회
    @GetMapping("/top-sips")
    public ResponseEntity<List<Map<String, Object>>> getTopSIPs() {
        try {
            List<Map<String, Object>> topSIPs = patternLogService.findTopSIPs();
            return ResponseEntity.ok(topSIPs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Top 3 패턴 조합 조회
    @GetMapping("/top-pattern-combinations")
    public ResponseEntity<List<Map<String, Object>>> getTopPatternCombinations() {
        try {
            List<Map<String, Object>> topPatternCombinations = patternLogService.findTopPatternCombinations();
            return ResponseEntity.ok(topPatternCombinations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // 모든 패턴 로그를 최신 순으로 조회
    @GetMapping("/ordered-by-time")
    public ResponseEntity<List<PatternLog>> getAllPatternLogsOrderedByTimeDesc() {
        try {
            List<PatternLog> patternLogs = patternLogService.findAllOrderedByTimeDesc();
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 다중 조건으로 패턴 로그 조회
    @GetMapping("/search")
    public ResponseEntity<List<PatternLog>> searchPatternLogs(@RequestParam Map<String, Object> params) {
        try {
            List<PatternLog> patternLogs = patternLogService.findByMultipleCriteria(params);
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
