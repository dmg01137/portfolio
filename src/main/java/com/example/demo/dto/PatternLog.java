package com.example.demo.dto;

public class PatternLog {
    private int id;
    private String time;
    private String sIp;
    private String dIp;
    private int sPort;
    private int dPort;
    private int len;
    private String pattern;
    private String packet;
    private String policyName;

    public PatternLog() {
    }

    public PatternLog(int id, String time, String sIp, String dIp, int sPort, int dPort, int len, String pattern, String packet, String policyName) {
        this.id = id;
        this.time = time;
        this.sIp = sIp;
        this.dIp = dIp;
        this.sPort = sPort;
        this.dPort = dPort;
        this.len = len;
        this.pattern = pattern;
        this.packet = packet;
        this.policyName = policyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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
}
