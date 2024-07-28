package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.BehaviorPolicyDto;
import com.example.demo.service.BehaviorPolicyService;

@Controller
public class BehaviorPolicyController 
{
    private final BehaviorPolicyService behaviorPolicyService;

    public BehaviorPolicyController(BehaviorPolicyService behaviorPolicyService)
    {
        this.behaviorPolicyService = behaviorPolicyService;
    }
    
    
    // 행동 정책 리스트 출력
    @GetMapping("/listBehaviorPolicy")
    public String listBehaviorPolicy(Model model) 
    {
        List<BehaviorPolicyDto> listBehaviorPolicy = behaviorPolicyService.listBehaviorPolicy();
        model.addAttribute("listBehaviorPolicy", listBehaviorPolicy);
        return "/listBehaviorPolicy";
    }

    // 행동 정책 추가
	@GetMapping("/addBehaviorPolicy")
	public String addBehaviorPolicy(Model model) 
	{
		// primary key인 디텍션 넘버를 + 1해서 넘겨준다
		
		  int lastDetectionNumber = behaviorPolicyService.getLastDetectionNumber() + 1;
		  model.addAttribute("detectionNumber", lastDetectionNumber);
		  return "addBehaviorPolicy"; // 파일 경로 확인
	}
    
    
    // 행동 정책 추가 후 list 이동
	@PostMapping("/addBehaviorPolicy")
	public String addBehaviorPolicyList(BehaviorPolicyDto behaviorPolicyDto) 
	{
	    int newDetectionNumber = behaviorPolicyService.addBehaviorPolicy(behaviorPolicyDto);
	    if (newDetectionNumber > 0) {
	    	behaviorPolicyService.udpBehaviorPolicy();
	        return "redirect:/listBehaviorPolicy";
	    }
	    return "redirect:/listBehaviorPolicy";
	}
    
    // 행동 정책 수정
    @GetMapping("/updateBehaviorPolicy")
    public String updateBehaviorPolicy(@RequestParam("detection_number") int detectionNumber, Model model) 
    {
        BehaviorPolicyDto policy = behaviorPolicyService.findByDetectionNumber(detectionNumber);

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
        model.addAttribute("base_cnt", policy.getBase_cnt());
        model.addAttribute("base_time", policy.getBase_time());
        model.addAttribute("pattern_1", policy.getPattern_1());
        model.addAttribute("pattern_2", policy.getPattern_2());
        model.addAttribute("pattern_3", policy.getPattern_3());
        model.addAttribute("dangerous", policy.getDangerous());

        return "/updateBehaviorPolicy";
    }

    // 행동 정책 수정 후 list 이동
    @PostMapping("/updateBehaviorPolicy")
    public String updateBehaviorPolicyList(@ModelAttribute BehaviorPolicyDto behaviorPolicyDto) 
    {
        if(behaviorPolicyService.updateBehaviorPolicy(behaviorPolicyDto) > 0) 
        {
            behaviorPolicyService.udpBehaviorPolicy();
            return "redirect:/listBehaviorPolicy";
        }
        return "redirect:/listBehaviorPolicy";
    }

    // 행동 정책 활성화 / 비활성화
    @PostMapping("/clickBehaviorPolicy")
    @ResponseBody
    public Boolean clickBehaviorPolicy(@RequestBody BehaviorPolicyDto behaviorPolicyDto) 
    {
        try 
        {
            behaviorPolicyService.clickBehaviorPolicy(behaviorPolicyDto.getDetection_number());
            behaviorPolicyService.udpBehaviorPolicy();
            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
    
    // 행동 정책 검색하기
	@GetMapping("/searchBehaviorPolicy")
	public String searchBehaviorPolicy(
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
	        @RequestParam(value = "base_cnt", required = false) Integer baseCnt,
	        @RequestParam(value = "base_time", required = false) Integer baseTime,
	        @RequestParam(value = "pattern_1", required = false) String pattern1,
	        @RequestParam(value = "pattern_2", required = false) String pattern2,
	        @RequestParam(value = "pattern_3", required = false) String pattern3,
	        @RequestParam(value = "dangerous", required = false) Integer dangerous,
	        Model model) {
	    List<BehaviorPolicyDto> listBehaviorPolicy;
	    if (detectionNumber != null || sToIp != null || sIp != null || dIp != null || sToPort != null || sPort != null || dPort != null
	            || createDtFrom != null || createDtTo != null || modifyDtFrom != null || modifyDtTo != null || actionType != null || policyName != null
	            || policyInfo != null || baseCnt != null || baseTime != null || pattern1 != null || pattern2 != null || pattern3 != null || dangerous != null) {
	        listBehaviorPolicy = behaviorPolicyService.searchBehaviorPolicy(detectionNumber, sToIp, sIp, dIp, sToPort, sPort, dPort,
	                createDtFrom, createDtTo, modifyDtFrom, modifyDtTo, actionType, policyName, policyInfo, baseCnt, baseTime, pattern1, pattern2, pattern3, dangerous);
	    } else {
	        listBehaviorPolicy = behaviorPolicyService.listBehaviorPolicy();
	    }
	    model.addAttribute("listBehaviorPolicy", listBehaviorPolicy);
	    return "/listBehaviorPolicy";
	}
	
	@GetMapping("/detailsBehaviorPolicy")
    public String detailsBehaviorPolicy(@RequestParam("detection_number") int detectionNumber, Model model) 
    {
        BehaviorPolicyDto policy = behaviorPolicyService.findByDetectionNumber(detectionNumber);

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
        model.addAttribute("base_cnt", policy.getBase_cnt());
        model.addAttribute("base_time", policy.getBase_time());
        model.addAttribute("pattern_1", policy.getPattern_1());
        model.addAttribute("pattern_2", policy.getPattern_2());
        model.addAttribute("pattern_3", policy.getPattern_3());
        model.addAttribute("dangerous", policy.getDangerous());

        return "/detailsBehaviorPolicy";
    }
	
	
}
