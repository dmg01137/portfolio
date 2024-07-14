package com.example.demo.controller.detection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.detection.BehaviorDetection;
import com.example.demo.service.UDPClient;
import com.example.demo.service.detection.BehaviorDetectionService;

import jakarta.validation.Valid;
@Controller
public class BehaviorDetectionController {

    private final BehaviorDetectionService behaviorDetectionService;
    private final UDPClient udpClient; // UDPClient 인스턴스 추가

    @Autowired
    public BehaviorDetectionController(BehaviorDetectionService behaviorDetectionService, UDPClient udpClient) {
        this.behaviorDetectionService = behaviorDetectionService;
        this.udpClient = udpClient; // UDPClient 의존성 주입
    }
    
    @GetMapping("/addpolicy2")
    public String addpolicy2(Model model) {
       
        return "addpolicy2";
    }
    // "/behaviorpolicy" 엔드포인트로 GET 요청을 받아 정책 목록 페이지를 보여줍니다.
    @GetMapping("/behaviorpolicy")
    public String showPolicyList2(Model model) {
        // 서비스를 통해 모든 행동 감지 데이터를 가져옵니다.
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.getAllBehaviorDetections();
        // 모델에 behaviorDetections 속성으로 추가하여 뷰로 전달합니다.
        model.addAttribute("behaviorDetections", behaviorDetections);
        // behaviorpolicy.html을 렌더링하여 클라이언트에게 반환합니다.
        return "behaviorpolicy";
    }

    // "/api/search-behavior-detections" 엔드포인트로 POST 요청을 받아 행동 감지 데이터를 검색합니다.
    @PostMapping("/api/search-behavior-detections")
    @ResponseBody
    public List<BehaviorDetection> searchBehaviorDetections(@RequestBody BehaviorDetection behaviorDetection) {
        // 주어진 검색 조건에 따라 행동 감지 데이터를 검색하여 반환합니다.
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.searchBehaviorDetections(behaviorDetection);
        return behaviorDetections;
    }

    // "/api/behavior-detections-by-name/{name}" 엔드포인트로 GET 요청을 받아 특정 이름의 행동 감지 데이터를 조회합니다.
    @GetMapping("/api/behavior-detections-by-name/{name}")
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionByName(@PathVariable String name) {
        // 주어진 이름에 해당하는 행동 감지 데이터를 조회하여 반환합니다.
        return behaviorDetectionService.getBehaviorDetectionsByName(name);
    }

    // "/api/behavior-detections-paged" 엔드포인트로 GET 요청을 받아 페이징 처리된 행동 감지 데이터를 조회합니다.
    @GetMapping("/api/behavior-detections-paged")
    @ResponseBody
    public List<BehaviorDetection> getAllBehaviorDetectionsPaged(@RequestParam Map<String, Integer> params) {
        // 주어진 파라미터를 기반으로 페이징 처리된 행동 감지 데이터를 조회하여 반환합니다.
        return behaviorDetectionService.getAllBehaviorDetectionsPaged(params);
    }

    // "/api/behavior-detections-by-time-range" 엔드포인트로 GET 요청을 받아 시간 범위에 따른 행동 감지 데이터를 조회합니다.
    @GetMapping("/api/behavior-detections-by-time-range")
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionsByTimeRange(@RequestParam Map<String, LocalDateTime> params) {
        // 주어진 시간 범위에 해당하는 행동 감지 데이터를 조회하여 반환합니다.
        return behaviorDetectionService.getBehaviorDetectionsByTimeRange(params);
    }
    
    
    
//추가
    @GetMapping("/addbehaviorpolicy")
    public String showAddBehaviorForm(Model model) {
        model.addAttribute("behaviorDetection", new BehaviorDetection());
        return "addbehaviorpolicy";
    }

    @PostMapping("/addbehaviorpolicy")
    public String addBehavior(@ModelAttribute("behaviorDetection") @Valid BehaviorDetection behaviorDetection) {
        try {
            behaviorDetectionService.addBehaviorDetection(behaviorDetection);
            
            // 행동 변경 메시지를 전송합니다.
            udpClient.sendBehaviorChangeMessage();
            
            return "redirect:/policylist";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

}
