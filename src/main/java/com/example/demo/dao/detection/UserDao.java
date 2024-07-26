package com.example.demo.dao.detection;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserDto;

@Mapper
public interface UserDao 
{
	// 회원 추가 인터페이스 함수
	public int addUser(UserDto userDto);
	
	// 회원 리스트 출력 인터페이스 함수
	public List<UserDto> listUser();
	
	public String getUserById(String user_id);
	public String getUserByPw(String user_password);
	
	public UserDto findByUserNumber(int user_number);
	
	public int getLastUserNumber();
	
	public int updateUser(UserDto userDto);
	
	public int deleteUser(UserDto userDto);
}
