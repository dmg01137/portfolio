package com.example.demo.controller.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.service.log.BehaviorLogService;

@Controller
public class BehaviorLogController {

    @Autowired
    private BehaviorLogService behaviorLogService;

    // 페이지: 모든 위험 로그 보기
    @GetMapping("/behaviorlog")
    public String showBehaviorLogPage(Model model) {
        List<BehaviorLog> logs = behaviorLogService.findAll(null);
        model.addAttribute("logs", logs);
        return "behaviorlog"; // Thymeleaf 템플릿 이름
    }

    // API 엔드포인트: 모든 위험 로그 가져오기
    @GetMapping("/api/behaviorlogs")
    public ResponseEntity<List<BehaviorLog>> getAllBehaviorLogs() {
        List<BehaviorLog> logs = behaviorLogService.findAll(null);
        return ResponseEntity.ok(logs);
    }

    // Top 5 s_ip 값 조회 API
    @GetMapping("/behaviorlog/top-sips")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getTopSIPs() {
        try {
            List<Map<String, Object>> topSIPs = behaviorLogService.findTopSIPs();
            return ResponseEntity.ok(topSIPs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 매개변수로 검색하는 기능 (BehaviorLog 검색)
    @GetMapping("/behaviorlog/search")
    public String searchBehaviorLogs(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "time", required = false) LocalDateTime time,
            @RequestParam(name = "s_ip", required = false) String s_ip,
            @RequestParam(name = "s_port", required = false) Integer s_port,
            @RequestParam(name = "d_ip", required = false) String d_ip,
            @RequestParam(name = "d_port", required = false) Integer d_port,
        
            @RequestParam(name = "len", required = false) Integer len,
          
            @RequestParam(name = "pattern1", required = false) String pattern1,
            @RequestParam(name = "pattern2", required = false) String pattern2,
            @RequestParam(name = "pattern3", required = false) String pattern3,
            @RequestParam(name = "packet", required = false) byte[] packet,
            @RequestParam(name = "base_cnt", required = false) Integer base_cnt,
            @RequestParam(name = "base_time", required = false) Integer base_time,
            @RequestParam(name = "action_type", required = false) Integer action_type,
            @RequestParam(name = "policy_name", required = false) String policy_name,
            Model model) {
        try {
            List<BehaviorLog> logs = behaviorLogService.search(id, time, s_ip, d_ip, s_port, d_port, action_type, len, base_cnt, base_time, pattern1, pattern2, pattern3, packet,policy_name);
            model.addAttribute("logs", logs);
            return "behaviorlog"; // Thymeleaf 템플릿 이름
        } catch (Exception e) {
            // 예외 발생 시 로깅하고 오류 페이지로 리다이렉트
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve behavior logs");
            return "error"; // 오류 페이지의 Thymeleaf 템플릿 이름
        }
    }

    // 다중 조건으로 위험 로그 조회 (DB에서 처리)

@GetMapping("/behaviorlog/multipleSearch")
public ResponseEntity<List<BehaviorLog>> searchBehaviorLogsFromDB(@RequestParam Map<String, String> params) {
    try {
        // 파라미터에서 빈 값을 제외하고 유효한 검색 조건만 처리
        Map<String, Object> searchParams = new HashMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                if ("time".equals(entry.getKey())) {
                    // 시간 파싱 예시: "yyyyMMdd" 형식을 LocalDateTime으로 변환
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDateTime time = LocalDateTime.parse(entry.getValue(), formatter);
                    searchParams.put(entry.getKey(), time);
                } else {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }
        }

        List<BehaviorLog> behaviorLogs = behaviorLogService.findByMultipleCriteria(searchParams);
        return ResponseEntity.ok(behaviorLogs);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}