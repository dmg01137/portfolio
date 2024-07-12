package com.example.demo.dto.detection;

import java.time.LocalDateTime;

public class BehaviorDetection {

    private Integer detectionNumber;
    private String sIp;
    private String dIp;
    private Integer sPort;
    private Integer dPort;
    private LocalDateTime createDt;
    private LocalDateTime modifyDt;
    private String actionType;
    private String policyName;
    private String policyInfo;
    private String pattern1;
    private String pattern2;
    private String pattern3;
    private Integer dangerous;
    private String regexp1;
    private String regexp2;
    private String regexp3;
    private Integer baseCnt;
    private Integer baseTime;

    // Getter 및 Setter 메서드

    public Integer getDetectionNumber() {
        return detectionNumber;
    }

    public void setDetectionNumber(Integer detectionNumber) {
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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
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

    public Integer getBaseCnt() {
        return baseCnt;
    }

    public void setBaseCnt(Integer baseCnt) {
        this.baseCnt = baseCnt;
    }

    public Integer getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Integer baseTime) {
        this.baseTime = baseTime;
    }

    @Override
    public String toString() {
        return "BehaviorDetection{" +
                "detectionNumber=" + detectionNumber +
                ", sIp='" + sIp + '\'' +
                ", dIp='" + dIp + '\'' +
                ", sPort=" + sPort +
                ", dPort=" + dPort +
                ", createDt=" + createDt +
                ", modifyDt=" + modifyDt +
                ", actionType='" + actionType + '\'' +
                ", policyName='" + policyName + '\'' +
                ", policyInfo='" + policyInfo + '\'' +
                ", pattern1='" + pattern1 + '\'' +
                ", pattern2='" + pattern2 + '\'' +
                ", pattern3='" + pattern3 + '\'' +
                ", dangerous=" + dangerous +
                ", regexp1='" + regexp1 + '\'' +
                ", regexp2='" + regexp2 + '\'' +
                ", regexp3='" + regexp3 + '\'' +
                ", baseCnt=" + baseCnt +
                ", baseTime=" + baseTime +
                '}';
    }
}
