package com.example.demo.service.log;

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

    @Autowired
    public DangerousLogService(DangerousLogDAO dangerousLogDAO) {
        this.dangerousLogDAO = dangerousLogDAO;
    }

    public DangerousLog findByDangerousNumber(int dangerousNumber) {
        return dangerousLogDAO.findByDangerousNumber(dangerousNumber);
    }

    public List<DangerousLog> findAll() {
        return dangerousLogDAO.findAll();
    }

    public void save(DangerousLog log) {
        dangerousLogDAO.save(log);
    }

    public void update(DangerousLog log) {
        dangerousLogDAO.update(log);
    }

    public void delete(int dangerousNumber) {
        dangerousLogDAO.delete(dangerousNumber);
    }

    public List<DangerousLog> search(Integer dangerousNumber, String ip, Integer port, Integer detectionNumber) {
        return dangerousLogDAO.search(dangerousNumber, ip, port, detectionNumber);
    }

    @Transactional(readOnly = true)
    public List<DangerousLog> findByMultipleCriteria(Map<String, Object> criteria) {
        return dangerousLogDAO.findByMultipleCriteria(criteria);
    }
}
