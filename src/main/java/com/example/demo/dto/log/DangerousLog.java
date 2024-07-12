package com.example.demo.dto.log;

public class DangerousLog {
    private int detection_number; // 위험 로그 ID
    private String ip; // IP 주소
    private int port; // 포트 번호
    private String policyNumber; // 정책 번호

    public DangerousLog() {
    }

    // IP, 포트, 정책 번호를 입력받는 생성자
    public DangerousLog(String ip, int port, String policyNumber) {
        this.ip = ip;
        this.port = port;
        this.policyNumber = policyNumber;
    }

    public int getId() {
        return detection_number;
    }

    public void setId(int detection_number) {
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

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    @Override
    public String toString() {
        return "DangerousLog{" +
                "detection_number=" +detection_number +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", policyNumber='" + policyNumber + '\'' +
                '}';
    }
}
