package com.example.demo.service.log;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.log.DangerousLogDAO;
import com.example.demo.dto.log.DangerousLog;

@Service
@Transactional
public class DangerousLogService {

    private final DangerousLogDAO dangerousLogDAO;
    LocalDate currentDate = LocalDate.now();
    String tableName = currentDate.toString().replace("-", "");
    
    @Autowired
    public DangerousLogService(DangerousLogDAO dangerousLogDAO) {
        this.dangerousLogDAO = dangerousLogDAO;
    }

    // Find a log by dangerous number
    @Transactional(readOnly = true)
    public DangerousLog findByDangerousNumber(int dangerousNumber) {
        return dangerousLogDAO.findByDangerousNumber(dangerousNumber);
    }

    // Find all logs from a specific table
    @Transactional(readOnly = true)
    public List<DangerousLog> findAll(String tableName) {
        if (tableName == null) {
            tableName = "dangerous_log_" + LocalDate.now().toString().replace("-", "");
        }
        return dangerousLogDAO.findAll(tableName);
    }


   

    // Search logs by specific criteria
    @Transactional(readOnly = true)
    public List<DangerousLog> search(Integer dangerousNumber, String ip, Integer port, String policy_name) {
    	
        return ((DangerousLogService) dangerousLogDAO).search(dangerousNumber,ip,port,policy_name);
    }
    // Search logs by multiple criteria
    @Transactional(readOnly = true)
    public List<DangerousLog> findByMultipleCriteria(String tableName, Map<String, Object> criteria) {
        if (tableName == null) {
            tableName = "dangerous_log_" + LocalDate.now().toString().replace("-", "");
        }
        if (criteria.containsKey("date")) {
            String date = criteria.get("date").toString();
            tableName = "dangerous_log_" + date.replace("-", "");
        }
        return dangerousLogDAO.findByMultipleCriteria(tableName, criteria);
    }
}

