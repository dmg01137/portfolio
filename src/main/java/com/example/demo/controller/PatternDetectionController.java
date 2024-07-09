package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.PatternDetection;
import com.example.demo.service.PatternDetectionService;

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
        return "policylist";
    }

    @PostMapping("/api/search-pattern-detections")
    @ResponseBody
    public List<PatternDetection> searchPatternDetections(@RequestBody SearchCriteria searchCriteria) {
        List<PatternDetection> patternDetections = patternDetectionService.searchPatternDetections(searchCriteria);
        return patternDetections;
    }
}
