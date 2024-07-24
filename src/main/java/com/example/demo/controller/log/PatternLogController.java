package com.example.demo.controller.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.log.PatternLog;
import com.example.demo.service.log.PatternLogService;

@RestController
@RequestMapping("/api/patternlogs")
public class PatternLogController {

    private final PatternLogService patternLogService;

    @Autowired
    public PatternLogController(PatternLogService patternLogService) {
        this.patternLogService = patternLogService;
    }

 // 웹 페이지: 모든 패턴 로그 조회
    @GetMapping("/patternlog")
    public String showPatternLogPage(Model model) {
        List<PatternLog> logs = patternLogService.findAll(null);
        model.addAttribute("logs", logs);
        return "patternlog"; // Assuming "patternlog" is your Thymeleaf template name
    }

    // API endpoint: Get all pattern logs
    @GetMapping
    public ResponseEntity<List<PatternLog>> getAllPatternLogs() {
        List<PatternLog> logs = patternLogService.findAll(null);
        return ResponseEntity.ok(logs);
    }

    // Get a specific pattern log by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatternLog> getPatternLogById(@PathVariable int id) {
        try {
            PatternLog patternLog = patternLogService.getPatternLogById(id);
            if (patternLog != null) {
                return ResponseEntity.ok(patternLog);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

  
    // Get top 5 s_ip values
    @GetMapping("/top-sips")
    public ResponseEntity<List<Map<String, Object>>> getTopSIPs() {
        try {
            List<Map<String, Object>> topSIPs = patternLogService.findTopSIPs();
            return ResponseEntity.ok(topSIPs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get top 3 pattern combinations
    @GetMapping("/top-pattern-combinations")
    public ResponseEntity<List<Map<String, Object>>> getTopPatternCombinations() {
        try {
            List<Map<String, Object>> topPatternCombinations = patternLogService.findTopPatternCombinations();
            return ResponseEntity.ok(topPatternCombinations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all pattern logs ordered by time descending
    @GetMapping("/ordered-by-time")
    public ResponseEntity<List<PatternLog>> getAllPatternLogsOrderedByTimeDesc() {
        try {
            List<PatternLog> patternLogs = patternLogService.findAllOrderedByTimeDesc();
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Search pattern logs with multiple criteria

@GetMapping("/search")
public String searchPatternLogs(
        @RequestParam(name = "id", required = false) Integer id,
        @RequestParam(name = "time", required = false) String time,
        @RequestParam(name = "sIp", required = false) String sIp,
        @RequestParam(name = "dIp", required = false) String dIp,
        @RequestParam(name = "sPort", required = false) Integer sPort,
        @RequestParam(name = "dPort", required = false) Integer dPort,
        @RequestParam(name = "len", required = false) Integer len,
        @RequestParam(name = "pattern1", required = false) String pattern1,
        @RequestParam(name = "pattern2", required = false) String pattern2,
        @RequestParam(name = "pattern3", required = false) String pattern3,
        @RequestParam(name = "policy_name", required = false) String policy_name,
        @RequestParam(name = "actionType", required = false) Integer actionType,
        @RequestParam(name = "packet", required = false) byte[] packet,
        Model model) {
    try {
        List<PatternLog> logs = patternLogService.search(id, time, sIp, dIp, sPort, dPort, len, pattern1, pattern2, pattern3, packet, actionType,policy_name);
        model.addAttribute("logs", logs);
        return "patternlog"; // Thymeleaf template name
    } catch (Exception e) {
        // Log exception and redirect to error page
        e.printStackTrace();
        model.addAttribute("error", "Failed to retrieve pattern logs");
        return "error"; // Thymeleaf template name for error page
    }
}

    // Search logs by multiple criteria (processed in the DB)
    @GetMapping("/patternlog/multipleSearch")
    public ResponseEntity<List<PatternLog>> searchPatternLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            // Exclude empty values from parameters and process valid search conditions only
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }

            List<PatternLog> patternLogs = patternLogService.findByMultipleCriteria(null, searchParams);
            return ResponseEntity.ok(patternLogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}