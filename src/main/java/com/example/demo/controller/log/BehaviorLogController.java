package com.example.demo.controller.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/behaviorlog")
    public String showBehaviorLogPage(Model model) {
        List<BehaviorLog> logs = behaviorLogService.findAll(getTableName());
        model.addAttribute("logs", logs);
        return "behaviorlog";
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
    @GetMapping("/api/behaviorlogs")
    @ResponseBody
    public ResponseEntity<List<BehaviorLog>> getAllBehaviorLogs() {
        List<BehaviorLog> logs = behaviorLogService.findAll(getTableName());
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/behaviorlog/search")
    public String searchBehaviorLogs(
            @RequestParam(name = "date", required = false) LocalDateTime date,
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam(name = "time", required = false) LocalDateTime time,
            @RequestParam(name = "s_ip", required = false) String s_ip,
            @RequestParam(name = "s_port", required = false) Integer s_port,
            @RequestParam(name = "d_ip", required = false) String d_ip,
            @RequestParam(name = "d_port", required = false) Integer d_port,
            @RequestParam(name = "action_type", required = false) Integer action_type,
            @RequestParam(name = "len", required = false) Integer len,
            @RequestParam(name = "base_cnt", required = false) Integer base_cnt,
            @RequestParam(name = "base_time", required = false) Integer base_time,
            @RequestParam(name = "pattern1", required = false) String pattern1,
            @RequestParam(name = "pattern2", required = false) String pattern2,
            @RequestParam(name = "pattern3", required = false) String pattern3,
            @RequestParam(name = "packet", required = false) String packet,
            Model model) {
        try {
            List<BehaviorLog> logs = behaviorLogService.search(date, id, time, s_ip, d_ip, s_port, d_port, action_type,
                    len, base_cnt, base_time, pattern1, pattern2, pattern3, packet, getTableName());
            model.addAttribute("logs", logs);
            return "behaviorlog";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve behavior logs");
            return "error";
        }
    }

    @GetMapping("/behaviorlog/multipleSearch")
    @ResponseBody
    public ResponseEntity<List<BehaviorLog>> searchBehaviorLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            List<BehaviorLog> behaviorLogs = behaviorLogService.findByMultipleCriteria(params, getTableName());
            return ResponseEntity.ok(behaviorLogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String getTableName() {
        return "behavior_log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
