package com.example.demo.service.log;

import java.time.LocalDate;
import java.util.List;
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

   
    public List<PatternLog> findAll(String tableName) {
        String tableName1;
        if (tableName == null) {
            tableName1 = "pattern_log_" + LocalDate.now().toString().replace("-", "");
        } else {
            tableName1 = tableName;
        }
        return patternLogDAO.findAll(tableName1);
    }
    @Transactional
    public PatternLog getPatternLogById(int id) {
        return patternLogDAO.findById(id);
    }

   
    // Find top 5 s_ip values
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopSIPs() {
        return patternLogDAO.findTopSIPs("pattern_log_" + LocalDate.now().toString().replace("-", ""));
    }

    // Find top 3 pattern combinations
    @Transactional(readOnly = true)
    public List<Map<String, Object>> findTopPatternCombinations() {
        return patternLogDAO.findTopPatternCombinations("pattern_log_" + LocalDate.now().toString().replace("-", ""));
    }

    // Find all pattern logs ordered by time descending
    @Transactional(readOnly = true)
    public List<PatternLog> findAllOrderedByTimeDesc() {
        return patternLogDAO.findAllOrderedByTimeDesc("pattern_log_" + LocalDate.now().toString().replace("-", ""));
    }

    
    // Search logs by specific criteria
    @Transactional(readOnly = true)
    public List<PatternLog> search(Integer id, String  time,  String  sIp, String  dIp, Integer  sPort,Integer  dPort, Integer len,
    		String pattern1,String pattern2,String pattern3,byte[] packet,Integer actionType,String policy_name) {
        return patternLogDAO.search(id, time, sIp, dIp, sPort, dPort, len, pattern1, pattern2, pattern3, packet, actionType,policy_name);
    }

 // Search logs by multiple criteria
    @Transactional(readOnly = true)
    public List<PatternLog> findByMultipleCriteria(String tableName, Map<String, Object> criteria) {
        if (tableName == null) {
            tableName = "pattern_log_" + LocalDate.now().toString().replace("-", "");
        }
        return patternLogDAO.findByMultipleCriteria(tableName, criteria);
    }


	
}