package com.example.demo.dto.log;

import java.time.LocalDateTime;
import java.util.Arrays;

public class BehaviorLog {
    private int id;
    private LocalDateTime time;
    private String s_ip;
    private String d_ip;
    private int s_port;
    private int d_port;
    private int len;
    private String pattern1;
    private String pattern2;
    private String pattern3;
    private byte[] packet;
    private int base_cnt;
    private int base_time;
    private int action_type;
    private String policy_name;

    public BehaviorLog() {
        // 기본 생성자
    }

    public BehaviorLog(int id, LocalDateTime time, String s_ip, String d_ip, int s_port, int d_port,
                       int len, String pattern1, String pattern2, String pattern3, byte[] packet,
                       int base_cnt, int base_time, int action_type, String policy_name) {
        this.id = id;
        this.time = time;
        this.s_ip = s_ip;
        this.d_ip = d_ip;
        this.s_port = s_port;
        this.d_port = d_port;
        this.len = len;
        this.pattern1 = pattern1;
        this.pattern2 = pattern2;
        this.pattern3 = pattern3;
        this.packet = packet;
        this.base_cnt = base_cnt;
        this.base_time = base_time;
        this.action_type = action_type;
        this.policy_name = policy_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getS_ip() {
        return s_ip;
    }

    public void setS_ip(String s_ip) {
        this.s_ip = s_ip;
    }

    public String getD_ip() {
        return d_ip;
    }

    public void setD_ip(String d_ip) {
        this.d_ip = d_ip;
    }

    public int getS_port() {
        return s_port;
    }

    public void setS_port(int s_port) {
        this.s_port = s_port;
    }

    public int getD_port() {
        return d_port;
    }

    public void setD_port(int d_port) {
        this.d_port = d_port;
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

    public int getBase_cnt() {
        return base_cnt;
    }

    public void setBase_cnt(int base_cnt) {
        this.base_cnt = base_cnt;
    }

    public int getBase_time() {
        return base_time;
    }

    public void setBase_time(int base_time) {
        this.base_time = base_time;
    }

    public int getAction_type() {
        return action_type;
    }

    public void setAction_type(int action_type) {
        this.action_type = action_type;
    }

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    @Override
    public String toString() {
        return "BehaviorLog{" +
                "id=" + id +
                ", time=" + time +
                ", s_ip='" + s_ip + '\'' +
                ", d_ip='" + d_ip + '\'' +
                ", s_port=" + s_port +
                ", d_port=" + d_port +
                ", len=" + len +
                ", pattern1='" + pattern1 + '\'' +
                ", pattern2='" + pattern2 + '\'' +
                ", pattern3='" + pattern3 + '\'' +
                ", packet=" + Arrays.toString(packet) +
                ", base_cnt=" + base_cnt +
                ", base_time=" + base_time +
                ", action_type=" + action_type +
                ", policy_name='" + policy_name + '\'' +
                '}';
    }
}
