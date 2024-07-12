package com.example.demo.dao.detection;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.detection.Users;

@Mapper
public interface UsersDAO {

    // 모든 사용자 정보를 가져오는 메서드
    List<Users> getAllUsers();
    
    // 주어진 사용자 ID에 해당하는 사용자 정보를 가져오는 메서드
    Users getUserById(@Param("user_id") String userId);

    // 사용자 정보를 추가하는 메서드
    int addUser(Users users);

    // 주어진 사용자 ID와 비밀번호에 해당하는 사용자 정보를 가져오는 메서드
    Users getUserByIdAndPw(@Param("user_id") String userId, @Param("password") String password);

    // 주어진 사용자 ID에 해당하는 사용자를 삭제하는 메서드
    int deleteUserById(@Param("user_id") String userId);

    // 사용자 정보를 업데이트하는 메서드
    int updateUser(Users users);

}