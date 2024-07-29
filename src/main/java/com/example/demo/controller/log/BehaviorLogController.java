package com.example.demo.controller.log;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils; // StringUtils import 추가
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.detection.Users;
import com.example.demo.dto.log.BehaviorLog;
import com.example.demo.dto.log.DangerousLog;
import com.example.demo.service.log.BehaviorLogService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BehaviorLogController {

    @Autowired
    private BehaviorLogService behaviorLogService;


    // Get pattern packet details
    @GetMapping("/behaviorlog/packet")
    public ResponseEntity<?> getPatternPacket(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "time", required = false) String time,
            @RequestParam(name = "sIp", required = false) String sIp,
            @RequestParam(name = "sPort", required = false) Integer sPort,
            @RequestParam(name = "dIp", required = false) String dIp,
            @RequestParam(name = "dPort", required = false) Integer dPort,
            @RequestParam(name = "len", required = false) Integer len,
            @RequestParam(name = "encodedPacket", required = false) String encodedPacket) {
        try {
            // Implement your logic to retrieve detailed pattern packet information
            return ResponseEntity.ok("Received parameters: id=" + id + ", time=" + time + ", sIp=" + sIp + ", sPort=" + sPort
                    + ", dIp=" + dIp + ", dPort=" + dPort + ", len=" + len + ", encodedPacket=" + encodedPacket);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
       
    }
  
    // 페이지: 모든 위험 로그 보기
    @GetMapping("/behaviorlog")
    public String showBehaviorLogPage(Model model,HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보 가져오기
        Users user = (Users) session.getAttribute("user");

        // 세션이 없거나 사용자 정보가 없으면 로그인 폼으로 리다이렉트
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
            return "redirect:/signin";
        } 

        List<BehaviorLog> logs = behaviorLogService.findAll(null);
        model.addAttribute("logs", logs);
        return "behaviorlog"; // Thymeleaf 템플릿 이름
    }

    // API 엔드포인트: 모든 위험 로그 가져오기
    @GetMapping("/api/behaviorlogs")
    @ResponseBody
    public ResponseEntity<List<BehaviorLog>> getAllBehaviorLogs() {
        try {
            List<BehaviorLog> logs = behaviorLogService.findAll(null);
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

    // 다중 조건으로 위험 로그 조회 (Thymeleaf 템플릿)
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
            List<BehaviorLog> logs = behaviorLogService.search(id, time, s_ip, d_ip, s_port, d_port, action_type, len, base_cnt, base_time, pattern1, pattern2, pattern3, packet, policy_name);
            model.addAttribute("logs", logs);
            return "behaviorlog";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to retrieve behavior logs");
            return "error";
        }
    }

    // 다중 조건으로 위험 로그 조회 (API)
    @GetMapping("/behaviorlog/multipleSearch")
    @ResponseBody
    public ResponseEntity<List<BehaviorLog>> searchBehaviorLogsFromDB(@RequestParam Map<String, String> params) {
        try {
            // 비어 있지 않은 파라미터만 필터링하여 검색 조건으로 사용
            Map<String, Object> searchParams = new HashMap<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (StringUtils.hasText(entry.getValue())) {
                    searchParams.put(entry.getKey(), entry.getValue());
                }
            }
            List<BehaviorLog> behaviorLogs = behaviorLogService.findByMultipleCriteria(null, searchParams);
            return ResponseEntity.ok(behaviorLogs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
