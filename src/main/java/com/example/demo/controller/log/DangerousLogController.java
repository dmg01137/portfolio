package com.example.demo.controller.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.log.DangerousLog;
import com.example.demo.service.log.DangerousLogService;

@Controller
public class DangerousLogController {

    private final DangerousLogService dangerousLogService;

    @Autowired
    public DangerousLogController(DangerousLogService dangerousLogService) {
        this.dangerousLogService = dangerousLogService;
    }

    // 페이지: 모든 위험 로그 보기
    @GetMapping("/dangerous")
    public String showDangerousLogPage(Model model) {
        List<DangerousLog> logs = dangerousLogService.findAll();
        model.addAttribute("logs", logs);
        return "dangerouslog"; // Thymeleaf 템플릿 이름
    }

    // API 엔드포인트: 모든 위험 로그 가져오기
    @GetMapping("/api/dangerouslogs")
    public ResponseEntity<List<DangerousLog>> getAllDangerousLogs() {
        List<DangerousLog> logs = dangerousLogService.findAll();
        return ResponseEntity.ok(logs);
    }

    // 특정 매개변수로 검색하는 기능
    @GetMapping("/dangerouslog/search")
    public String searchDangerousLogs(
            @RequestParam(name = "dangerous_number", required = false) Integer dangerousNumber,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "port", required = false) Integer port,
            @RequestParam(name = "detection_number", required = false) Integer detectionNumber,
            Model model) {
        try {
            List<DangerousLog> logs = dangerousLogService.search(dangerousNumber, ip, port, detectionNumber);
            model.addAttribute("logs", logs);
            return "dangerouslog"; // Thymeleaf 템플릿 이름
        } catch (Exception e) {
            // 예외 발생 시 로깅하고 오류 페이지로 리다이렉트
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve dangerous logs");
            return "error"; // 오류 페이지의 Thymeleaf 템플릿 이름
        }
    }
    // 다중 조건으로 위험 로그 조회 (DB에서 처리)
    @GetMapping("/dangerouslog/multipleSearch")
    public ResponseEntity<List<DangerousLog>> searchDangerousLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            // 파라미터에서 빈 값을 제외하고 유효한 검색 조건만 처리
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }

            List<DangerousLog> dangerousLogs = dangerousLogService.findByMultipleCriteria(searchParams);
            return ResponseEntity.ok(dangerousLogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
