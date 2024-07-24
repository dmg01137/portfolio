package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PatternPolicyDto 
{
	private int enable_disable;		  // 정책 활성화 여부 0 : 비활성화, 1 : 활성화
    private int detection_number;     // 정책 번호
    private String s_to_ip;           // 출발지 범위 IP
    private String s_ip;              // 출발지 IP
    private String d_ip;              // 목적지 IP
    private String s_to_port;         // 출발지 범위 PORT
    private int s_port;         	  // 출발지 포트
    private int d_port;          	  // 목적지 포트
    private LocalDateTime create_dt;  // 생성 일시
    private LocalDateTime modify_dt;  // 수정 일시
    private int action_type;     	  // 동작 유형 0 : 로깅, 1: 로깅 + 차단
    private String policy_name;       // 정책 이름
    private String policy_info;       // 정책 정보
    private String pattern_1;         // 패턴 1
    private String pattern_2;         // 패턴 2
    private String pattern_3;         // 패턴 3
    private int dangerous;        	  // 위험도
}
