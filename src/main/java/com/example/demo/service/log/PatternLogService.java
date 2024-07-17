package com.example.demo.service.log;

import com.example.demo.dao.log.PatternLogDAO;
import com.example.demo.dto.log.PatternLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PatternLogService {

    private final PatternLogDAO patternLogDAO;

    @Autowired
    public PatternLogService(PatternLogDAO patternLogDAO) {
        this.patternLogDAO = patternLogDAO;
    }

    @Transactional
    public List<PatternLog> getAllPatternLogs() {
        return patternLogDAO.findAll();
    }

    @Transactional
    public PatternLog getPatternLogById(int detection_number) {
        return patternLogDAO.findById(detection_number);
    }

    @Transactional
    public void createPatternLog(PatternLog patternLog) {
        patternLogDAO.save(patternLog);
    }

    @Transactional
    public void updatePatternLog(PatternLog patternLog) {
        patternLogDAO.update(patternLog);
    }

    @Transactional
    public void deletePatternLog(int detection_number) {
        patternLogDAO.delete(detection_number);
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        return patternLogDAO.findTopSIPs();
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopPatternCombinations() {
        return patternLogDAO.findTopPatternCombinations();
    }

    @Transactional(readOnly = true)
    public List<PatternLog> findAllOrderedByTimeDesc() {
        return patternLogDAO.findAllOrderedByTimeDesc();
    }
}
