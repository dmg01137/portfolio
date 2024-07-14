package com.example.demo.controller.detection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.log.SearchCriteria;
import com.example.demo.dto.detection.PatternDetection;
import com.example.demo.service.UDPClient;
import com.example.demo.service.detection.PatternDetectionService;

@Controller
public class PatternDetectionController {

    private final PatternDetectionService patternDetectionService;
    private final UDPClient udpClient; // UDPClient 인스턴스 추가


    @Autowired
    public PatternDetectionController(PatternDetectionService patternDetectionService, UDPClient udpClient) {
        this.patternDetectionService = patternDetectionService;
        this.udpClient = udpClient; // UDPClient 의존성 주입
    }

    @GetMapping("/policylist")
    public String showPolicyList(Model model) {
        List<PatternDetection> patternDetections = patternDetectionService.getAllPatternDetections();
        model.addAttribute("patternDetections", patternDetections);
        return "policylist"; // policylist.html 템플릿을 반환
    }

    
    @PostMapping("/api/search-pattern-detections")
    @ResponseBody
    public List<PatternDetection> searchPatternDetections(@RequestBody SearchCriteria searchCriteria) {
        List<PatternDetection> patternDetections = patternDetectionService.searchPatternDetections(searchCriteria);
        return patternDetections;
    }

    @GetMapping("/api/pattern-detections-by-name/{name}")
    @ResponseBody
    public List<PatternDetection> getPatternDetectionByName(@PathVariable String name) {
        return patternDetectionService.getPatternDetectionsByName(name);
    }

    @GetMapping("/api/pattern-detections-paged")
    @ResponseBody
    public List<PatternDetection> getAllPatternDetectionsPaged(@RequestParam("page") int page, @RequestParam("size") int size) {
        return patternDetectionService.getAllPatternDetectionsPaged(page, size);
    }

    @GetMapping("/api/pattern-detections-by-time-range")
    @ResponseBody
    public List<PatternDetection> getPatternDetectionsByTimeRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                   @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return patternDetectionService.getPatternDetectionsByTimeRange(start, end);
    }
    
    //추가
    
    @GetMapping("/addpolicy")
    public String showAddPatternForm(Model model) {
        model.addAttribute("patternDetection", new PatternDetection());
        return "addpolicy"; // addpolicy.html 템플릿을 반환
    }

    @PostMapping("/addpolicy")
    public String addPattern(@ModelAttribute("patternDetection") PatternDetection patternDetection) {
    	 try {
    		 patternDetectionService.addPatternDetection(patternDetection);
             // 행동 변경 메시지를 전송합니다.
             udpClient.sendPatternChangeMessage();
             
             return "redirect:/policylist";
         } catch (Exception e) {
             e.printStackTrace();
             return "500";
         }
    
    }
    
    
 //수정
    
    @GetMapping("/modifypolicy")
    public String showModifyPatternForm(Model model) {
        // 필요한 경우 모델에 추가적인 데이터를 넣어줄 수 있습니다.
        return "modifypolicy"; // modifypolicy.html 템플릿을 반환
    }

    @PostMapping("/modifypolicy")
    public String modifyPattern(@ModelAttribute("patternDetection") PatternDetection patternDetection) {
        try {
            // 패턴 수정 로직을 여기에 추가
            patternDetectionService.updatePatternDetection(patternDetection);
            // 행동 변경 메시지를 전송합니다.
            udpClient.sendPatternChangeMessage();
            
            return "redirect:/policylist";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

    
    
//삭제
    
    @GetMapping("/deletepolicy")
    public String showDeletePatternForm(Model model) {
        // 필요한 경우 모델에 추가적인 데이터를 넣어줄 수 있습니다.
        return "deletepolicy"; // deletepolicy.html 템플릿을 반환
    }

    @PostMapping("/deletepolicy")
    public String deletePattern(@ModelAttribute("patternDetection") PatternDetection patternDetection) {
        try {
            // 패턴 삭제 로직을 여기에 추가
            patternDetectionService.deletePatternDetection(patternDetection);
            // 행동 변경 메시지를 전송합니다.
            udpClient.sendPatternChangeMessage();
            
            return "redirect:/deletepolicy";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

}
