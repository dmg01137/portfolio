package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UsersDAO;
import com.example.demo.dto.Users;

@Service
public class UsersService {
 private final UsersDAO usersDAO;

    @Autowired
    public UsersService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    // 사용자 삭제 메서드
    @Transactional
    public int deleteUserById(String userId) {
        return usersDAO.deleteUserById(userId);
    }

    // 사용자 아이디로 사용자 정보를 가져오는 메서드
    public Users getUserById(String userId) {
        return usersDAO.getUserById(userId);
    }

    // 사용자 아이디와 비밀번호로 사용자 정보를 가져오는 메서드
    public Users getUserByIdAndPw(String user_id, String password) {
        Users user = usersDAO.getUserById(user_id);
        // 사용자가 존재하고 비밀번호가 일치할 경우 사용자 정보 반환
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null; // 그 외의 경우 null 반환
    }

    // 사용자 추가 메서드
    @Transactional
    public int addUser(Users users) {
        return usersDAO.addUser(users);
    }
    // 사용자 전체 목록을 가져오는 메서드
    public List<Users> getAllUsers() {
        return usersDAO.getAllUsers();
    }
    // 사용자 정보 수정 메서드
    @Transactional
    public int updateUser(Users users) {
        return usersDAO.updateUser(users);
    }

}