package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.PatternPolicyDto;
import com.example.demo.service.PatternPolicyService;

@Controller
public class PatternPolicyController 
{
	private final PatternPolicyService patternPolicyService;

	public PatternPolicyController(PatternPolicyService patternPolicyService) 
	{
		this.patternPolicyService = patternPolicyService;
	}

	
	
	// 패턴 정책 리스트 출력
	@GetMapping("/listPatternPolicy")
	public String listPatternPolicy(Model model) 
	{
		List<PatternPolicyDto> listPatternPolicy = patternPolicyService.listPatternPolicy();
		model.addAttribute("listPatternPolicy", listPatternPolicy);
		return "/listPatternPolicy";
	}

	// 패턴 정책 추가
	@GetMapping("/addPatternPolicy")
	public String addPatternPolicy(Model model) 
	{
		// primary key인 디텍션 넘버를 + 1해서 넘겨준다
		
		  int lastDetectionNumber = patternPolicyService.getLastDetectionNumber() + 1;
		  model.addAttribute("detectionNumber", lastDetectionNumber);
		 
	    return "addPatternPolicy";
	}

	// 패턴 정책 추가후 list 이동
	@PostMapping("/addPatternPolicy")
	public String addPatternPolicyList(PatternPolicyDto patternPolicyDto) 
	{
	    int newDetectionNumber = patternPolicyService.addPatternPolicy(patternPolicyDto);
	    if (newDetectionNumber > 0) {
	        patternPolicyService.udpPatternPolicy();
	        return "redirect:/listPatternPolicy";
	    }
        return "redirect:/listPatternPolicy";
	}

	// 패턴 정책 수정
	@GetMapping("/updatePatternPolicy")
	public String updatePatternPolicy(@RequestParam("detection_number") int detectionNumber, Model model) 
	{
		PatternPolicyDto policy = patternPolicyService.findByDetectionNumber(detectionNumber);

		// 모델에 데이터 추가
		model.addAttribute("enable_disable", policy.getEnable_disable());
		model.addAttribute("detection_number", policy.getDetection_number());
		model.addAttribute("s_to_ip", policy.getS_to_ip());
		model.addAttribute("s_ip", policy.getS_ip());
		model.addAttribute("d_ip", policy.getD_ip());
		model.addAttribute("s_to_port", policy.getS_to_port());
		model.addAttribute("s_port", policy.getS_port());
		model.addAttribute("d_port", policy.getD_port());
		model.addAttribute("create_dt", policy.getCreate_dt());
		model.addAttribute("modify_dt", policy.getModify_dt());
		model.addAttribute("action_type", policy.getAction_type());
		model.addAttribute("policy_name", policy.getPolicy_name());
		model.addAttribute("policy_info", policy.getPolicy_info());
		model.addAttribute("pattern_1", policy.getPattern_1());
		model.addAttribute("pattern_2", policy.getPattern_2());
		model.addAttribute("pattern_3", policy.getPattern_3());
		model.addAttribute("dangerous", policy.getDangerous());

		return "/updatePatternPolicy";
	}

	// 패턴 정책 수정후 list 이동
	@PostMapping("/updatePatternPolicy")
	public String updatePatternPolicyList(@ModelAttribute PatternPolicyDto patternPolicyDto) 
	{
		if (patternPolicyService.updatePatternPolicy(patternPolicyDto) > 0) {
			patternPolicyService.udpPatternPolicy();
			return "redirect:/listPatternPolicy";
		}
		return "redirect:/listPatternPolicy";
	}

	// 패턴 정책 활성화/비활성화
	@PostMapping("/clickPatternPolicy")
	@ResponseBody
	public Boolean clickPatternPolicy(@RequestBody PatternPolicyDto patternPolicyDto) 
	{
		try 
		{
			patternPolicyService.clickPatternPolicy(patternPolicyDto.getDetection_number());
			patternPolicyService.udpPatternPolicy();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 패턴 정책 검색하기
	@GetMapping("/searchPatternPolicy")
	public String searchPatternPolicy(
	        @RequestParam(value = "detection_number", required = false) Integer detectionNumber,
	        @RequestParam(value = "s_to_ip", required = false) String sToIp,
	        @RequestParam(value = "s_ip", required = false) String sIp,
	        @RequestParam(value = "d_ip", required = false) String dIp,
	        @RequestParam(value = "s_to_port", required = false) String sToPort,
	        @RequestParam(value = "s_port", required = false) Integer sPort,
	        @RequestParam(value = "d_port", required = false) Integer dPort,
	        @RequestParam(value = "create_dt_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime createDtFrom,
	        @RequestParam(value = "create_dt_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime createDtTo,
	        @RequestParam(value = "modify_dt_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime modifyDtFrom,
	        @RequestParam(value = "modify_dt_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime modifyDtTo,
	        @RequestParam(value = "action_type", required = false) Integer actionType,
	        @RequestParam(value = "policy_name", required = false) String policyName,
	        @RequestParam(value = "policy_info", required = false) String policyInfo,
	        @RequestParam(value = "pattern_1", required = false) String pattern1,
	        @RequestParam(value = "pattern_2", required = false) String pattern2,
	        @RequestParam(value = "pattern_3", required = false) String pattern3,
	        @RequestParam(value = "dangerous", required = false) Integer dangerous,
	        Model model) {
	    List<PatternPolicyDto> listPatternPolicy;
	    if (detectionNumber != null || sToIp != null || sIp != null || dIp != null || sToPort != null || sPort != null || dPort != null
	            || createDtFrom != null || createDtTo != null || modifyDtFrom != null || modifyDtTo != null || actionType != null || policyName != null
	            || policyInfo != null || pattern1 != null || pattern2 != null || pattern3 != null || dangerous != null) {
	        listPatternPolicy = patternPolicyService.searchPatternPolicy(detectionNumber, sToIp, sIp, dIp, sToPort, sPort, dPort,
	                createDtFrom, createDtTo, modifyDtFrom, modifyDtTo, actionType, policyName, policyInfo, pattern1, pattern2, pattern3, dangerous);
	    } else {
	        listPatternPolicy = patternPolicyService.listPatternPolicy();
	    }
	    model.addAttribute("listPatternPolicy", listPatternPolicy);
	    return "/listPatternPolicy";
	}
	
	
	@GetMapping("/detailsPatternPolicy")
	public String detailsPatternPolicy(@RequestParam("detection_number") int detectionNumber, Model model) 
	{
	    PatternPolicyDto policy = patternPolicyService.findByDetectionNumber(detectionNumber);

	    // 모델에 데이터 추가
	    model.addAttribute("enable_disable", policy.getEnable_disable());
	    model.addAttribute("detection_number", policy.getDetection_number());
	    model.addAttribute("s_to_ip", policy.getS_to_ip());
	    model.addAttribute("s_ip", policy.getS_ip());
	    model.addAttribute("d_ip", policy.getD_ip());
	    model.addAttribute("s_to_port", policy.getS_to_port());
	    model.addAttribute("s_port", policy.getS_port());
	    model.addAttribute("d_port", policy.getD_port());
	    model.addAttribute("create_dt", policy.getCreate_dt());
	    model.addAttribute("modify_dt", policy.getModify_dt());
	    model.addAttribute("action_type", policy.getAction_type());
	    model.addAttribute("policy_name", policy.getPolicy_name());
	    model.addAttribute("policy_info", policy.getPolicy_info());
	    model.addAttribute("pattern_1", policy.getPattern_1());
	    model.addAttribute("pattern_2", policy.getPattern_2());
	    model.addAttribute("pattern_3", policy.getPattern_3());
	    model.addAttribute("dangerous", policy.getDangerous());

	    return "/detailsPatternPolicy";
	}


}
