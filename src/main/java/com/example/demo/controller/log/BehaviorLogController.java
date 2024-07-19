package com.example.demo.controller.log;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.service.log.BehaviorLogService;

@Controller
@RequestMapping("/behaviorlog")
public class BehaviorLogController {

    @Autowired
    private BehaviorLogService behaviorLogService;

    // 모든 행동 로그 조회 페이지
    @GetMapping("")
    public String getAllLogs(Model model) {
        List<BehaviorLog> logs = behaviorLogService.findAll();
        model.addAttribute("logs", logs);
        return "behaviorlog"; // behaviorlog.html로 이동하여 전체 로그를 보여줍니다.
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
    @GetMapping("/top-sips")
    @ResponseBody
    public ResponseEntity<List<Map<String, Object>>> getTopSIPs() {
        try {
            List<Map<String, Object>> topSIPs = behaviorLogService.findTopSIPs();
            return ResponseEntity.ok(topSIPs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}