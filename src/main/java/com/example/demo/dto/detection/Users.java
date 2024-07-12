package com.example.demo.dto.detection;

// 사용자 정보를 담는 DTO 클래스
public class Users {
    private String user_id;         // 사용자 ID
    private String password;        // 비밀번호
    private String user_privilege;  // 사용자 권한
    private String user_name;       // 사용자 이름

    // 사용자 ID getter
    public String getUser_id() {
        return user_id;
    }

    // 사용자 ID setter
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // 비밀번호 getter
    public String getPassword() {
        return password;
    }

    // 비밀번호 setter
    public void setPassword(String password) {
        this.password = password;
    }

    // 사용자 권한 getter
    public String getUser_privilege() {
        return user_privilege;
    }

    // 사용자 권한 setter
    public void setUser_privilege(String user_privilege) {
        this.user_privilege = user_privilege;
    }

    // 사용자 이름 getter
    public String getUser_name() {
        return user_name;
    }

    // 사용자 이름 setter
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}