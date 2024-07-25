package com.example.demo.dao.detection;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.BehaviorPolicyDto;

@Mapper
public interface BehaviorPolicyDao 
{
	// 패턴 정책 조회
	public List<BehaviorPolicyDto> listBehaviorPolicy();
	
	// 패턴 정책 추가
	public int addBehaviorPolicy(BehaviorPolicyDto behaviorPolicyDto);
	
	// 해당하는 정책 번호 찾기
	public BehaviorPolicyDto findByDetectionNumber(int detection_number);
	
	// 패턴 정책 수정
	public int updateBehaviorPolicy(BehaviorPolicyDto behaviorPolicyDto);
	
	// 패턴 활성화/비활성화
	public int clickBehaviorPolicy(int detection_number);
	
	// 정책 마지막 번호 가져오기 
	public int getLastDetectionNumber();
	 
	// 행동 정책 검색
    public List<BehaviorPolicyDto> searchBehaviorPolicy(
            @Param("detection_number") Integer detectionNumber,
            @Param("s_to_ip") String sToIp,
            @Param("s_ip") String sIp,
            @Param("d_ip") String dIp,
            @Param("s_to_port") String sToPort,
            @Param("s_port") Integer sPort,
            @Param("d_port") Integer dPort,
            @Param("create_dt_from") LocalDateTime createDtFrom,
            @Param("create_dt_to") LocalDateTime createDtTo,
            @Param("modify_dt_from") LocalDateTime modifyDtFrom,
            @Param("modify_dt_to") LocalDateTime modifyDtTo,
            @Param("action_type") Integer actionType,
            @Param("policy_name") String policyName,
            @Param("policy_info") String policyInfo,
            @Param("base_cnt") Integer baseCnt,
            @Param("base_time") Integer baseTime,
            @Param("pattern_1") String pattern1,
            @Param("pattern_2") String pattern2,
            @Param("pattern_3") String pattern3,
            @Param("dangerous") Integer dangerous);
	
}
