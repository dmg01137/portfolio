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

    
 // 다중 조건으로 위험 로그 조회 (DB에서 처리)
    @GetMapping("/behaviorlog/multipleSearch")
    public ResponseEntity<List<BehaviorLog>> searchBehaviorLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            // 파라미터에서 빈 값을 제외하고 유효한 검색 조건만 처리
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isEmpty()) {
                    searchParams.put(entry.getKey(), entry.getValue());
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