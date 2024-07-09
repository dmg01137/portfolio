package com.example.demo.dto;

import java.time.LocalDateTime;

public class PatternDetection {

    private Long detectionNumber;
    private String sIp;
    private String dIp;
    private Integer sPort;
    private Integer dPort;
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;
    private Integer actionType;
    private String policyName;
    private String policyInfo;
    private String pattern1;
    private String pattern2;
    private String pattern3;
    private Integer dangers;

    // 생성자
    public PatternDetection() {
    }

    public PatternDetection(Long detectionNumber, String sIp, String dIp, Integer sPort, Integer dPort,
                            LocalDateTime createDt, LocalDateTime modifyDt, Integer actionType, String policyName,
                            String policyInfo, String pattern1, String pattern2, String pattern3, Integer dangers) {
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
        this.dangers = dangers;
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

    public Integer getDangers() {
        return dangers;
    }

    public void setDangers(Integer dangers) {
        this.dangers = dangers;
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
                ", dangers=" + dangers +
                '}';
    }
}
