package com.example.demo.dto.log;

public class PatternLog {
    private int detectionNumber;
    private String time;
    private String sIp;
    private String dIp;
    private int sPort;
    private int dPort;
    private int len;
    private String pattern1;
    private String pattern2;
    private String pattern3;
    private String packet;
    private String policyName;

    public PatternLog() {
    }

    public PatternLog(int detectionNumber, String time, String sIp, String dIp, int sPort, int dPort, int len, String pattern1, String pattern2, String pattern3, String packet, String policyName) {
        this.detectionNumber = detectionNumber;
        this.time = time;
        this.sIp = sIp;
        this.dIp = dIp;
        this.sPort = sPort;
        this.dPort = dPort;
        this.len = len;
        this.pattern1 = pattern1;
        this.pattern2 = pattern2;
        this.pattern3 = pattern3;
        this.packet = packet;
        this.policyName = policyName;
    }

    public int getDetectionNumber() {
        return detectionNumber;
    }

    public void setDetectionNumber(int detectionNumber) {
        this.detectionNumber = detectionNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getsPort() {
        return sPort;
    }

    public void setsPort(int sPort) {
        this.sPort = sPort;
    }

    public int getdPort() {
        return dPort;
    }

    public void setdPort(int dPort) {
        this.dPort = dPort;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
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

    public String getPacket() {
        return packet;
    }

    public void setPacket(String packet) {
        this.packet = packet;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    @Override
    public String toString() {
        return "PatternLog{" +
                "detectionNumber=" + detectionNumber +
                ", time='" + time + '\'' +
                ", sIp='" + sIp + '\'' +
                ", dIp='" + dIp + '\'' +
                ", sPort=" + sPort +
                ", dPort=" + dPort +
                ", len=" + len +
                ", pattern1='" + pattern1 + '\'' +
                ", pattern2='" + pattern2 + '\'' +
                ", pattern3='" + pattern3 + '\'' +
                ", packet='" + packet + '\'' +
                ", policyName='" + policyName + '\'' +
                '}';
    }
}
