package com.example.demo.controller.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.service.log.BehaviorLogService;

@Controller
@RequestMapping("/behaviorlog")
public class BehaviorLogController {

    @Autowired
    private BehaviorLogService behaviorLogService;

    // 모든 로그 조회
    @GetMapping("")
    public String getAllLogs(Model model) {
        List<BehaviorLog> logs = behaviorLogService.findAll();
        model.addAttribute("logs", logs);
        return "behaviorlog"; // behaviorlog.html로 이동
    }

    // 로그 저장
    @PostMapping("/save")
    public String saveLog(@ModelAttribute BehaviorLog log) {
        behaviorLogService.save(log);
        return "redirect:/behaviorlog"; // 목록으로 리다이렉트
    }

    // 로그 업데이트
    @PostMapping("/update/{id}")
    public String updateLog(@PathVariable("detection_number") int detection_number, @ModelAttribute BehaviorLog log) {
        log.setDetection_number(detection_number); // 경로에서 받은 ID를 설정하여 업데이트
        behaviorLogService.update(log);
        return "redirect:/behaviorlog"; // 목록으로 리다이렉트
    }

    // 로그 삭제
    @GetMapping("/delete/{detection_number}")
    public String deleteLog(@PathVariable("detection_number") int detection_number) {
        behaviorLogService.delete(detection_number);
        return "redirect:/behaviorlog"; // 목록으로 리다이렉트
    }
}
