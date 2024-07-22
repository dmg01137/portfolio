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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    	 List<BehaviorLog> logs = behaviorLogService.findAll();
        model.addAttribute("logs", logs);
        return "behaviorlog"; // Thymeleaf 템플릿 이름
    }
 
    // 모든 행동 로그 조회 페이지
    // API 엔드포인트: 모든 위험 로그 가져오기
    @GetMapping("/api/behaviorlogs")
    public ResponseEntity<List<BehaviorLog>> getAllBehaviorLogs() {
    	 List<BehaviorLog> logs = behaviorLogService.findAll();
        return ResponseEntity.ok(logs);
    }
  
 
   
    // 행동 로그 저장 처리
    @PostMapping("/save")
    public String saveLog(@ModelAttribute BehaviorLog log) {
        behaviorLogService.save(log);
        return "redirect:/behaviorlog"; // 저장 후 목록 페이지로 리다이렉트합니다.
    }

    // 특정 행동 로그 업데이트 처리
    @PostMapping("/update/{id}")
    public String updateLog(@PathVariable("id") int id, @ModelAttribute BehaviorLog log) {
        log.setId(id); // 경로에서 받은 ID를 설정하여 업데이트 대상을 설정합니다.
        behaviorLogService.update(log);
        return "redirect:/behaviorlog"; // 업데이트 후 목록 페이지로 리다이렉트합니다.
    }

    // 특정 행동 로그 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteLog(@PathVariable("id") int id) {
        behaviorLogService.delete(id);
        return "redirect:/behaviorlog"; // 삭제 후 목록 페이지로 리다이렉트합니다.
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
            @RequestParam(name = "time", required = false) String time,
            @RequestParam(name = "s_ip", required = false) String s_ip,
            @RequestParam(name = "s_port", required = false) Integer s_port,
            @RequestParam(name = "d_ip", required = false) String d_ip,
            @RequestParam(name = "d_port", required = false) Integer d_port,
            @RequestParam(name = "action_type", required = false) String action_type,
            @RequestParam(name = "len", required = false) Integer len,
            @RequestParam(name = "base_cnt", required = false) Integer base_cnt,
            @RequestParam(name = "base_time", required = false) String base_time,
            @RequestParam(name = "pattern1", required = false) String pattern1,
            @RequestParam(name = "pattern2", required = false) String pattern2,
            @RequestParam(name = "pattern3", required = false) String pattern3,
            @RequestParam(name = "packet", required = false) String packet,
            Model model) {
        try {
            List<BehaviorLog> logs = behaviorLogService.search(id, time, s_ip, s_port, d_ip, d_port, action_type, len, base_cnt, base_time, pattern1, pattern2, pattern3, packet);
            model.addAttribute("logs", logs);
            return "behaviorlog"; // Thymeleaf 템플릿 이름
        } catch (Exception e) {
            // 예외 발생 시 로깅하고 오류 페이지로 리다이렉트
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve behavior logs");
            return "error"; // 오류 페이지의 Thymeleaf 템플릿 이름
        }
    }
    // 다중 조건으로 행동 로그 조회 (API 엔드포인트)
    @GetMapping("/api/behaviorlog/multipleSearch")
    @ResponseBody
    public ResponseEntity<List<BehaviorLog>> searchBehaviorLogsAPI(
            @RequestParam Map<String, String> params) {
        try {
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }
            List<BehaviorLog> logs = behaviorLogService.findByMultipleCriteria(searchParams);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}