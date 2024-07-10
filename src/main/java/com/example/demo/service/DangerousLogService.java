package com.example.demo.service;

import com.example.demo.dao.DangerousLogDAO;
import com.example.demo.dto.DangerousLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DangerousLogService {

    private final DangerousLogDAO dangerousLogDAO;

    @Autowired
    public DangerousLogService(DangerousLogDAO dangerousLogDAO) {
        this.dangerousLogDAO = dangerousLogDAO;
    }

    public DangerousLog findById(int detection_number) {
        return dangerousLogDAO.findById(detection_number);
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

    public void delete(int detection_number) {
        dangerousLogDAO.delete(detection_number);
    }

    public List<DangerousLog> search(Integer detection_number, String ip, Integer port, String policyNumber) {
        return dangerousLogDAO.search(detection_number, ip, port, policyNumber);
    }
}
