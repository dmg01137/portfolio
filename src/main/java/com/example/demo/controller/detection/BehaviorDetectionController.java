package com.example.demo.controller.detection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.log.SearchCriteria;
import com.example.demo.dto.detection.BehaviorDetection; // 변경된 클래스 이름
import com.example.demo.service.detection.BehaviorDetectionService; // 변경된 클래스 이름

@Controller
public class BehaviorDetectionController { // 클래스 이름 변경

    private final BehaviorDetectionService behaviorDetectionService; // 변경된 클래스 이름

    @Autowired
    public BehaviorDetectionController(BehaviorDetectionService behaviorDetectionService) { // 변경된 클래스 이름
        this.behaviorDetectionService = behaviorDetectionService;
    }

    @GetMapping("/policylist2") // 경로 변경
    public String showBehaviorList(Model model) { // 메소드 이름 변경 및 모델에 사용할 클래스 타입 변경
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.getAllBehaviorDetections(); // 변경된 클래스 이름
        model.addAttribute("behaviorDetections", behaviorDetections); // 변경된 클래스 이름
        return "policylist2"; // policylist2.html 템플릿을 반환
    }

    @PostMapping("/api/search-behavior-detections") // 경로 변경
    @ResponseBody
    public List<BehaviorDetection> searchBehaviorDetections(@RequestBody BehaviorDetection searchCriteria) { // 변경된 클래스 이름
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.searchBehaviorDetections(searchCriteria); // 변경된 클래스 이름
        return behaviorDetections;
    }

    @GetMapping("/api/behavior-detections-by-name/{name}") // 경로 변경
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionByName(@PathVariable String name) { // 변경된 클래스 이름
        return behaviorDetectionService.getBehaviorDetectionsByName(name); // 변경된 클래스 이름
    }

    @GetMapping("/api/behavior-detections-paged") // 경로 변경
    @ResponseBody
    public List<BehaviorDetection> getAllBehaviorDetectionsPaged(@RequestParam("page") int page, @RequestParam("size") int size) { // 변경된 클래스 이름
        return behaviorDetectionService.getAllBehaviorDetectionsPaged(page, size); // 변경된 클래스 이름
    }

    @GetMapping("/api/behavior-detections-by-time-range") // 경로 변경
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionsByTimeRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                    @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) { // 변경된 클래스 이름
        return behaviorDetectionService.getBehaviorDetectionsByTimeRange(start, end); // 변경된 클래스 이름
    }
}
