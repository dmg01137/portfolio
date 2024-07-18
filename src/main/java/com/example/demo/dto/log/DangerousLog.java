package com.example.demo.dto.log;

public class DangerousLog {
	   private int dangerous_number; // 위험 번호 (formerly policy_number)
	
    private String ip; // IP 주소
    private int port; // 포트 번호
    private int detection_number; // 위험 로그 ID

    public DangerousLog() {
    }

    // IP, 포트, 위험 번호를 입력받는 생성자
    public DangerousLog(String ip, int port, int detection_number) {
        this.ip = ip;
        this.port = port;
        this.detection_number = detection_number; // 필드명 변경
    }

    public int getdetection_number() {
        return detection_number;
    }

    public void setdetection_number(int detection_number) {
        this.detection_number = detection_number;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getdangerous_number() {
        return dangerous_number; // 메서드 이름과 필드명을 일치시킴
    }

    public int setdangerous_number(int dangerous_number) {
        return this.dangerous_number = dangerous_number;
    }

    @Override
    public String toString() {
        return "DangerousLog{" +
                "detection_number=" + detection_number +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", dangerous_number='" + dangerous_number + '\'' + // 필드명 변경
                '}';
    }
}
