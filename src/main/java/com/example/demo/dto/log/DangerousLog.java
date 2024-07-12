package com.example.demo.dto.log;

public class DangerousLog {
    private int detection_number; // 위험 로그 ID
    private String ip; // IP 주소
    private int port; // 포트 번호
    private String policy_number; // 정책 번호

    public DangerousLog() {
    }

    // IP, 포트, 정책 번호를 입력받는 생성자
    public DangerousLog(String ip, int port, String policyNumber) {
        this.ip = ip;
        this.port = port;
        this.policy_number = policyNumber; // 필드명 변경
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
        return policy_number; // 메서드 이름과 필드명을 일치시킴
    }

    public void setPolicyNumber(String policyNumber) {
        this.policy_number = policyNumber; // 필드명 변경
    }

    @Override
    public String toString() {
        return "DangerousLog{" +
                "detection_number=" +detection_number +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", policy_number='" + policy_number + '\'' + // 필드명 변경
                '}';
    }
}
