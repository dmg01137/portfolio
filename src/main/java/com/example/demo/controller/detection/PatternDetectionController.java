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
import com.example.demo.service.detection.PatternDetectionService;

@Controller
public class PatternDetectionController {

    private final PatternDetectionService patternDetectionService;

    @Autowired
    public PatternDetectionController(PatternDetectionService patternDetectionService) {
        this.patternDetectionService = patternDetectionService;
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
        return "redirect:/policylist";
    
} catch (Exception e) {
    e.printStackTrace(); // 예외 내용을 콘솔에 출력
    // 예외 처리 로직 추가
    return "500"; // 예외 발생 시 보여줄 에러 페이지로 리다이렉트 또는 뷰 반환
}
    
    }
}
