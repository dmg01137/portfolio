package com.example.demo.dto.log;

public class DangerousLog {
    private int dangerous_number; // 위험 번호
    private String ip; // IP 주소
    private int port; // 포트 번호
    private int detection_number; // 위험 로그 ID

    public DangerousLog() {
    }

    // IP, 포트, 위험 번호를 입력받는 생성자
    public DangerousLog(String ip, int port, int detection_number) {
        this.ip = ip;
        this.port = port;
        this.detection_number = detection_number;
    }

    public int getDangerous_number() {
        return dangerous_number;
    }

    public void setDangerous_number(int dangerous_number) {
        this.dangerous_number = dangerous_number;
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

    public int getDetection_number() {
        return detection_number;
    }

    public void setDetection_number(int detection_number) {
        this.detection_number = detection_number;
    }

    @Override
    public String toString() {
        return "DangerousLog{" +
                "dangerous_number=" + dangerous_number +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", detection_number=" + detection_number +
                '}';
    }
}
