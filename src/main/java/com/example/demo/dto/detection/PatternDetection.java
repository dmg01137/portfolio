package com.example.demo.dto.detection;

import java.time.LocalDateTime;

public class PatternDetection {

    private Long detectionNumber;    // 탐지 번호
    private String sIp;             // 소스 IP
    private String dIp;             // 목적지 IP
    private Integer sPort;          // 소스 포트
    private Integer dPort;          // 목적지 포트
    private LocalDateTime createDt; // 생성 일시
    private LocalDateTime modifyDt; // 수정 일시
    private Integer actionType;     // 동작 유형
    private String policyName;      // 정책 이름
    private String policyInfo;      // 정책 정보
    private String pattern1;        // 패턴 1
    private String pattern2;        // 패턴 2
    private String pattern3;        // 패턴 3
    private Integer dangerous;      // 위험도
    private String regexp1;         // 정규 표현식 1
    private String regexp2;         // 정규 표현식 2
    private String regexp3;         // 정규 표현식 3
    // 생성자
    public PatternDetection() {
    }

    public PatternDetection(Long detectionNumber, String sIp, String dIp, Integer sPort, Integer dPort,
                            LocalDateTime createDt, LocalDateTime modifyDt, Integer actionType, String policyName,
                            String policyInfo, String pattern1, String pattern2, String pattern3, Integer dangerous,
                            String regexp1, String regexp2, String regexp3) {
        this.detectionNumber = detectionNumber;
        this.sIp = sIp;
        this.dIp = dIp;
        this.sPort = sPort;
        this.dPort = dPort;
        this.createDt = createDt;
        this.modifyDt = modifyDt;
        this.actionType = actionType;
        this.policyName = policyName;
        this.policyInfo = policyInfo;
        this.pattern1 = pattern1;
        this.pattern2 = pattern2;
        this.pattern3 = pattern3;
        this.dangerous = dangerous;
        this.regexp1 = regexp1;
        this.regexp2 = regexp2;
        this.regexp3 = regexp3;
    }

    // Getter 및 Setter 메소드
    public Long getDetectionNumber() {
        return detectionNumber;
    }

    public void setDetectionNumber(Long detectionNumber) {
        this.detectionNumber = detectionNumber;
    }

    public String getsIp() {
        return sIp;
    }

    public void setsIp(String sIp) {
        this.sIp = sIp;
    }

    public String getdIp() {
        return dIp;
    }

    public void setdIp(String dIp) {
        this.dIp = dIp;
    }

    public Integer getsPort() {
        return sPort;
    }

    public void setsPort(Integer sPort) {
        this.sPort = sPort;
    }

    public Integer getdPort() {
        return dPort;
    }

    public void setdPort(Integer dPort) {
        this.dPort = dPort;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getModifyDt() {
        return modifyDt;
    }

    public void setModifyDt(LocalDateTime modifyDt) {
        this.modifyDt = modifyDt;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyInfo() {
        return policyInfo;
    }

    public void setPolicyInfo(String policyInfo) {
        this.policyInfo = policyInfo;
    }

    public String getPattern1() {
        return pattern1;
    }

    public void setPattern1(String pattern1) {
        this.pattern1 = pattern1;
    }

    public String getPattern2() {
        return pattern2;
    }

    public void setPattern2(String pattern2) {
        this.pattern2 = pattern2;
    }

    public String getPattern3() {
        return pattern3;
    }

    public void setPattern3(String pattern3) {
        this.pattern3 = pattern3;
    }

    public Integer getDangerous() {
        return dangerous;
    }

    public void setDangerous(Integer dangerous) {
        this.dangerous = dangerous;
    }

    public String getRegexp1() {
        return regexp1;
    }

    public void setRegexp1(String regexp1) {
        this.regexp1 = regexp1;
    }

    public String getRegexp2() {
        return regexp2;
    }

    public void setRegexp2(String regexp2) {
        this.regexp2 = regexp2;
    }

    public String getRegexp3() {
        return regexp3;
    }

    public void setRegexp3(String regexp3) {
        this.regexp3 = regexp3;
    }

    @Override
    public String toString() {
        return "PatternDetection{" +
                "detectionNumber=" + detectionNumber +
                ", sIp='" + sIp + '\'' +
                ", dIp='" + dIp + '\'' +
                ", sPort=" + sPort +
                ", dPort=" + dPort +
                ", createDt=" + createDt +
                ", modifyDt=" + modifyDt +
                ", actionType=" + actionType +
                ", policyName='" + policyName + '\'' +
                ", policyInfo='" + policyInfo + '\'' +
                ", pattern1='" + pattern1 + '\'' +
                ", pattern2='" + pattern2 + '\'' +
                ", pattern3='" + pattern3 + '\'' +
                ", dangerous=" + dangerous +
                ", regexp1='" + regexp1 + '\'' +
                ", regexp2='" + regexp2 + '\'' +
                ", regexp3='" + regexp3 + '\'' +
                '}';
    }
}
