package com.example.demo.controller.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.log.DangerousLog;
import com.example.demo.service.log.DangerousLogService;

@Controller
public class DangerousLogController {

    private final DangerousLogService dangerousLogService;

    @Autowired
    public DangerousLogController(DangerousLogService dangerousLogService) {
        this.dangerousLogService = dangerousLogService;
    }

    // 위험 로그 목록 페이지
    @GetMapping("/dangerous")
    public String showDangerousLogPage(Model model) {
        List<DangerousLog> logs = dangerousLogService.findAll();
        model.addAttribute("logs", logs);
        return "dangerouslog"; // Thymeleaf 템플릿 이름 수정
    }
    @GetMapping("/api/dangerouslogs")
    @ResponseBody
    public List<DangerousLog> getAllDangerousLogs() {
        return dangerousLogService.findAll(); // DangerousLogService에서 모든 위험 로그 가져오기
    }


    // 검색 기능 추가
    @GetMapping("/dangerouslog/search")
    public String searchDangerousLogs(
            @RequestParam(name = "detection_number", required = false) Integer detection_number,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "port", required = false) Integer port,
            @RequestParam(name = "dangerous_number", required = false) Integer dangerous_number,
            Model model) {

        List<DangerousLog> logs = dangerousLogService.search(dangerous_number, ip, port,detection_number );
        model.addAttribute("logs", logs);
        return "dangerouslog"; // Thymeleaf 템플릿 이름 수정
    }
}
