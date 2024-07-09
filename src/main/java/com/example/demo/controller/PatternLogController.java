package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.PatternLog;
import com.example.demo.service.PatternLogService;

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
            List<PatternLog> patternLogs = patternLogService.getAllPatternLogs();
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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

    @PostMapping
    public ResponseEntity<Void> createPatternLog(@RequestBody PatternLog patternLog) {
        try {
            patternLogService.createPatternLog(patternLog);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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
}
