package com.example.demo.controller.policy;

import java.time.LocalDateTime;

public class SearchCriteria {
    private Long detection_number;
    private String s_to_ip;
    private String s_ip;
    private String d_ip;
    private Integer s_port;
    private Integer d_port;
    private LocalDateTime create_dt;
    private LocalDateTime modify_dt;
    private String action_type;
    private String policy_name;
    private String policy_info;
    private String pattern_1;
    private String pattern_2;
    private String pattern_3;
    private Boolean dangerous;

    // Constructor
    public SearchCriteria() {
    }

    // Getters and Setters
    public Long getDetection_number() {
        return detection_number;
    }

    public void setDetection_number(Long detection_number) {
        this.detection_number = detection_number;
    }

    public String getS_to_ip() {
        return s_to_ip;
    }

    public void setS_to_ip(String s_to_ip) {
        this.s_to_ip = s_to_ip;
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

    public Integer getS_port() {
        return s_port;
    }

    public void setS_port(Integer s_port) {
        this.s_port = s_port;
    }

    public Integer getD_port() {
        return d_port;
    }

    public void setD_port(Integer d_port) {
        this.d_port = d_port;
    }

    public LocalDateTime getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(LocalDateTime create_dt) {
        this.create_dt = create_dt;
    }

    public LocalDateTime getModify_dt() {
        return modify_dt;
    }

    public void setModify_dt(LocalDateTime modify_dt) {
        this.modify_dt = modify_dt;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public String getPolicy_info() {
        return policy_info;
    }

    public void setPolicy_info(String policy_info) {
        this.policy_info = policy_info;
    }

    public String getPattern_1() {
        return pattern_1;
    }

    public void setPattern_1(String pattern_1) {
        this.pattern_1 = pattern_1;
    }

    public String getPattern_2() {
        return pattern_2;
    }

    public void setPattern_2(String pattern_2) {
        this.pattern_2 = pattern_2;
    }

    public String getPattern_3() {
        return pattern_3;
    }

    public void setPattern_3(String pattern_3) {
        this.pattern_3 = pattern_3;
    }

    public Boolean getDangerous() {
        return dangerous;
    }

    public void setDangerous(Boolean dangerous) {
        this.dangerous = dangerous;
    }

    // toString() method
    @Override
    public String toString() {
        return "SearchCriteria{" +
                "detection_number=" + detection_number +
                ", s_to_ip='" + s_to_ip + '\'' +
                ", s_ip='" + s_ip + '\'' +
                ", d_ip='" + d_ip + '\'' +
                ", s_port=" + s_port +
                ", d_port=" + d_port +
                ", create_dt=" + create_dt +
                ", modify_dt=" + modify_dt +
                ", action_type='" + action_type + '\'' +
                ", policy_name='" + policy_name + '\'' +
                ", policy_info='" + policy_info + '\'' +
                ", pattern_1='" + pattern_1 + '\'' +
                ", pattern_2='" + pattern_2 + '\'' +
                ", pattern_3='" + pattern_3 + '\'' +
                ", dangerous=" + dangerous +
                '}';
    }
}
