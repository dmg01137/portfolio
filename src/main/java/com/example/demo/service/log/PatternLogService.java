package com.example.demo.service.log;

import java.util.List;

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
    public List<PatternLog> getAllPatternLogs() {
        return patternLogDAO.findAll();
    }

    @Transactional
    public PatternLog getPatternLogById(int id) {
        return patternLogDAO.findById(id);
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
    public void deletePatternLog(int id) {
        patternLogDAO.delete(id);
    }
}
