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

    public DangerousLog findBydangerous_number(int dangerous_number) {
        return dangerousLogDAO.findBydangerous_number(dangerous_number);
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

    public void delete(int dangerous_number) {
        dangerousLogDAO.delete(dangerous_number);
    }

    public List<DangerousLog> search(Integer dangerous_number, String ip, Integer port, Integer detection_number) {
        return dangerousLogDAO.search(dangerous_number, ip, port,detection_number);
    }
    
 // 다중 검색 기능을 위한 패턴 로그 조회
    @Transactional(readOnly = true)
    public List<DangerousLog> findByMultipleCriteria(Map<String, String> params) {
        return dangerousLogDAO.findByMultipleCriteria(params);
    }

	
}
