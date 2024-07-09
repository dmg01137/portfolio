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

    public DangerousLog findById(int id) {
        return dangerousLogDAO.findById(id);
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

    public void delete(int id) {
        dangerousLogDAO.delete(id);
    }

    public List<DangerousLog> search(Integer id, String ip, Integer port, String policyNumber) {
        return dangerousLogDAO.search(id, ip, port, policyNumber);
    }
}
