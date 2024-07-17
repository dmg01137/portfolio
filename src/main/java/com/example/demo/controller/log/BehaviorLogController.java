package com.example.demo.controller.log;

import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.service.log.BehaviorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/update/{detection_number}")
    public String updateLog(@PathVariable("detection_number") int detection_number, @ModelAttribute BehaviorLog log) {
        log.setDetection_number(detection_number); // 경로에서 받은 ID를 설정하여 업데이트 대상을 설정합니다.
        behaviorLogService.update(log);
        return "redirect:/behaviorlog"; // 업데이트 후 목록 페이지로 리다이렉트합니다.
    }

    // 특정 행동 로그 삭제 처리
    @GetMapping("/delete/{detection_number}")
    public String deleteLog(@PathVariable("detection_number") int detection_number) {
        behaviorLogService.delete(detection_number);
        return "redirect:/behaviorlog"; // 삭제 후 목록 페이지로 리다이렉트합니다.
    }

    // Top 5 SIP 조회 API
    @GetMapping("/top-sips")
    public ResponseEntity<List<String>> getTopSIPs() {
        try {
            List<String> topSIPs = behaviorLogService.findTop5SIPs(); // Top 5 SIP 조회 서비스 메서드를 호출합니다.
            return ResponseEntity.ok(topSIPs); // 조회된 결과를 성공 응답으로 반환합니다.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 오류 발생 시 500 에러를 반환합니다.
        }
    }
}
