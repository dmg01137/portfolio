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

    // Page: View all dangerous logs
    @GetMapping("/dangerous")
    public String showDangerousLogPage(Model model) {
        List<DangerousLog> logs = dangerousLogService.findAll(null);
        model.addAttribute("logs", logs);
        return "dangerouslog"; // Thymeleaf template name
    }

    // API endpoint: Get all dangerous logs
    @GetMapping("/api/dangerouslogs")
    public ResponseEntity<List<DangerousLog>> getAllDangerousLogs() {
        List<DangerousLog> logs = dangerousLogService.findAll(null);
        return ResponseEntity.ok(logs);
    }

    // Search logs by specific parameters
    @GetMapping("/dangerouslog/search")
    public String searchDangerousLogs(
            @RequestParam(name = "dangerous_number", required = false) Integer dangerousNumber,
            @RequestParam(name = "ip", required = false) String ip,
            @RequestParam(name = "port", required = false) Integer port,
            @RequestParam(name = "policy_name", required = false) String policy_name,
            Model model) {
        try {
            List<DangerousLog> logs = dangerousLogService.search(dangerousNumber, ip, port, policy_name);
            model.addAttribute("logs", logs);
            return "dangerouslog"; // Thymeleaf template name
        } catch (Exception e) {
            // Log exception and redirect to error page
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve dangerous logs");
            return "error"; // Thymeleaf template name for error page
        }
    }

    // Search logs by multiple criteria (processed in the DB)
    @GetMapping("/dangerouslog/multipleSearch")
    public ResponseEntity<List<DangerousLog>> searchDangerousLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            // Exclude empty values from parameters and process valid search conditions only
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (!entry.getValue().isEmpty()) {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }

            List<DangerousLog> dangerousLogs = dangerousLogService.findByMultipleCriteria(null, searchParams);
            return ResponseEntity.ok(dangerousLogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
