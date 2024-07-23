package com.example.demo.dto.log;

public class PatternLog {
    private int id; // detection_number에서 id로 변경
    private String time; // 시간
    private String sIp; // 송신 IP
    private String dIp; // 수신 IP
    private int sPort; // 송신 포트
    private int dPort; // 수신 포트
    private int len; // 길이
    private String pattern1; // 패턴1
    private String pattern2; // 패턴2
    private String pattern3; // 패턴3
    private byte[] packet; // 패킷
    private int actionType; // 액션 타입
    private String policy_name; // 위험 로그 ID
    
    public PatternLog() {
    }

    public PatternLog(int id, String time, String sIp, String dIp, int sPort, int dPort, int len, String pattern1, String pattern2, String pattern3, byte[] packet, int actionType,String policy_name) {
        this.id = id;
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
        this.actionType = actionType;
 
        this.policy_name = policy_name;
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

    public byte[] getPacket() {
        return packet;
    }

    public void setPacket(byte[] packet) {
        this.packet = packet;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "PatternLog{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", sIp='" + sIp + '\'' +
                ", dIp='" + dIp + '\'' +
                ", sPort=" + sPort +
                ", dPort=" + dPort +
                ", len=" + len +
                ", pattern1='" + pattern1 + '\'' +
                ", pattern2='" + pattern2 + '\'' +
                ", pattern3='" + pattern3 + '\'' +
                ", packet='" + (packet != null ? new String(packet) : null) + '\'' +
                ", actionType=" + actionType +
                '}';
    }

	public String getPolicy_name() {
		return policy_name;
	}

	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}
}