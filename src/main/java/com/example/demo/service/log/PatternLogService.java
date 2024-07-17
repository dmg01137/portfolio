package com.example.demo.service.log;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.log.PatternLogDAO;
import com.example.demo.dto.log.PatternLog;

@Service
public class PatternLogService {

    private final PatternLogDAO patternLogDAO;

    @Autowired
    public PatternLogService(PatternLogDAO patternLogDAO) {
        this.patternLogDAO = patternLogDAO;
    }

    @Transactional
    public List<PatternLog> getAllPatternLogs(String tableName) {
        Map<String, String> params = new HashMap<>();
        params.put("tableName", tableName);
        return patternLogDAO.findAll(params);
    }

    @Transactional
    public PatternLog getPatternLogById(int detection_number, String tableName) {
        Map<String, Object> params = new HashMap<>();
        params.put("detection_number", detection_number);
        params.put("tableName", tableName);
        return patternLogDAO.findById(params);
    }

    @Transactional
    public void createPatternLog(PatternLog patternLog, String tableName) {
        patternLogDAO.save(patternLog, tableName);
    }

    @Transactional
    public void updatePatternLog(PatternLog patternLog, String tableName) {
        patternLogDAO.update(patternLog, tableName);
    }

    @Transactional
    public void deletePatternLog(int detection_number, String tableName) {
        patternLogDAO.delete(detection_number, tableName);
    }
}
