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

import com.example.demo.dto.detection.BehaviorDetection;
import com.example.demo.service.detection.BehaviorDetectionService;

@Controller
public class BehaviorDetectionController {

    private final BehaviorDetectionService behaviorDetectionService;

    @Autowired
    public BehaviorDetectionController(BehaviorDetectionService behaviorDetectionService) {
        this.behaviorDetectionService = behaviorDetectionService;
    }

    @GetMapping("/policylist2")
    public String showPolicyList2(Model model) {
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.getAllBehaviorDetections();
        model.addAttribute("behaviorDetections", behaviorDetections);
        return "policylist2"; // policylist.html 템플릿을 반환
    }

    @PostMapping("/api/search-behavior-detections")
    @ResponseBody
    public List<BehaviorDetection> searchBehaviorDetections(@RequestBody BehaviorDetection searchCriteria) {
        List<BehaviorDetection> behaviorDetections = behaviorDetectionService.searchBehaviorDetections(searchCriteria);
        return behaviorDetections;
    }

    @GetMapping("/api/behavior-detections-by-name/{name}")
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionByName(@PathVariable String name) {
        return behaviorDetectionService.getBehaviorDetectionsByName(name);
    }

    @GetMapping("/api/behavior-detections-paged")
    @ResponseBody
    public List<BehaviorDetection> getAllBehaviorDetectionsPaged(@RequestParam("page") int page, @RequestParam("size") int size) {
        return behaviorDetectionService.getAllBehaviorDetectionsPaged(page, size);
    }

    @GetMapping("/api/behavior-detections-by-time-range")
    @ResponseBody
    public List<BehaviorDetection> getBehaviorDetectionsByTimeRange(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return behaviorDetectionService.getBehaviorDetectionsByTimeRange(start, end);
    }
}
